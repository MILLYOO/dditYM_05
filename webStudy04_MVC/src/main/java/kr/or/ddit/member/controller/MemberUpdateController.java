package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

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
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.MemberVO;

@Controller
public class MemberUpdateController {
	private MemberService service = new MemberServiceImpl();
	@RequestMapping("/member/memberUpdate.do")
	public String doGet(HttpSession session, HttpServletRequest req){
		MemberVO authMember = (MemberVO) session.getAttribute("authMember");
		MemberVO member = service.retrieveMember(authMember.getMemId());

		req.setAttribute("member", member);
		String viewName = "member/memberForm";
		
		return viewName;
	}
	
	@RequestMapping(value="/member/memberUpdate.do",method=RequestMethod.POST)
	public String doPost(@ModelAttribute("member") MemberVO member,
			@RequestPart(value="memImage", required=false) MultipartFile memImage,
			HttpServletRequest req) throws ServletException, IOException {
//		1. 가입을 하기위한 파라미터 확보 -> MemberVO (빈유틸을 사용하기 위해 name을 vo의 파라미터와 맞추자)
/*		MemberVO member = new MemberVO();
		req.setAttribute("member", member);
		String memId = req.getParameter("memId");
		member.setMemId(memId);
		
		Map<String, String[]> paramterMap = req.getParameterMap();
		try {
			BeanUtils.populate(member, paramterMap);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			throw new ServletException(e);
		}
		*/
		member.setMemImage(memImage);
//		2. 검증 : DB스키마에 따른 검증 룰 (NotNull인가, 길이에 대한, 형식 등등)
		Map<String, List<String>> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		boolean valid = ValidateUtils.validate(member, errors, UpdateGroup.class);
		String viewName = null;
		String message = null;
		if(valid) {
//		3-1. 통과
//			로직 사용
			ServiceResult result = service.modifyMember(member);
			switch(result) {
			case INVALIDPASSWORD :
				viewName = "member/memberForm";
				message = "비밀번호 틀림";
				break;
			case OK : 
//			 - OK	  : index페이지로 이동
				viewName = "redirect:/mypage.do";
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
		return viewName;
	}
}
