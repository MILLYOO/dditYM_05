package kr.or.ddit.board.service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.board.dao.AttatchDAO;
import kr.or.ddit.board.dao.AttatchDAOImpl;
import kr.or.ddit.board.dao.BoardDAO;
import kr.or.ddit.board.dao.BoardDAOImpl;
import kr.or.ddit.common.servlet.PKNotFoundException;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.util.CryptoUtils;
import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

public class BoardServiceImpl implements BoardService{
	private BoardDAO boardDAO = new BoardDAOImpl();
	private AttatchDAO attatchDAO = new AttatchDAOImpl();
	
	private String saveFolderPath = "d:/saveFiles";
	private File saveFolder = new File(saveFolderPath);
	private SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	public int processAttatches(BoardVO board, SqlSession sqlSession) {
		int rowCnt = 0;
		List<AttatchVO> attatchList = board.getAttatchList();
		if(attatchList != null && !attatchList.isEmpty()) {
			rowCnt = attatchDAO.insertAttatches(board, sqlSession);
			
//			if(1==1) throw new RuntimeException("강제 발생 예외");
			
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
	
	
	@Override
	public ServiceResult createBoard(BoardVO board) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		) {
			// 1. board 인서트
			// 2. attatch에 메타데이터 저장
			// 3. 이진데이터 저장 위치 : saveFolderPath
			ServiceResult result = null;
			int rowCnt = boardDAO.insertBoard(board, sqlSession);
			if(rowCnt > 0) {
				rowCnt += processAttatches(board, sqlSession);
				result = ServiceResult.OK;
				sqlSession.commit();
			}else {
				result = ServiceResult.FAILED;
			}
			return result;
		}
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
			try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
			){
				int rowCnt = boardDAO.updateBoard(board, sqlSession);
				if(rowCnt > 0) {
					// 올릴 파일 처리
					processAttatches(board, sqlSession);
					// 지울 파일 처리
					int[] delAttNos = board.getDelAttNos();
					if(delAttNos != null && delAttNos.length > 0) {
						List<AttatchVO> attatchList = saved.getAttatchList();
						attatchDAO.deleteAttatches(board, sqlSession);
						Arrays.sort(board.getDelAttNos());
						for(AttatchVO tmp : attatchList) {
							if(Arrays.binarySearch(delAttNos, tmp.getAttNo()) >= 0) 
								FileUtils.deleteQuietly(new File(saveFolder, tmp.getAttSavename()));
						}
					}
					sqlSession.commit();
					result = ServiceResult.OK;
				}else {
					result = ServiceResult.FAILED;
				}
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
	
	@Override
	public ServiceResult removeBoard(BoardVO board) {
		// 1. 트랜젝션 관리
		// 2. 자식에 대한 처리 후 게시물 삭제
		//  : 메타데이터 가져오기 -> 저장 -> 메타데이터 지우기 -> 게시물지우기 -> 2진데이터 지우기
		Object authenticated = authenticated(board);
		ServiceResult result = null;
		if(authenticated instanceof BoardVO) {
			BoardVO saved = (BoardVO) authenticated;
			try(
					SqlSession sqlSession = sqlSessionFactory.openSession();
					){
				List<AttatchVO> attatchList = saved.getAttatchList();
				attatchDAO.deleteAttatches(board, sqlSession);
				boardDAO.deleteBoard(board.getBoNo(), sqlSession);
				for(AttatchVO tmp : attatchList) {
					FileUtils.deleteQuietly(new File(saveFolder, tmp.getAttSavename()));
				}
				sqlSession.commit();
				result = ServiceResult.OK;
			} // try end
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
