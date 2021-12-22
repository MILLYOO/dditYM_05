package kr.or.ddit.prod.controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.annotation.resolvers.RequestPart;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.mvc.fileupload.MultipartFile;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.util.ValidateUtils;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.ProdVO;
import lombok.extern.slf4j.Slf4j;

// /prod/prodUpdae.do viewName = prod/prodForm
@Controller
@Slf4j
public class ProdUpdateController {
	private ProdService service = new ProdServiceImpl();
	private String saveFolderURL = "/resources/prodImages";
	
	@RequestMapping("/prod/prodUpdate.do")
	public String form(@RequestParam("what") String prodId, HttpServletRequest req) {
//		String prodId = req.getParameter("what");
//		if(StringUtils.isBlank(prodId)) {
//			resp.sendError(400);
//			return null;
//		}
		ProdVO prod = service.retrieveProd(prodId);
		req.setAttribute("prod", prod);
		return "prod/prodForm";
	}
	
	@RequestMapping(value = "/prod/prodUpdate.do", method=RequestMethod.POST)
	public String process(@ModelAttribute("prod") ProdVO prod, @RequestPart(value="prodImage", required=false) MultipartFile prodImage, HttpServletRequest req) throws IOException {
//		ProdVO prod = new ProdVO();
//		req.setAttribute("prod", prod);
//		try {
//			BeanUtils.populate(prod, req.getParameterMap());
//		} catch (IllegalAccessException | InvocationTargetException e) {
//			throw new RuntimeException(e);
//		}
		if(prodImage!=null && !prodImage.isEmpty()) {
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
		
		Map<String, List<String>> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		boolean valid = ValidateUtils.validate(prod, errors, UpdateGroup.class);
		String viewName = null;
		if(valid) {
			ServiceResult result = service.modifyProd(prod);
			if(ServiceResult.OK.equals(result)) {
				viewName = "redirect:/prod/prodView.do?what=" + prod.getProdId();
			}else {
				viewName = "prod/prodForm";
			}
		}else { 
			viewName = "prod/prodForm";
		}
		return viewName;
	}
}
