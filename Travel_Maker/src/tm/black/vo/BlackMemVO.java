package tm.black.vo;

public class BlackMemVO {
	
	private String tm_id;		//회원 아이디
	private String tm_bl_rs;	//차단 사유
	private String tm_bl_date;	//차단 일시
	private String tm_bl_admn;	//차단한 관리자
	
	
	public String getTm_id() {
		return tm_id;
	}
	public void setTm_id(String tm_id) {
		this.tm_id = tm_id;
	}
	public String getTm_bl_rs() {
		return tm_bl_rs;
	}
	public void setTm_bl_rs(String tm_bl_rs) {
		this.tm_bl_rs = tm_bl_rs;
	}
	public String getTm_bl_date() {
		return tm_bl_date;
	}
	public void setTm_bl_date(String tm_bl_date) {
		this.tm_bl_date = tm_bl_date;
	}
	public String getTm_bl_admn() {
		return tm_bl_admn;
	}
	public void setTm_bl_admn(String tm_bl_admn) {
		this.tm_bl_admn = tm_bl_admn;
	}
	
	
	@Override
	public String toString() {
		return "BlackMemberVO [tm_id=" + tm_id + ", tm_bl_rs=" + tm_bl_rs + ", tm_bl_date=" + tm_bl_date
				+ ", tm_bl_admn=" + tm_bl_admn + "]";
	}

}
