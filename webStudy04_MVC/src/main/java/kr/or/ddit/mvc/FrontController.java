package kr.or.ddit.mvc;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.mvc.annotation.HandlerAdapter;
import kr.or.ddit.mvc.annotation.HandlerMapping;
import kr.or.ddit.mvc.annotation.RequestMappingHandlerAdapter;
import kr.or.ddit.mvc.annotation.RequestMappingHandlerMapping;
import kr.or.ddit.mvc.annotation.RequestMappingInfo;
// 이게 유일한 서블릿! => web.xml에 다 정의해놓음 
public class FrontController extends HttpServlet {   // 웹상의 모든 요청을 다 받을 수 있는 컨트롤러 => 요청을 다 받기 위해서는 service를 오버라이딩 해야 함 
// 프론트컨트롤러가 전체를 지휘(요청들어오면 => 핸들러매핑에게 넘김 => 어뎁터)     
   
//   private Map<String, Object> handlerMap;   // value에 controller의 객체를 넣어준다. => 이거 없애는 대신 핸들러 매핑 사용
   private HandlerMapping handlerMapping;
   private HandlerAdapter handlerAdapter;   // 리플렉션 기반으로 어뎁터가 핸들러를 호출 => 결합력을 끊음 => 리턴된 뷰네임을 받음
   private ViewResolver viewResolver;   // 프론트는 핸들러어뎁터에게 받은 뷰네임을 여기(viewResolver)로 넘겨줌 
   
   @Override
   public void init(ServletConfig config) throws ServletException {
      super.init(config);
      String basePackages = config.getInitParameter("basePackages");
//      handlerMap = new LinkedHashMap<>();
      // 지금까지는 Controller객체를 생성한 적이 없었지만,(Controller는 다 서블릿 이었으니까) 이 구조에서는 생성해줘야 함
//      handlerMap.put("/member/memberInsert.do", new MemberInsertController());   // 매핑할때 주소들의 공통점 => .do로 끝남 => web.xml에 설정 
//      handlerMap.put("/mypage.do", new MypageController()); 
      String prefix = config.getInitParameter("prefix");
      String suffix = config.getInitParameter("suffix");
      handlerMapping = new RequestMappingHandlerMapping(basePackages.split("\\s+"));   // \\s는 공백 한칸, '+' 가 붙으면 공백 한칸 이상 
      handlerAdapter = new RequestMappingHandlerAdapter();
      viewResolver = new InternalResourceViewResolver();
      
      ((InternalResourceViewResolver)viewResolver).setPrefix(prefix);
      ((InternalResourceViewResolver)viewResolver).setSuffix(suffix);
   }
   
   @Override
   protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      // 여기서 분기를 해놓으면 뒷단에 있는 서블릿에서는 할 필요가 없음 (여기(프론트)에서 호출할 수 있도록 뒷단에서는 public으로 메서드가 정의되어 있어야 한다. 
//      req.setCharacterEncoding("UTF-8");   // 바디안의 데이터를 건들기 전에 실행되야 한다. requestWrapperFilter에서 데이터를 조작하기 떄문에 인코딩처리가 안된다.
      
      RequestMappingInfo mappingInfo = handlerMapping.findCommandHandler(req);
      
//      String viewName = null;
//      
//      Object handler = handlerMap.get(uri);
//      
//      int sc = 200;
//      
//      if(handler instanceof MemberInsertController) {
//         MemberInsertController controller = (MemberInsertController) handler;
//         if("GET".equals(method)) {
//            viewName = controller.form(req, resp);
//         }else if("POST".equals(method)) {
//            viewName = controller.process(req, resp);
//         }else {
//            sc = 405;
//         }
//      }else if(handler instanceof MypageController) {   // 백단 컨트롤러 하나 늘어나면 if문 하나 더 추가되어야 함 
//         MypageController controller = (MypageController) handler;
//         viewName = controller.myPageHandler(req, resp);
//      }
      
      if(mappingInfo==null) {   // 핸들러매핑이 핸들러를 못찾은 경우임 
         resp.sendError(HttpServletResponse.SC_NOT_FOUND, mappingInfo + "는 제공하지 않는 서비스임둥");
         return;
      }
      
      String viewName = handlerAdapter.invokeHandler(mappingInfo, req, resp);
      
      if(viewName==null) {
//         if(sc==200) sc = 500;   // sc가 여전히 200인 상태이면 500으로 바꿈 
         if(!resp.isCommitted()) {    
            // 메소드 지원되지 않는 경우, 
            // 개발자가 잘못 만든 경우 
            resp.sendError(500); 
         }
         // backend controller 에서 응답이 이미 결정된 경우 => 이거는 정상 처리된 경우임 => 리턴 
         return;
      }else {

         viewResolver.viewResolve(viewName, req, resp);
      }
      
   }
   
}