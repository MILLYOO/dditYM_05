package kr.or.ddit.prod.preparer;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;

import kr.or.ddit.prod.dao.ProdDAO;
import kr.or.ddit.vo.MenuVO;
import lombok.extern.slf4j.Slf4j;

// 스프링 밖에서 관리되는 객체
// 동적 메뉴를 만들기 위한 객체.
// 어노테이션을 만들어서 루트와 서블릿 컨텍스트에 추가한 후 리졸버에 SpringBeanFactrory를 추가해야한다.
@Slf4j
@kr.or.ddit.annotations.ViewPreparer
public class ProdViewPreparer implements ViewPreparer{
	
	@Inject
	private ProdDAO dao;
	
	@PostConstruct
	public void init() {
		log.info("주입된 dao 객체 : {}", dao.getClass().getName());
	}
	
	@Override
	public void execute(Request tilesContext, AttributeContext attributeContext) {
		MenuVO menu1 = new MenuVO();
		menu1.setMenuURL("/prod/prodInsert.do");
		menu1.setMenuText("신규상품등록");
		MenuVO menu2 = new MenuVO();
		menu2.setMenuURL("/prod/prodList.do");
		menu2.setMenuText("상품목록");
		List<MenuVO> menuList = Arrays.asList(menu1, menu2);
		Map<String, Object> requestScope = tilesContext.getContext(Request.REQUEST_SCOPE); // 우리 알고있는데 스코프가 돌아온다.
		requestScope.put("menuList", menuList); // 동적으로 할 수 있는 기반을 만들기 위하여
		
	}
}
