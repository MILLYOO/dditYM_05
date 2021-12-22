package tm.comm.util;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

/**
 * sqlMapClient 객체를 제공하는 팩토리 클래스
 */
public class SqlMapClientFactory {
	
	private static SqlMapClient smc; //SqlMapClient객체 변수 선언
	
	/**
	 * SqlMapClient객체를 제공하는 팩토리메서드
	 * @return SqlMapClient 객체
	 */
	public static SqlMapClient getInstance() {
		if(smc == null) {
			try {
				// 1-1. xml문서 읽어오기
				Charset charset = Charset.forName("UTF-8");	// 설정파일 인코딩 설정
				Resources.setCharset(charset);
				Reader rd = Resources.getResourceAsReader("SqlMapConfig.xml");
				
				// 1-2. 위에서 읽어온 Reader객체를 이용하여 실제 작업을 진행 할 객체 생성
				smc = SqlMapClientBuilder.buildSqlMapClient(rd);
				
				rd.close();
			}catch (IOException e) {
				System.out.println("SqlMapClient 객체 생성 실패");
				e.printStackTrace();
			}
		}
		return smc;
	}
	
	
	
	
}
