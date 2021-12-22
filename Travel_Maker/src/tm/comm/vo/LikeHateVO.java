package tm.comm.vo;

public class LikeHateVO {
	private int tm_lh_no; // 좋아요 번호
	private String tm_id; // 좋아요 한 사람
	
	private int tm_b_no; // 좋아요 된 글
	private int tm_category_id; // 좋아요 된 글 종류
	private int tm_lh_like; // 좋아요 default:0/ 좋아요 하면 :1
	private int tm_lh_hate; // 싫어요 default:0/ 싫어요 하면 :1
	
	
	


	public int getTm_lh_no() {
		return tm_lh_no;
	}
	public void setTm_lh_no(int tm_lh_no) {
		this.tm_lh_no = tm_lh_no;
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
	public int getTm_lh_like() {
		return tm_lh_like;
	}
	public void setTm_lh_like(int tm_lh_like) {
		this.tm_lh_like = tm_lh_like;
	}
	public int getTm_lh_hate() {
		return tm_lh_hate;
	}
	public void setTm_lh_hate(int tm_lh_hate) {
		this.tm_lh_hate = tm_lh_hate;
	}
	
	
	
}
