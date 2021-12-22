package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/member/memberInsert.do")
public class MemberInsertController{
	@Inject
	private MemberService service;
	
	@ModelAttribute("command")
	public String commandId() {
		return "INSERT";
	}
	
	@RequestMapping
	public String form() {
		return "member/memberForm";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String process(
			@Validated(InsertGroup.class) @RequestAttribute("member") MemberVO member, // 커맨더 오브젝트
			Errors errors,
			Model model) throws IOException{
//		2. 검증 : DB스키마에 따른 검증 룰 (NotNull인가, 길이에 대한, 형식 등등)
		
		String viewName = null;
		String message = null;
		if(!errors.hasErrors()) {
//		3-1. 통과
//			로직 사용
			ServiceResult result = service.createMember(member);
			switch(result) {
			case PKDUPLICATED :
//			 - PK중복 : memberForm으로 이동(기존데이터 + pk중복에 대한 메시지).
				viewName = "member/memberForm";
				message = "아이디 중복";
				break;
			case OK : 
//			 - OK	  : index페이지로 이동
				viewName = "redirect:/";
				break;
			default :
//			 - FAIL	  : 개발자가 잘못 만든 상황임(일반적으로는 500에러를 띄어야함) -> DB서버가 죽었음 
//			 			->  memberForm으로 이동(기존데이터 + 메시지).
				viewName = "member/memberForm";
				message = "서버 오류";
			}
		}else {
//		3-2. 불통
//			: memberForm으로 이동 (기존데이터 + 검증 결과 메시지 띄우기.)
			viewName = "member/memberForm";
		}
		model.addAttribute("message", message);
		
		return viewName;
	}
}
