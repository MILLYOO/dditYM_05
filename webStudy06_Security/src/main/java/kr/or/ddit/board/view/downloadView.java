package kr.or.ddit.board.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.view.AbstractView;

import kr.or.ddit.vo.AttatchVO;

public class downloadView extends AbstractView {
	@Value("#{appInfo.attatchPath}")
	private String saveFolderPath;
	@Value("#{appInfo.attatchPath}") // 스프링프로퍼티에디터는 꺼내서 넣으면 혼자 알아서 타입을 맞추어서 넣어줌
	private File saveFolder;
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse resp) throws Exception {
		AttatchVO atch = (AttatchVO) model.get("atch");
		File saveFile = new File(saveFolder, atch.getAttSavename());
		if(!saveFile.exists()) throw new FileNotFoundException("저장위치에 해당 파일이 없음"); // 파일이 없어졌을 경우 서버의 잘못이됨.
//		resp.setContentType(atch.getAttMime()); // 이 코드를 넣을 경우 브라우저가 바로 실행을 할 수 있도록 하는것이다.
		String fileName = URLEncoder.encode(atch.getAttFilename(), "UTF-8").replaceAll("\\+", " "); // 한글로 처리하기 위해. 인코딩을 해줘야한다.
		resp.setHeader("Content-Length", atch.getAttFilesize().toString()); // 있어도 되고 없어도 된다.
		resp.setContentType("application/octet-stream");
		resp.setHeader("Content-Disposition", "attatchment;filename=\""+ fileName +"\""); // inline 브라우저로 2진 데이터가 나가면 바로 실행해서 보여줘라.
																//attatchment 이 데이터는 저장을 해야한다는것.
																// \"\" 파일 이름의 공백을 처리하기위함(이 셋팅을 안하면 헤더가 두개로 나눠진다)		
		try(
			OutputStream os = resp.getOutputStream();
		){
			FileUtils.copyFile(saveFile, os);
		}
	}

}
