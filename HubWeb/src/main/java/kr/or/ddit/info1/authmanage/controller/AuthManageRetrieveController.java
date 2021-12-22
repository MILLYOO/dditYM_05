package kr.or.ddit.info1.authmanage.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.info1.authmanage.service.AuthManageService;
import kr.or.ddit.info1.vo.MemAuthVO;
import kr.or.ddit.info1.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 권한설정
 */
@Controller
@Slf4j
public class AuthManageRetrieveController {
	@Inject
	private AuthManageService service;

	@RequestMapping(value = "/info1/authManageRetrieve.do")
	public String AuthManageRetrieve() {
		return "info/authmanage";
	}

	@RequestMapping(value = "/info1/authManageList.do", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<MemberVO> AuthManageList() {
		List<MemberVO> memberList = service.retrieveMemList();
		return memberList;
	}
	
	@RequestMapping(value = "/info1/authManageView.do", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<MemAuthVO> AuthManageView(@ModelAttribute("empCode") MemberVO member) {
		List<MemAuthVO> memberList = service.selectMemAuth(member);
		return memberList;
	}

}
