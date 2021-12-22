package kr.or.ddit.dummy.controller;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.naming.Context;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.dummy.service.DummyService;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/dummy")
@Slf4j
public class DummyController {
	private DummyService service;
	
	// 주입할 setter
	@Required
	@Inject
	public void setService(DummyService service) {
		this.service = service;
	}
	
	@PostConstruct // 객체를 생성하고 모든 주입이 끝난 이후에 init()호출
	public void init() {
		log.info("주입된 객체 : {}", service);
		log.info("주입된 컨테이너 주소 : {}", context.hashCode());
		log.info("boardImages 주소 : {}", saveFolderURL);
		ServletContext application = context.getServletContext();
		saveFolder = new File(application.getRealPath(saveFolderURL));
		if(!saveFolder.exists()) saveFolder.mkdirs();
	}
	
	@RequestMapping("/first")
	public String dummy(Model model) {
		String info = service.retrieveData();
		model.addAttribute("info", info);
		return "dummy/view1";
	}
	
	@RequestMapping("/second")
	public String form() {
		return "dummy/form";
	}
	
	@Inject
	private WebApplicationContext context;
	
	@Value("#{appInfo['boardImages']}")
	private String saveFolderURL;
	private File saveFolder;
	
	// 올라오는 파라미터의 이름과 메소드의 시그니처의 이름이 같으면 어노테이션을 안써도된다. required=true가 된다.
	@RequestMapping(value="/second", method=RequestMethod.POST)
	public String process(String uploader,
			MultipartFile uploadFile,
			RedirectAttributes redirectAttributes // 내부적으로 session scope안에 어트리뷰트를 넣음
			) throws IllegalStateException, IOException {
		log.info("uploader : {}", uploader);
		log.info("upload File : {}", uploadFile.getOriginalFilename());
		
		uploadFile.transferTo(new File(saveFolder, uploadFile.getOriginalFilename()));
		
		String saveURL = saveFolderURL + "/" + uploadFile.getOriginalFilename();
		
		redirectAttributes.addFlashAttribute("saveURL", saveURL);
		// scope에서 꺼내면 지워진다.
		return "redirect:/dummy/second";
	}
	
}
