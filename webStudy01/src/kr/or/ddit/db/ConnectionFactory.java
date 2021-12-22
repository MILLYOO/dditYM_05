package kr.or.ddit.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Factory Object[Method] pattern 
 * : consumer 에 의해 소비되는 객체 생성만을 전담하는 객체 운영.
 *
 */
public class ConnectionFactory{
	private static String url;
	private static String user;
	private static String password;
	// static code block
	static {
		// properties를 읽어올때 사용하는 api : properties, resource번들
		// properties : map형태, 외부의 데이터까지 관리 가능
		// resource : map형태, 메세지에 locale설정 가능
		ResourceBundle bundle = ResourceBundle.getBundle("kr.or.ddit.db.dbInfo", Locale.ENGLISH);
		url = bundle.getString("url");
		user = bundle.getString("user");
		password = bundle.getString("password");
		String message = bundle.getString("message");
		try {
			Class.forName(bundle.getString("driverClassName"));
			System.out.println(message);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);  // 톰캣한테 전달하기 위해 CheckedException을 UnCheckedException으로 바꿔주며 발생했던 에러를 인자로 넘겨줌.
		}
	}
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url, user, password);
	}
}
