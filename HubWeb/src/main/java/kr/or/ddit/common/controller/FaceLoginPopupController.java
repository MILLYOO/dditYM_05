package kr.or.ddit.common.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FaceLoginPopupController {
	
	@Resource(name="csrfTokenRepository")
	CsrfTokenRepository csrfTokenRepository;
	
	@RequestMapping(value="/login/faceLoginPopup.do", method=RequestMethod.GET)
	public String faceLoginPopup(
			HttpServletRequest req, HttpServletResponse resp, Model model){
		CsrfToken csrfToken = csrfTokenRepository.generateToken(req);//토큰 생성
		csrfTokenRepository.saveToken(csrfToken, req, resp);//토큰세션 저장
		String faceTokenSession = csrfToken.getToken();
		model.addAttribute("faceTokenSession",faceTokenSession);
		
		return "login/faceCamPopup";
	}
}
