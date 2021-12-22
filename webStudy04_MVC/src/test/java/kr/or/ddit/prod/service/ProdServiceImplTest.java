package kr.or.ddit.prod.service;

import static org.junit.Assert.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.common.servlet.PKNotFoundException;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.vo.ProdVO;

@Controller
public class ProdServiceImplTest {
	
	@RequestMapping(value="/test.do", method=RequestMethod.POST)
	public String testHandler(HttpServletRequest req, HttpServletResponse resp) {
		return "viewName";
	};
	
	public String testHandler2(HttpServletRequest req, HttpServletResponse resp) {
		return "viewName";
	};
	
	
	private ProdService service = new ProdServiceImpl();
	private ProdVO dummy;
	private String prodId;

	@Before
	public void setUp() throws Exception {
		prodId = "P10100000q";
		dummy = new ProdVO();
	}

	@Test
	public void testCreateProd() {
		fail("Not yet implemented");
	}

	@Test
	public void testRetrieveProdList() {
		fail("Not yet implemented");
	}

	@Test
	public void testModifyProd() {
		fail("Not yet implemented");
	}

	@Test(expected=PKNotFoundException.class)
	public void testRetrieveProd() {
		ProdVO saved = service.retrieveProd(prodId);
//		assertNotNull(saved);
	}

}
