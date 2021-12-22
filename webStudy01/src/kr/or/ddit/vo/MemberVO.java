package kr.or.ddit.vo;

import java.io.Serializable;

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
public class MemberVO implements Comparable<MemberVO>, Serializable{
	public MemberVO() {
		super();
	}
	
	public MemberVO(String mem_id, String mem_pass) {
		super();
		this.mem_id = mem_id;
		this.mem_pass = mem_pass;
	}
	
	private String mem_id;
	private transient String mem_pass;
	
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getMem_pass() {
		return mem_pass;
	}
	public void setMem_pass(String mem_pass) {
		this.mem_pass = mem_pass;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mem_id == null) ? 0 : mem_id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MemberVO other = (MemberVO) obj;
		if (mem_id == null) {
			if (other.mem_id != null)
				return false;
		} else if (!mem_id.equals(other.mem_id))
			return false;
		return true;
	}
	
	@Override
	public int compareTo(MemberVO o) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public String toString() {
		return "MemberVO [mem_id=" + mem_id + "]";
	}
	
	
}
