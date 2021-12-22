package kr.or.ddit;

import java.io.File;
import java.io.IOException;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QrCode {
	
	public static void main(String[] args) throws IOException, WriterException {

		QRCodeWriter qrCodeWriter = new QRCodeWriter(); // QR 코드
		MultiFormatWriter barcode = new MultiFormatWriter(); // 바코드

		String text = "https://offbyone.tistory.com/91";
		text = new String(text.getBytes("UTF-8"), "ISO-8859-1");
		BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 200, 200);
		/*
		 * BitMatrix bitMatrix = barcode.encode(text, BarcodeFormat.CODE_128, 200, 200);
		 */
		File file = new File("C:\\Temp", "test.png");
		file.mkdirs();
		
		MatrixToImageWriter.writeToFile(bitMatrix, "png", file);
		
		
	}
}
