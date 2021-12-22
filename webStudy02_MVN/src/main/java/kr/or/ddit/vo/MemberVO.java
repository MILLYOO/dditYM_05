package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *	ValueObject(DataTransferObject, Model, Bean) 
 *	JavaBean 규약	
 *	1. 값을 가질 수 있는 property 정의
 *	2. property encapsulation
 *	3. 캡슐화된 property에 정급한 수 있는 인터페이스 제공(getter/setter)
 *	4. property의 상태 비교할 수 있는 방법 제공
 *	5. 정렬의 기준을 제공
 *	6. property의 상태 확인 방법 제공	[ 보여질 목록과, 보이면 안되는 목록 구별 ]
 *  7. 직렬화 가능 객체로 선언
 */
@Data
//@Getter
//@Setter
@ToString(exclude= {"memRegno1","memRegno2","memPass"})
@EqualsAndHashCode(of= {"memId"})
@NoArgsConstructor
public class MemberVO implements Serializable{
	
	public MemberVO(String memId, String memPass) {
		super();
		this.memId = memId;
		this.memPass = memPass;
	}
	
	private String memId;
	private String memPass;
	private String memName;
	private String memRegno1;
	private String memRegno2;
	private String memBir;
	private String memZip;
	private String memAdd1;
	private String memAdd2;
	private String memHometel;
	private String memComtel;
	private String memHp;
	private String memMail;
	private String memJob;
	private String memLike;
	private String memMemorial;
	private String memMemorialday;
	private Integer memMileage;
	private String memDelete;
}
