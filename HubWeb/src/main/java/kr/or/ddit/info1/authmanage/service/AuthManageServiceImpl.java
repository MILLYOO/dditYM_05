package kr.or.ddit.info1.authmanage.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.info1.authmanage.dao.AuthManageDAO;
import kr.or.ddit.info1.vo.MemAuthVO;
import kr.or.ddit.info1.vo.MemberVO;

@Service
public class AuthManageServiceImpl implements AuthManageService {

	@Inject
	AuthManageDAO authManageDAO;
	
	@Override
	public ServiceResult updateAuth(MemAuthVO memAuth) {
		ServiceResult result = null;
		int rowcnt = authManageDAO.updateMemberAuth(memAuth);
		if(rowcnt > 0) {
			result = ServiceResult.OK;		
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public List<MemberVO> retrieveMemList() {
		return authManageDAO.selectMemList();
	}

	@Override
	public List<MemAuthVO> selectMemAuth(MemberVO member) {
		return authManageDAO.selecMemAuth(member);
	}
	
	
}
