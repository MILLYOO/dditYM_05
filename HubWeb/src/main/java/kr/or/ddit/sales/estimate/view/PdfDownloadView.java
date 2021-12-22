package kr.or.ddit.sales.estimate.view;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.io.exceptions.IOException;
import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.layout.font.FontProvider;

import kr.or.ddit.info1.vo.BuyerVO;
import kr.or.ddit.sales.vo.EstimateProdVO;
import kr.or.ddit.sales.vo.EstimateVO;

/**
 * PdfDownloadView.java
 * By 김건웅_211202(최종수정)
 * 컨트롤러에서 가져온 DB를 바탕으로 실제 VIEW 를 구성하는 클래스입니다
 * 
 * 1.AbstractView 를 상속받아 해당 클래스를 View로 처리한다
 * 2.Controller 에서 전달받은 데이터를 꺼내준다
 * 3.데이터를 model에 삽입 후 pdfDownloadView 로 이동합니다. 
 * 4.폰트 및 다운로드 경로를 지정한다
 * 5.비어있는 PDF파일을 만든다. 파일 존재시 수정한다
 * 6.HTML 파일을 PDF파일로 변환한다
 */

//1.AbstractView 를 상속받아 해당 클래스를 View로 처리한다
public class PdfDownloadView extends AbstractView{

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest req,
			HttpServletResponse resp) throws Exception {
		//2.Controller 에서 전달받은 데이터를 꺼내준다
		EstimateVO estimateVO =  (EstimateVO) model.get("estimateVO");
		List<EstimateProdVO> estimateProdList =  (List<EstimateProdVO>) model.get("estimateProdList");
		List<BuyerVO> buyerVO = (List<BuyerVO>)model.get("buyerVO");
		
		//3.HTML 문자열로 구성한 클래스에 전달받은 데이터를 넣어주는 메서드를 실행한다. 
		EstimateHTML estimateHTML = new EstimateHTML();
		StringBuffer stringBufferHtml = estimateHTML.makeHtml(estimateVO, estimateProdList, buyerVO);
		String html = stringBufferHtml.toString();

		//4.폰트 및 다운로드 경로를 지정한다
	    String OUTPUT_FOLDER = "D:\\";	//pdf
		String FONT = "kr/or/ddit/NanumGothic.ttf";
		
		ConverterProperties properties = new ConverterProperties();
    	FontProvider fontProvider = new DefaultFontProvider(false, false, false);
    	
        FontProgram fontProgram = FontProgramFactory.createFont(FONT);
        fontProvider.addFont(fontProgram);
        properties.setFontProvider(fontProvider);
    	
        //5.비어있는 PDF파일을 만든다. 파일 존재시 수정한다
        File pdfDest = new File(OUTPUT_FOLDER + "HubWeb_견적서.pdf");
        boolean isExists = pdfDest.exists(); 
        	try {
        		if(!isExists) { 
        		pdfDest.createNewFile(); 
        		}
        	}catch(IOException ex) { 
        		ex.getMessage(); 
        	}
        	
        //6.HTML 파일을 PDF파일로 변환한다
        HtmlConverter.convertToPdf(html, new FileOutputStream(pdfDest), properties);
        
		//7.PDF파일을 크롬 브라우져에서 바로 보여준다
		String chrome = "C:/Program Files/Google/Chrome/Application/chrome.exe";
		try {
			new ProcessBuilder(chrome, OUTPUT_FOLDER+"HubWeb_견적서.pdf").start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}
		
		
}
