package tm.comm.vo;

public class BoardVO{
	
	
	private int tm_b_no;			//글번호
	private int tm_category_id;		//카테고리 번호
	private String tm_id;			//아이디=작성자
	private String tm_b_title;		//제목
	private String tm_b_content;	//내용
	private int tm_b_ox;			//댓글여부
	private String tm_b_date;		//작성일
	private int tm_b_hit;			//조회수
	
	private String tm_bimg_id = "-1";	//첨부파일id
	private String tm_bimgd_course; //저장경로
	private String tm_bimgd_sname;	//저장 파일명
	private String tm_bimgd_extsn;   //파일 확장자
	
	private String tm_b_modate;		//수정일
	private int tm_lh_hate;
	public int getTm_b_no() {
		return tm_b_no;
	}
	public void setTm_b_no(int tm_b_no) {
		this.tm_b_no = tm_b_no;
	}
	public int getTm_category_id() {
		return tm_category_id;
	}
	public void setTm_category_id(int tm_category_id) {
		this.tm_category_id = tm_category_id;
	}
	public String getTm_id() {
		return tm_id;
	}
	public void setTm_id(String tm_id) {
		this.tm_id = tm_id;
	}
	public String getTm_b_title() {
		return tm_b_title;
	}
	public void setTm_b_title(String tm_b_title) {
		this.tm_b_title = tm_b_title;
	}
	public String getTm_b_content() {
		return tm_b_content;
	}
	public void setTm_b_content(String tm_b_content) {
		this.tm_b_content = tm_b_content;
	}
	public int getTm_b_ox() {
		return tm_b_ox;
	}
	public void setTm_b_ox(int tm_b_ox) {
		this.tm_b_ox = tm_b_ox;
	}
	public String getTm_b_date() {
		return tm_b_date;
	}
	public void setTm_b_date(String tm_b_date) {
		this.tm_b_date = tm_b_date;
	}
	public int getTm_b_hit() {
		return tm_b_hit;
	}
	public void setTm_b_hit(int tm_b_hit) {
		this.tm_b_hit = tm_b_hit;
	}
	public String getTm_bimg_id() {
		return tm_bimg_id;
	}
	public void setTm_bimg_id(String tm_bimg_id) {
		this.tm_bimg_id = tm_bimg_id;
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
	public String getTm_bimgd_extsn() {
		return tm_bimgd_extsn;
	}
	public void setTm_bimgd_extsn(String tm_bimgd_extsn) {
		this.tm_bimgd_extsn = tm_bimgd_extsn;
	}
	public String getTm_b_modate() {
		return tm_b_modate;
	}
	public void setTm_b_modate(String tm_b_modate) {
		this.tm_b_modate = tm_b_modate;
	}
	public int getTm_lh_hate() {
		return tm_lh_hate;
	}
	public void setTm_lh_hate(int tm_lh_hate) {
		this.tm_lh_hate = tm_lh_hate;
	}
	
	

	
	
}
