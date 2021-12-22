package tm.comm.vo;

public class CartVO {

	
	
	
	private int tm_cart_no;
	private String tm_id;
	private String tm_prod_id;
	private String tm_prod_name;
	private int tm_cart_qty;
	private int tm_cart_price;
	private String tm_prod_comp;
	private int totalCount;
	private int totalPrice;
	
	
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getTm_prod_comp() {
		return tm_prod_comp;
	}
	public void setTm_prod_comp(String tm_prod_comp) {
		this.tm_prod_comp = tm_prod_comp;
	}
	public int getTm_cart_no() {
		return tm_cart_no;
	}
	public void setTm_cart_no(int tm_cart_no) {
		this.tm_cart_no = tm_cart_no;
	}
	public String getTm_id() {
		return tm_id;
	}
	public void setTm_id(String tm_id) {
		this.tm_id = tm_id;
	}
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
	public int getTm_cart_qty() {
		return tm_cart_qty;
	}
	public void setTm_cart_qty(int tm_cart_qty) {
		this.tm_cart_qty = tm_cart_qty;
	}
	public int getTm_cart_price() {
		return tm_cart_price;
	}
	public void setTm_cart_price(int tm_cart_price) {
		this.tm_cart_price = tm_cart_price;
	}
	
	
	
	
	
}
