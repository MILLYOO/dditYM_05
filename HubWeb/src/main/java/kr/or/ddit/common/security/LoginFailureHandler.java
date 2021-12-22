package kr.or.ddit.common.security;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


/**
 * 로그인 실패시 처리할 객체
 * @author pc01
 *
 */
@Slf4j
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {
   @Inject
   private MessageSourceAccessor msgAccessor;
   @Override
   public void onAuthenticationFailure(
         HttpServletRequest request, HttpServletResponse response, AuthenticationException exception
         ) throws IOException, ServletException {
      
      String errorMsg = null;
      // 유저가 존재하지 않을때
      if(exception instanceof UsernameNotFoundException){
    	  errorMsg = msgAccessor.getMessage("error.UsernameNotFoundException");
      // 비밀번호 불일치
      }else if(exception instanceof BadCredentialsException) {
         errorMsg = msgAccessor.getMessage("error.BadCredentials");
      } 
      request.getSession().setAttribute("errorMsg", errorMsg);
      response.sendRedirect(request.getContextPath() + "/");
   }
}