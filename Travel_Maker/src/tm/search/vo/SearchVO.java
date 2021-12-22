package tm.search.vo;

public class SearchVO {
	String tm_search_id     ;
	String tm_search_cate   ;
	String tm_search_name   ;
	String tm_search_addr  ;
	String tm_search_x      ;
	String tm_search_y      ;
	String tm_search_con    ;
	String tm_search_tel;
	String tm_search_hit    ;
	String img_id = ""       ;
	int tm_search_like;
	///-------------------------------------------------
	private long atchFileId = -1;
	
	public long getAtchFileId() {
		return atchFileId;
	}
	public void setAtchFileId(long atchFileId) {
		this.atchFileId = atchFileId;
	}
	//--------------------------------------------------
	public int getTm_search_like() {
		return tm_search_like;
	}
	public void setTm_search_like(int tm_search_like) {
		this.tm_search_like = tm_search_like;
	}
	public String getTm_search_tel() {
		return tm_search_tel;
	}
	public void setTm_search_tel(String tm_search_tel) {
		this.tm_search_tel = tm_search_tel;
	}
	public String getTm_search_id() {
		return tm_search_id;
	}
	public void setTm_search_id(String tm_search_id) {
		this.tm_search_id = tm_search_id;
	}
	public String getTm_search_addr() {
		return tm_search_addr;
	}
	public void setTm_search_addr(String tm_search_addr) {
		this.tm_search_addr = tm_search_addr;
	}
	public String getTm_search_cate() {
		return tm_search_cate;
	}
	public void setTm_search_cate(String tm_search_cate) {
		this.tm_search_cate = tm_search_cate;
	}
	public String getTm_search_name() {
		return tm_search_name;
	}
	public void setTm_search_name(String tm_search_name) {
		this.tm_search_name = tm_search_name;
	}
	public String getTm_search_x() {
		return tm_search_x;
	}
	public void setTm_search_x(String tm_search_x) {
		this.tm_search_x = tm_search_x;
	}
	public String getTm_search_y() {
		return tm_search_y;
	}
	public void setTm_search_y(String tm_search_y) {
		this.tm_search_y = tm_search_y;
	}
	public String getTm_search_con() {
		return tm_search_con;
	}
	public void setTm_search_con(String tm_search_con) {
		this.tm_search_con = tm_search_con;
	}
	public String getTm_search_hit() {
		return tm_search_hit;
	}
	public void setTm_search_hit(String tm_search_hit) {
		this.tm_search_hit = tm_search_hit;
	}
	public String getImg_id() {
		return img_id;
	}
	public void setImg_id(String img_id) {
		this.img_id = img_id;
	}
	
}
