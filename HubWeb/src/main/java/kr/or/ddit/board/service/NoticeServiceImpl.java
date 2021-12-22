package kr.or.ddit.board.service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.board.dao.AttatchDAO;
import kr.or.ddit.board.dao.NoticeDAO;
import kr.or.ddit.board.vo.AttatchVO;
import kr.or.ddit.board.vo.NoticeVO;
import kr.or.ddit.board.vo.PagingVO;
import kr.or.ddit.common.PKNotFoundException;
import kr.or.ddit.enumpkg.ServiceResult;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class NoticeServiceImpl implements NoticeService {
	@Inject
	private NoticeDAO noticeDAO;
	@Inject
	private AttatchDAO attatchDAO;
	
	@Value("#{appInfo.attatchPath}")
	private File saveFolder;
	
	
	private int processAttatches(NoticeVO notice) {
		int rowcnt = 0;
		List<AttatchVO> attatchList = notice.getAttatchList();
		if(attatchList != null && !attatchList.isEmpty()) {
			rowcnt = attatchDAO.insertAttatches(notice);	// 메타데이터넣기
//			if(1==1) throw new RuntimeException("강제발생예외");	// 트랜잭션 test위해 발생시킨 예외
			try {
				for(AttatchVO attatch : attatchList) {	// 이진데이터저장
					attatch.saveTo(saveFolder);
				}
			}catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return rowcnt;
	}

	@Transactional
	@Override
	public ServiceResult createNotice(NoticeVO notice) {
		ServiceResult result = null;
		int cnt = noticeDAO.insertNotice(notice);
		if(cnt > 0) {	// 글 등록
			cnt += processAttatches(notice);
			result = ServiceResult.OK;
		}else { 		// 실패
			result = ServiceResult.FAILED;
		}
		return result;
	}
	@Override
	public List<NoticeVO> retrieveNoticeList(PagingVO<NoticeVO> pagingVO) {
		int totalRecord = noticeDAO.selectTotalRecord(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		List<NoticeVO> noticeList = noticeDAO.selectNoticeList(pagingVO);
		pagingVO.setDataList(noticeList);
		return noticeList;
	}
	@Override
	public NoticeVO retrieveNotice(NoticeVO noti) {
		NoticeVO notice = noticeDAO.selectNotice(noti);
		if(notice== null) {
			throw new PKNotFoundException(noti.getNotiNum() + "번 글 존재하지 않음.");
		}
		noticeDAO.updateNotinumCount(noti.getNotiNum());
		return notice;
	}
	
	@Transactional
	@Override
	public ServiceResult modifyNotice(NoticeVO notice) {
		NoticeVO saved = retrieveNotice(notice);
		ServiceResult result = null;
		int cnt = noticeDAO.updateNotice(notice);
		if(cnt > 0) {
			processAttatches(notice);
			int[] delAttNos = notice.getDelAttNos();
			if(delAttNos != null && delAttNos.length > 0) {
				List<AttatchVO> attatchList = saved.getAttatchList();	//이진데이터 찾기 위함
				attatchDAO.deleteAttatches(notice);	// 메타 지우기
				Arrays.sort(delAttNos);	// 이진 탐색 위한 정렬
				for(AttatchVO tmp : attatchList) {
					if(Arrays.binarySearch(delAttNos, tmp.getAttNo()) >= 0) {
						FileUtils.deleteQuietly(new File(saveFolder,tmp.getAttSavename())); // 이진데이터지우기
					}
				}
			}
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}
	
	@Transactional
	@Override
	public ServiceResult removeNotice(NoticeVO notice) {
		ServiceResult result = null;
		NoticeVO saved = retrieveNotice(notice);
		if(notice.getNotiNum() != null) {
			List<AttatchVO> attatchList = saved.getAttatchList();	//이진데이터 찾기 위함
			attatchDAO.deleteAttatches(notice);
			noticeDAO.deleteNotice(notice);
			for(AttatchVO tmp : attatchList) {
				FileUtils.deleteQuietly(new File(saveFolder,tmp.getAttSavename()));
			}
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}
	
	@Override
	public AttatchVO download(AttatchVO attatch) {
		AttatchVO att = new AttatchVO();
		att.setAttNo(attatch.getAttNo());
		AttatchVO atch = attatchDAO.selectAttatch(att);
		if(atch == null) throw new PKNotFoundException();
		return atch;
	}

	@Override
	public List<NoticeVO> retrieveNoticeListMain() {
		return noticeDAO.selectNoticeListMain();
	}
	

}
