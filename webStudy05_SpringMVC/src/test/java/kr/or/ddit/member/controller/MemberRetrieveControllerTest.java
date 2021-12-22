package kr.or.ddit.member.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.TestWebAppConfiuration;

@RunWith(SpringRunner.class)
@TestWebAppConfiuration
public class MemberRetrieveControllerTest {
	
	@Inject
	WebApplicationContext context;
	MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build(); // 가짜 MockMvc환경이 만들어짐
	}

	@Test
	public void testMemberVIew() throws Exception {
//		mockMvc.perform(get("/member/memberView.do"))
//					.andExpect(status().isBadRequest())
//					.andDo(log())
//					.andReturn();
		mockMvc.perform(get("/member/memberView.do").param("who", "b001"))
					.andExpect(status().isOk())
					.andExpect(model().attributeExists("member"))
					.andExpect(view().name("member/ajax/memberView"))
					.andDo(log())
					.andReturn();
	}

	@Test
	public void testListView() throws Exception {
		mockMvc.perform(get("/member/memberLiust.do"))
				.andExpect(view().name("member/memberList"))
				.andReturn();
	}

	@Test
	public void testForm() throws Exception {
		mockMvc.perform(get("/member/memberLiust.do").accept(MediaType.APPLICATION_JSON))
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andDo(log())
		.andReturn();
	}

}
