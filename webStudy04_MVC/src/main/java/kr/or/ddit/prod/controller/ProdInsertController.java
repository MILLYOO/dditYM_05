package kr.or.ddit.prod.controller;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestPart;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.mvc.fileupload.MultipartFile;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.util.ValidateUtils;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.vo.ProdVO;

@Controller
public class ProdInsertController{
	private ProdService service = new ProdServiceImpl();
	
	private String saveFolderURL = "/resources/prodImages";
	
	@RequestMapping("/prod/prodInsert.do")
	public String prodView(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		return "prod/prodForm";
	}
	
	@RequestMapping(value = "/prod/prodInsert.do", method=RequestMethod.POST)
	public String process(@ModelAttribute("prod") ProdVO prod, 
				@RequestPart("prodImage") MultipartFile prodImage, HttpServletRequest req) throws ServletException, IOException {
		
//		ProdVO prod = new ProdVO();
//		req.setAttribute("prod", prod);
		// req에 있는 파라미터를 맵형식으로 만들어 담은 후 populate를 통해 memberVO와 일치하는 파라미터 복사.
//		Map<String, String[]> paramterMap = req.getParameterMap();
//		try {
//			BeanUtils.populate(prod, paramterMap);
//		} catch (IllegalAccessException | InvocationTargetException e) {
//			throw new ServletException(e);
//		}
		
		// 이미지 관련 추가
		if(!prodImage.isEmpty()) {
			prod.setProdImage(prodImage);
			
			File saveFolder = new File(req.getServletContext().getRealPath(saveFolderURL));
			if(!saveFolder.exists()) {
				saveFolder.mkdirs();
			}
			String saveName = UUID.randomUUID().toString();
			File dest = new File(saveFolder, saveName);
			prodImage.transferTO(dest);
			prod.setProdImg(saveName);
		}
		
		
//		2. 검증 : DB스키마에 따른 검증 룰 (NotNull인가, 길이에 대한, 형식 등등)
		Map<String, List<String>> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		boolean valid = ValidateUtils.validate(prod, errors, InsertGroup.class);
		
		String viewName = null;
		String message = null;
		if(valid) {
//		3-1. 통과
//			로직 사용
			ServiceResult result = service.createProd(prod);
			switch(result) {
			case OK : 
//			 - OK	  : index페이지로 이동
				viewName = "redirect:/prod/prodView.do?what="+prod.getProdId();
				break;
			default :
//			 - FAIL	  : 개발자가 잘못 만든 상황임(일반적으로는 500에러를 띄어야함) -> DB서버가 죽었음 
//			 			->  memberForm으로 이동(기존데이터 + 메시지).
				viewName = "prod/prodForm";
				message = "서버 오류";
			}
			
		}else {
//		3-2. 불통
			viewName = "prod/prodForm";
		}
		req.setAttribute("message", message);
		
		return viewName;
	}
	
}
