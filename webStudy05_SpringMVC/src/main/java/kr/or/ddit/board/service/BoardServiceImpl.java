package kr.or.ddit.board.service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.board.dao.AttatchDAO;
import kr.or.ddit.board.dao.BoardDAO;
import kr.or.ddit.common.PKNotFoundException;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.util.CryptoUtils;
import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class BoardServiceImpl implements BoardService{
	@Inject
	private BoardDAO boardDAO;
	@Inject
	private AttatchDAO attatchDAO;
	
	@Value("#{appInfo.attatchPath}")
	private String saveFolderPath;
	@Value("#{appInfo.attatchPath}")
	private File saveFolder;
	
	@PostConstruct
	public void init() throws IOException {
//		saveFolder = new File(saveFolderPath);
		log.info("첨부파일 저장 위치 : {}", saveFolder.getCanonicalPath());
	}
	
	public int processAttatches(BoardVO board) {
		int rowCnt = 0;
		List<AttatchVO> attatchList = board.getAttatchList();
		if(attatchList != null && !attatchList.isEmpty()) {
			rowCnt = attatchDAO.insertAttatches(board);
			
			if(1==1) throw new RuntimeException("강제 발생 예외");
			
			try {
				for(AttatchVO atch : attatchList) {
					atch.saveTo(saveFolder);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return rowCnt;
	}
	
	
	@Transactional
	@Override
	public ServiceResult createBoard(BoardVO board) {
			// 1. board 인서트
			// 2. attatch에 메타데이터 저장
			// 3. 이진데이터 저장 위치 : saveFolderPath
			ServiceResult result = null;
			int rowCnt = boardDAO.insertBoard(board);
			if(rowCnt > 0) {
				rowCnt += processAttatches(board);
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAILED;
			}
			return result;
	}
	

	@Override
	public List<BoardVO> retrieveBoardList(PagingVO<BoardVO> pagingVO) {
		int totalRecord = boardDAO.selectTotalRecord(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		List<BoardVO> boardList = boardDAO.selectBoardList(pagingVO);
		pagingVO.setDataList(boardList);
		return boardList;
	}

	@Override
	public BoardVO retrieveBoard(int boNo) {
		BoardVO board = boardDAO.selectBoard(boNo);
		if(board == null) 
			throw new PKNotFoundException(boNo+"에 해당하는 게시물이 없음.");
		Map<String, Object> pMap = new HashMap<>();
		pMap.put("boNo", boNo);
		pMap.put("incType", "BO_HIT"); // replace text 활용
		boardDAO.incrementCount(pMap);
		return board;
	}

	@Override
	public ServiceResult modifyBoard(BoardVO board) {
		Object authenticated = authenticated(board);
		ServiceResult result = null;
		if(authenticated instanceof BoardVO) {
			BoardVO saved = (BoardVO) authenticated;
				int rowCnt = boardDAO.updateBoard(board);
				if(rowCnt > 0) {
					// 올릴 파일 처리
					processAttatches(board);
					// 지울 파일 처리
					int[] delAttNos = board.getDelAttNos();
					if(delAttNos != null && delAttNos.length > 0) {
						List<AttatchVO> attatchList = saved.getAttatchList();
						attatchDAO.deleteAttatches(board);
						Arrays.sort(board.getDelAttNos());
						for(AttatchVO tmp : attatchList) {
							if(Arrays.binarySearch(delAttNos, tmp.getAttNo()) >= 0) 
								FileUtils.deleteQuietly(new File(saveFolder, tmp.getAttSavename()));
						}
					}
					result = ServiceResult.OK;
				}else {
					result = ServiceResult.FAILED;
				}
		
		}else {
			result = ServiceResult.INVALIDPASSWORD;
		}
		return result;
	}
	
	private Object authenticated(BoardVO board){
		BoardVO saved = retrieveBoard(board.getBoNo()); // 찾을수 없음 pkNotFound
		String savedPass = saved.getBoPass();
		String inputPass = board.getBoPass();
		inputPass = CryptoUtils.sha512EncryptBase64(inputPass);
		if(savedPass.equals(inputPass)) { // true, false
			return saved;
		}else {
			return ServiceResult.INVALIDPASSWORD;
		}
	}
	@Transactional
	@Override
	public ServiceResult removeBoard(BoardVO board) {
		// 1. 트랜젝션 관리
		// 2. 자식에 대한 처리 후 게시물 삭제
		//  : 메타데이터 가져오기 -> 저장 -> 메타데이터 지우기 -> 게시물지우기 -> 2진데이터 지우기
		Object authenticated = authenticated(board);
		ServiceResult result = null;
		if(authenticated instanceof BoardVO) {
			BoardVO saved = (BoardVO) authenticated;
				List<AttatchVO> attatchList = saved.getAttatchList();
				attatchDAO.deleteAttatches(board);
				boardDAO.deleteBoard(board.getBoNo());
				for(AttatchVO tmp : attatchList) {
					FileUtils.deleteQuietly(new File(saveFolder, tmp.getAttSavename()));
				}
				result = ServiceResult.OK;
		}else {
			result = ServiceResult.INVALIDPASSWORD;
		}
		return result;
	}

	@Override
	public AttatchVO download(int attNo) {
		AttatchVO atch = attatchDAO.selectAttatch(attNo);
		if(atch==null)
			throw new PKNotFoundException(attNo+"파일의 메타데이터가 없음");
		return atch;
	}
}
