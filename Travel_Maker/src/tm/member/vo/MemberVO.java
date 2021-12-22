package tm.member.vo;

import tm.comm.vo.PagingVO;

public class MemberVO {

	//여기에 zipvo 넣을지말지
	private String tm_id;
	private String tm_pass;
	private String tm_name;
	private String tm_tel;		
	private String tm_zip;		//우편번호
	private String tm_add1;		//번지주소
	private String tm_add2;		//도로명주소
	private String tm_email;	//메일
	private String tm_bir;		//생일
	private String tm_diss;		//탈퇴여부
	private String tm_date;		//가입일
	private int tm_author;		//권한
	private PagingVO pagingvo;
	
	
	public PagingVO getPagingvo() {
		return pagingvo;
	}
	public void setPagingvo(PagingVO pagingvo) {
		this.pagingvo = pagingvo;
	}
	public String getTm_id() {
		return tm_id;
	}
	public void setTm_id(String tm_id) {
		this.tm_id = tm_id;
	}
	public String getTm_pass() {
		return tm_pass;
	}
	public void setTm_pass(String tm_pass) {
		this.tm_pass = tm_pass;
	}
	public String getTm_name() {
		return tm_name;
	}
	public void setTm_name(String tm_name) {
		this.tm_name = tm_name;
	}
	public String getTm_tel() {
		return tm_tel;
	}
	public void setTm_tel(String tm_tel) {
		this.tm_tel = tm_tel;
	}
	public String getTm_zip() {
		return tm_zip;
	}
	public void setTm_zip(String tm_zip) {
		this.tm_zip = tm_zip;
	}
	public String getTm_add1() {
		return tm_add1;
	}
	public void setTm_add1(String tm_add1) {
		this.tm_add1 = tm_add1;
	}
	public String getTm_add2() {
		return tm_add2;
	}
	public void setTm_add2(String tm_add2) {
		this.tm_add2 = tm_add2;
	}
	public String getTm_email() {
		return tm_email;
	}
	public void setTm_email(String tm_email) {
		this.tm_email = tm_email;
	}
	public String getTm_bir() {
		return tm_bir;
	}
	public void setTm_bir(String tm_bir) {
		this.tm_bir = tm_bir;
	}
	public String getTm_diss() {
		return tm_diss;
	}
	public void setTm_diss(String tm_diss) {
		this.tm_diss = tm_diss;
	}
	public String getTm_date() {
		return tm_date;
	}
	public void setTm_date(String tm_date) {
		this.tm_date = tm_date;
	}
	public int getTm_author() {
		return tm_author;
	}
	public void setTm_author(int tm_author) {
		this.tm_author = tm_author;
	}
	@Override
	public String toString() {
		return "MemberVO [tm_id=" + tm_id + ", tm_pass=" + tm_pass + ", tm_name=" + tm_name + ", tm_tel=" + tm_tel
				+ ", tm_zip=" + tm_zip + ", tm_add1=" + tm_add1 + ", tm_add2=" + tm_add2 + ", tm_email=" + tm_email
				+ ", tm_bir=" + tm_bir + ", tm_diss=" + tm_diss + ", tm_date=" + tm_date + ", tm_author=" + tm_author
				+ "]";
	}
	
	

}
