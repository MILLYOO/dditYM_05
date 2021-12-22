package kr.or.ddit.dummy.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
//@Controller
//@ResponseBody // 마샬링 + 직렬화 하기위한 Body를 생성해서 보냄.
@RestController
@RequestMapping("/dummy/ajax")
public class DummyAjaxController {
	// 이 메서드의 역활을 servlet-context의 mvc:view-controller가 해줌
//	@RequestMapping
//	public String ajaxView() {
//		return "dummy/ajaxView";
//	}
	
	@RequestMapping(produces="text/plain;charset=UTF-8")
	public String makeText() {
		return "응답 메시지";
	}
	
	
   @RequestMapping(produces="application/json;charset=UTF-8")
   public Map<String, Object> makeJson() {
      Map<String, Object> resultMap = new HashMap<>();
      resultMap.put("result", "success");
      resultMap.put("message", "성공");
      return resultMap;
   }
}