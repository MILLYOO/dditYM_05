package tm.comm.vo;

import java.util.Date;

public class ImgVO {
	
	//선생님은 id long /number 였음
	private String tm_bimg_id = "-1"; //첨부 파일 ID.
	private String tm_bimg_date; 	//생성일자
	private String tm_bimg_use;  	//사용여부
	private int tm_bimgd_filesn = 1; //파일순번
	private String tm_bimgd_course; //저장경로
	private String tm_bimgd_sname;	//저장 파일명
	private String tm_bimgd_oname;	//원본 파일명
	private String tm_bimgd_extsn;   //파일 확장자
	private String tm_bimgd_cn;		//파일 내용
	private long tm_bimgd_size = 0;  	//파일 크기
	
	
	
	public String getTm_bimg_id() {
		return tm_bimg_id;
	}
	public void setTm_bimg_id(String tm_bimg_id) {
		this.tm_bimg_id = tm_bimg_id;
	}
	public String getTm_bimg_date() {
		return tm_bimg_date;
	}
	public void setTm_bimg_date(String tm_bimg_date) {
		this.tm_bimg_date = tm_bimg_date;
	}
	public String getTm_bimg_use() {
		return tm_bimg_use;
	}
	public void setTm_bimg_use(String tm_bimg_use) {
		this.tm_bimg_use = tm_bimg_use;
	}
	public int getTm_bimgd_filesn() {
		return tm_bimgd_filesn;
	}
	public void setTm_bimgd_filesn(int tm_bimgd_filesn) {
		this.tm_bimgd_filesn = tm_bimgd_filesn;
	}
	public String getTm_bimgd_course() {
		return tm_bimgd_course;
	}
	public void setTm_bimgd_course(String tm_bimgd_course) {
		this.tm_bimgd_course = tm_bimgd_course;
	}
	public String getTm_bimgd_sname() {
		return tm_bimgd_sname;
	}
	public void setTm_bimgd_sname(String tm_bimgd_sname) {
		this.tm_bimgd_sname = tm_bimgd_sname;
	}
	public String getTm_bimgd_oname() {
		return tm_bimgd_oname;
	}
	public void setTm_bimgd_oname(String tm_bimgd_oname) {
		this.tm_bimgd_oname = tm_bimgd_oname;
	}
	public String getTm_bimgd_extsn() {
		return tm_bimgd_extsn;
	}
	public void setTm_bimgd_extsn(String tm_bimgd_extsn) {
		this.tm_bimgd_extsn = tm_bimgd_extsn;
	}
	public String getTm_bimgd_cn() {
		return tm_bimgd_cn;
	}
	public void setTm_bimgd_cn(String tm_bimgd_cn) {
		this.tm_bimgd_cn = tm_bimgd_cn;
	}
	public long getTm_bimgd_size() {
		return tm_bimgd_size;
	}
	public void setTm_bimgd_size(long tm_bimgd_size) {
		this.tm_bimgd_size = tm_bimgd_size;
	}
	
	
	
	
	
	
}
