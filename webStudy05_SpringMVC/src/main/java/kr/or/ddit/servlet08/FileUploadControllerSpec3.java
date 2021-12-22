package kr.or.ddit.servlet08;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.annotation.resolvers.RequestPart;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.mvc.fileupload.MultipartFile;
import kr.or.ddit.mvc.fileupload.StandardMultipartHttpServletRequest;
import lombok.extern.slf4j.Slf4j;


//@WebServlet("/fileUpload")
//@MultipartConfig
@Controller
@Slf4j
public class FileUploadControllerSpec3 extends HttpServlet {
	String saveFolderURL = "/resources/images";
	
	@RequestMapping(value="/fileUploadSpec3.do", method=RequestMethod.POST)
	public String upload(
			@RequestParam("uploader") String uploader,
			@RequestPart("uploadFile") MultipartFile[] fileParts,
			StandardMultipartHttpServletRequest req, HttpServletResponse resp, HttpSession session) throws ServletException, IOException {
//		Body -> N개의 파트 (2.5 FileItem, 3.0이후부터 Part)
		session.setAttribute("uploader", uploader);
//		MultipartFile filePart = new StandardMultipartFile(req.getPart("uploadFile"));
//		MultipartFile filePart = req.getFile("uploadFile");
		int idx = 0;
		for(MultipartFile filePart : fileParts) {
			if(filePart.isEmpty()) continue;
			log.info("upload file : {}", filePart);
			String originalFilename = filePart.getOriginalFilename();
			log.info("원본 파일명 : {}", originalFilename);
			String contentType = filePart.getContentType();
			if(contentType != null)
				log.info("허용여부 : {}", contentType.startsWith("image/"));
			log.info("파일 크기 : {}", filePart.getSize());
			if(!filePart.isEmpty()) {
				File saveFolder = new File(req.getServletContext().getRealPath(saveFolderURL));
				String saveName = UUID.randomUUID().toString();
				File saveFile = new File(saveFolder, saveName);
				String fileURL = saveFolderURL + "/" + saveName;
				filePart.transferTO(saveFile);
				session.setAttribute("uploadFile_"+(idx++), fileURL);
			}
		}
		return "redirect:/11/fileUploadForm.jsp";
	}

//	private String parseDisposition(Part filePart) {
//		Content-Disposition: form-data; name="uploadFile"; filename="test.jpg" dawdawdaw
//		String headerValue = filePart.getHeader("Content-Disposition");
//		int fromIndex = headerValue.indexOf("filename=");
//		String filename = null;
//		if(fromIndex>=0) {
//			int index = headerValue.indexOf("=", fromIndex);
//			filename = headerValue.substring(index+1).split(";")[0].replaceAll("\"", "");
//		}else {
//			throw new IllegalArgumentException("일반 파라미터에 해당하는 파트는 파실할 수 없음.");
//		}
//		return filename;
//	}
}
