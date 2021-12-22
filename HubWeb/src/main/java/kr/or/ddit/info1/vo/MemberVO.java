package kr.or.ddit.info1.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 모든 회원의 정보
 */
@Data
@ToString(exclude= {"memPass"})
@NoArgsConstructor
@EqualsAndHashCode(of= {"empCode","memId"})
public class MemberVO implements Serializable,HttpSessionBindingListener {
	
	public MemberVO(String memId, String memPass) {
		super();
		this.memId = memId;
		this.memPass = memPass;
	}
	
	@NotEmpty
	private Integer empCode;
	private String memId;
	private String memPass;
	private String empName;
	private String deptName;
	private String authNum;
	
	private List<MemAuthVO> memAuthList;
	
	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		// memberVO가 session에 들어가는 순간
		String attrName = event.getName();
		if("authMember".equals(attrName)) {
			Set<MemberVO> userList = (Set) event.getSession().getServletContext().getAttribute("userList");
			userList.add(this);
		}
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		// memberVO가 session에서 삭제되는 순간
		String attrName = event.getName();
		if("authMember".equals(attrName)) {
			Set<MemberVO> userList = (Set) event.getSession().getServletContext().getAttribute("userList");
			userList.remove(this);
		}
	}
	
}
