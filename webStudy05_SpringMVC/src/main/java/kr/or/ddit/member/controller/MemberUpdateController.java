package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.MemberVO;

@Controller
@RequestMapping("/member/memberUpdate.do")
public class MemberUpdateController {
	@Inject
	private MemberService service;

	@ModelAttribute("command")
	public String commandId() {
		return "UPDATE";
	}

	@RequestMapping
	public String doGet(@SessionAttribute(name = "authMember", required = true) MemberVO authMember, Model model) {
		MemberVO member = service.retrieveMember(authMember.getMemId());

		model.addAttribute("member", member);
		String viewName = "member/memberForm";

		return viewName;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String doPost(@Validated(UpdateGroup.class) @ModelAttribute("member") MemberVO member, Errors errors, Model model) throws ServletException, IOException {
//		2. 검증 : DB스키마에 따른 검증 룰 (NotNull인가, 길이에 대한, 형식 등등)
		String viewName = null;
		String message = null;
		if (!errors.hasErrors()) {
//		3-1. 통과
//			로직 사용
			ServiceResult result = service.modifyMember(member);
			switch (result) {
			case INVALIDPASSWORD:
				viewName = "member/memberForm";
				message = "비밀번호 틀림";
				break;
			case OK:
//			 - OK	  : index페이지로 이동
				viewName = "redirect:/mypage.do";
				break;
			default:
//			 - FAIL	  : 개발자가 잘못 만든 상황임(일반적으로는 500에러를 띄어야함) -> DB서버가 죽었음 
//			 			->  memberForm으로 이동(기존데이터 + 메시지).
				viewName = "member/memberForm";
				message = "서버 오류";
			}
		} else {
//		3-2. 불통
//			: memberForm으로 이동 (기존데이터 + 검증 결과 메시지 띄우기.)
			viewName = "member/memberForm";
		}
		return viewName;
	}
}
