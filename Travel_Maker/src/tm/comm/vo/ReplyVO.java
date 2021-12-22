package tm.comm.vo;

public class ReplyVO {
	
	private int tm_bc_no;
	private String tm_id;
	private int tm_b_no;
	private int tm_category_id;
	private String tm_bc_writer;
	private String tm_bc_content;
	private String tm_bc_date;
	private String tm_bc_modate;
	public int getTm_bc_no() {
		return tm_bc_no;
	}
	public void setTm_bc_no(int tm_bc_no) {
		this.tm_bc_no = tm_bc_no;
	}
	public String getTm_id() {
		return tm_id;
	}
	public void setTm_id(String tm_id) {
		this.tm_id = tm_id;
	}
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
	public String getTm_bc_writer() {
		return tm_bc_writer;
	}
	public void setTm_bc_writer(String tm_bc_writer) {
		this.tm_bc_writer = tm_bc_writer;
	}
	public String getTm_bc_content() {
		return tm_bc_content;
	}
	public void setTm_bc_content(String tm_bc_content) {
		this.tm_bc_content = tm_bc_content;
	}
	public String getTm_bc_date() {
		return tm_bc_date;
	}
	public void setTm_bc_date(String tm_bc_date) {
		this.tm_bc_date = tm_bc_date;
	}
	public String getTm_bc_modate() {
		return tm_bc_modate;
	}
	public void setTm_bc_modate(String tm_bc_modate) {
		this.tm_bc_modate = tm_bc_modate;
	}
	@Override
	public String toString() {
		return "ReplyVO [tm_bc_no=" + tm_bc_no + ", tm_id=" + tm_id + ", tm_b_no=" + tm_b_no + ", tm_category_id="
				+ tm_category_id + ", tm_bc_writer=" + tm_bc_writer + ", tm_bc_content=" + tm_bc_content
				+ ", tm_bc_date=" + tm_bc_date + ", tm_bc_modate=" + tm_bc_modate + "]";
	}
	
	
}
