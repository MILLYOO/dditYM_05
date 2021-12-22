package kr.or.ddit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.layout.font.FontProvider;
import com.itextpdf.text.Document;
import com.itextpdf.text.Rectangle;

public class PdfTest {
	    private static final String ORIG = "D:\\A_TeachingMaterial\\7.LastProject\\workspace\\HubWeb\\src\\test\\java\\kr\\or\\ddit\\test1.html";
	    private static final String OUTPUT_FOLDER = "D:\\A_TeachingMaterial\\7.LastProject\\workspace\\HubWeb\\src\\test\\java\\kr\\or\\ddit\\";

	    public static void main(String[] args) throws IOException {
//	    	
//	    	FontProvider provider = new FontProvider();
//	    	provider.addFont(fontData);\
	    	
	    	String FONT = "kr/or/ddit/NanumGothic.ttf";
	    	
	    	ConverterProperties properties = new ConverterProperties();
	    	FontProvider fontProvider = new DefaultFontProvider(false, false, false);
	        FontProgram fontProgram = FontProgramFactory.createFont(FONT);
	        fontProvider.addFont(fontProgram);
	        properties.setFontProvider(fontProvider);
	    	
	        File htmlSource = new File(ORIG);
	        File pdfDest = new File(OUTPUT_FOLDER + "result10.pdf");
	        HtmlConverter.convertToPdf(new FileInputStream(htmlSource), new FileOutputStream(pdfDest), properties);
	       
	        
//	        Rectangle pageSize_ = new Rectangle(0,0,600,800);
//	        Document document_ = new Document(pageSize_);
//
//	        System.out.println("PDF 파일이 작성되었습니다.");
	   }
}
