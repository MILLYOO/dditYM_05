package tm.member.contoller;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
	
	public static void MailSend(String passWord, String email) {
		try{   
			Properties clsProp = System.getProperties();
			
			// 메일 서버 주소
			clsProp.put( "mail.smtp.host", "smtp.naver.com" );
			
			// 메일 서버 포트 번호
			clsProp.put( "mail.smtp.port", 587 );
			
			// 인증이 필요하면 아래와 같이 설정한다.
			clsProp.put( "mail.smtp.auth", "true" );
			
			Session clsSession = Session.getInstance( clsProp, new Authenticator(){
					public PasswordAuthentication getPasswordAuthentication(){
						// 인증 아이디/비밀번호를 저장한다.
						return new PasswordAuthentication( "travelmaker05", "java202105!" );
					}
				} );
			
			Message clsMessage = new MimeMessage( clsSession );
			
			// 발신자 이메일 주소를 설정한다.
			clsMessage.setFrom( new InternetAddress( "travelmaker05@naver.com" ) );
			
			// 수신자 이메일 주소를 설정한다.
			clsMessage.addRecipient( Message.RecipientType.TO, new InternetAddress( email ) );
			
			// 제목을 저장한다.
			clsMessage.setSubject( "[Travel_Maker] 비밀번호 찾기 안내입니다. " );
			
			// 메일 내용을 저장한다. 
			clsMessage.setContent( "회원님의 비밀번호는  [ " + passWord + " ] 입니다." , "text/plain;charset=utf-8" );
			
			// 메일 전송
			Transport.send( clsMessage );
		}
		catch( Exception e ){
			e.printStackTrace();
		}
	}
}
