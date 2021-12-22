package kr.or.ddit.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import oracle.jdbc.pool.OracleConnectionPoolDataSource;

/**
 * Factory Object[Method] pattern 
 * : consumer 에 의해 소비되는 객체 생성만을 전담하는 객체 운영.
 *
 */
public class ConnectionFactory{
	private static String url;
	private static String user;
	private static String password;
	private static DataSource dataSource;
	// static code block
	static {
		// properties를 읽어올때 사용하는 api : properties, resource번들
		// properties : map형태, 외부의 데이터까지 관리 가능
		// resource : map형태, 메세지에 locale설정 가능
		ResourceBundle bundle = ResourceBundle.getBundle("kr.or.ddit.db.dbInfo", Locale.ENGLISH);
		url = bundle.getString("url");
		user = bundle.getString("user");
		password = bundle.getString("password");
		int initialSize = Integer.parseInt(bundle.getString("initialSize"));
		long maxWait = Long.parseLong(bundle.getString("maxWait"));
		int maxTotal = Integer.parseInt(bundle.getString("maxTotal"));
		BasicDataSource ds = new BasicDataSource();
		dataSource = ds;
		ds.setDriverClassName(bundle.getString("driverClassName"));
		ds.setUrl(url);
		ds.setUsername(user);
		ds.setPassword(password);
		ds.setInitialSize(initialSize);
		ds.setMaxWaitMillis(maxWait);
		ds.setMaxTotal(maxTotal);
//		OracleConnectionPoolDataSource ds;
//		try {
//			ds = new OracleConnectionPoolDataSource();
//			dataSource = ds;
//			ds.setURL(url);
//			ds.setUser(user);
//			ds.setPassword(password);
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
//		}
//		try {
//			Class.forName(bundle.getString("driverClassName"));
//			System.out.println(message);
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException(e);  // 톰캣한테 전달하기 위해 CheckedException을 UnCheckedException으로 바꿔주며 발생했던 에러를 인자로 넘겨줌.
//		}
	}
	public static Connection getConnection() throws SQLException{
//		3. Connection 생성
//		return DriverManager.getConnection(url, user, password);
		return dataSource.getConnection();
	}
}
