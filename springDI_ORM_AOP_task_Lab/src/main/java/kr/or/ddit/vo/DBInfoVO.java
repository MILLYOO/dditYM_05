package kr.or.ddit.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class DBInfoVO {
	private String driverClassName;
	private String url;
	private String user;
	private String password;
	
	private int initialSize;
	private int maxTotal;
	private long maxWait;
	private int test_3;
}
