package tm.comm.vo;

public class SearchLikeHateVO {
	
	public int tm_search_sat; // 안쓰는 만족도
	private int tm_search_no; // 좋아요 번호
	private String tm_id; // 좋아요 한 사람	
	private String tm_search_id; // 좋아요 된 검색결과
	private int tm_lh_like; // 좋아요 default:0/ 좋아요 하면 :1
	private int tm_lh_hate; // 싫어요 default:0/ 싫어요 하면 :1
	
	
	
	public int getTm_search_sat() {
		return tm_search_sat;
	}
	public void setTm_search_sat(int tm_search_sat) {
		this.tm_search_sat = tm_search_sat;
	}
	public int getTm_search_no() {
		return tm_search_no;
	}
	public void setTm_search_no(int tm_search_no) {
		this.tm_search_no = tm_search_no;
	}
	public String getTm_id() {
		return tm_id;
	}
	public void setTm_id(String tm_id) {
		this.tm_id = tm_id;
	}
	public String getTm_search_id() {
		return tm_search_id;
	}
	public void setTm_search_id(String tm_search_id) {
		this.tm_search_id = tm_search_id;
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
