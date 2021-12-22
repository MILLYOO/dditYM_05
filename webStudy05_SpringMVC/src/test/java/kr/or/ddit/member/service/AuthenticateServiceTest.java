package kr.or.ddit.member.service;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import kr.or.ddit.TestWebAppConfiuration;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.MemberVO;

@RunWith(SpringRunner.class)
@TestWebAppConfiuration
public class AuthenticateServiceTest {
	
	@Inject
	private AuthenticateService service;
	
	MemberVO input;
	
	@Before
	public void setUp() throws Exception {
		input = new MemberVO();
		input.setMemId("b001");
		input.setMemPass("java");
	}

	@Test
	public void testAuthenticated() {
		ServiceResult result = service.authenticated(input);
		assertEquals(ServiceResult.OK, result);
		assertEquals("이쁜이", input.getMemName());
	}
	
	@Test
	public void testAuthenticated2() {
		ServiceResult result = service.authenticated(input);
		assertEquals(ServiceResult.OK, result);
		assertEquals("이쁜이", input.getMemName());
	}

}
