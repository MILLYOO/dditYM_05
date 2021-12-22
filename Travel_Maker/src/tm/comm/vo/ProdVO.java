package tm.comm.vo;

public class ProdVO {
	
	private String tm_prod_id;
	private String tm_prod_name;
	private int tm_prod_price;
	private String tm_prod_comp;
	private String tm_bing_id = "";
	
	public String getTm_prod_id() {
		return tm_prod_id;
	}
	public void setTm_prod_id(String tm_prod_id) {
		this.tm_prod_id = tm_prod_id;
	}
	public String getTm_prod_name() {
		return tm_prod_name;
	}
	public void setTm_prod_name(String tm_prod_name) {
		this.tm_prod_name = tm_prod_name;
	}
	public int getTm_prod_price() {
		return tm_prod_price;
	}
	public void setTm_prod_price(int tm_prod_price) {
		this.tm_prod_price = tm_prod_price;
	}
	public String getTm_prod_comp() {
		return tm_prod_comp;
	}
	public void setTm_prod_comp(String tm_prod_comp) {
		this.tm_prod_comp = tm_prod_comp;
	}
	public String getTm_bing_id() {
		return tm_bing_id;
	}
	public void setTm_bing_id(String tm_bing_id) {
		this.tm_bing_id = tm_bing_id;
	}
	
}
