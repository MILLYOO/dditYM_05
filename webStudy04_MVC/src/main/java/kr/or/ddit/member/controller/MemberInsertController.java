package kr.or.ddit.member.controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestPart;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.mvc.fileupload.MultipartFile;
import kr.or.ddit.util.ValidateUtils;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MemberInsertController{
	private MemberService service = new MemberServiceImpl();
	private String saveFolderURL = "/resources/memberImages";
	
	@RequestMapping("/member/memberInsert.do")
	public String form() {
		return "member/memberForm";
	}

	@RequestMapping(value="/member/memberInsert.do", method=RequestMethod.POST)
	public String process(@ModelAttribute("member") MemberVO member, @RequestPart(value="memImage", required=false) MultipartFile memImage, HttpServletRequest req) throws IOException{
//		1. 가입을 하기위한 파라미터 확보 -> MemberVO (빈유틸을 사용하기 위해 name을 vo의 파라미터와 맞추자)
//		MemberVO member = new MemberVO();
//		req.setAttribute("member", member);
////		String memId = req.getParameter("memId");
////		member.setMemId(memId);
//		
//		// req에 있는 파라미터를 맵형식으로 만들어 담은 후 populate를 통해 memberVO와 일치하는 파라미터 복사.
//		Map<String, String[]> paramterMap = req.getParameterMap();
//		try {
//			BeanUtils.populate(member, paramterMap);
//		} catch (IllegalAccessException | InvocationTargetException e) {
//			throw new ServletException(e);
//		}
		// 이미지 관련 추가
		member.setMemImage(memImage);
		// 멀티파트하나가 memImage에 들어가면서 vo내에서 img에 2진바이트배열형태로 변경후 img에 넣는다.
		
//		2. 검증 : DB스키마에 따른 검증 룰 (NotNull인가, 길이에 대한, 형식 등등)
		Map<String, List<String>> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		boolean valid = ValidateUtils.validate(member, errors, InsertGroup.class);
		
		String viewName = null;
		String message = null;
		if(valid) {
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
		req.setAttribute("message", message);
		
		return viewName;
	}
}
