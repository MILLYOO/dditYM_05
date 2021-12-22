package kr.or.ddit.info2.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.groups.Default;

import kr.or.ddit.validate.groups.DeleteGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import lombok.Data;

/**
 * 제품 VO
 */
@Data
public class ItemVO implements Serializable{
	@NotBlank(groups= {UpdateGroup.class, DeleteGroup.class})
	private String rawsCode;
	private String prodCode;
	@NotBlank(groups= {Default.class})
	private String icodeName;
	private String prodName;
	@NotBlank(groups= {Default.class})
	private String rawsName;
	private String gcommName;
	private Integer prodQty;
	private String ldivName;
	private String mdivName;
	private String sdivName;
	private String ucommInname;
	private String ucommOutname;
	private String ucommStname;
	private Integer prodAdqinv;
	public void setProdAdqinv(String prodAdqinv) {
		if(prodAdqinv==null || prodAdqinv.isEmpty()) return;
		this.prodAdqinv = Integer.parseInt(prodAdqinv.replace(",", ""));
	}
	private Integer prodStancost;
	public void setProdStancost(String prodStancost) {
		if(prodStancost==null || prodStancost.isEmpty()) return;
		this.prodStancost = Integer.parseInt(prodStancost.replace(",", ""));
	}
	private Integer prodActucost;
	public void setProdActucost(String prodActucost) {
		if(prodActucost==null || prodActucost.isEmpty()) return;
		this.prodActucost = Integer.parseInt(prodActucost.replace(",", ""));
	}
	private Integer prodPurchprice;
	public void setProdPurchprice(String prodPurchprice) {
		if(prodPurchprice==null || prodPurchprice.isEmpty()) return;
		this.prodPurchprice = Integer.parseInt(prodPurchprice.replace(",", ""));
	}
	private Integer rawsPurchprice;
	public void setRawsPurchprice(String rawsPurchprice) {
		if(rawsPurchprice==null || rawsPurchprice.isEmpty()) return;
		this.rawsPurchprice = Integer.parseInt(rawsPurchprice.replace(",", ""));
	}
	private Integer prodSalprice;
	public void setProdSalprice(String prodSalprice) {
		if(prodSalprice==null || prodSalprice.isEmpty()) return;
		this.prodSalprice = Integer.parseInt(prodSalprice.replace(",", ""));
	}
	private Integer rawsSalprice;
	public void setRawsSalprice(String rawsSalprice) {
		if(rawsSalprice==null || rawsSalprice.isEmpty()) return;
		this.rawsSalprice = Integer.parseInt(rawsSalprice.replace(",", ""));
	}
	private String prodUse;
	private String rawsUse;
	private Integer prodBaseqty;
	public void setProdBaseqty(String prodBaseqty) {
		if(prodBaseqty==null || prodBaseqty.isEmpty()) return;
		this.prodBaseqty = Integer.parseInt(prodBaseqty.replace(",", ""));
	}
	private Integer rawsAdqinv;
	public void setRawsAdqinv(String rawsAdqinv) {
		if(rawsAdqinv==null || rawsAdqinv.isEmpty()) return;
		this.rawsAdqinv = Integer.parseInt(rawsAdqinv.replace(",", ""));
	}
	private Integer rawsBaseqty;
	public void setRawsBaseqty(String rawsBaseqty) {
		if(rawsBaseqty==null || rawsBaseqty.isEmpty()) return;
		this.rawsBaseqty = Integer.parseInt(rawsBaseqty.replace(",", ""));
	}
	private Integer rawsBaseucost;
	public void setRawsBaseucost(String rawsBaseucost) {
		if(rawsBaseucost==null || rawsBaseucost.isEmpty()) return;
		this.rawsBaseucost = Integer.parseInt(rawsBaseucost.replace(",", ""));
	}
	private Integer rawsBasecost;
	public void setRawsBasecost(String rawsBasecost) {
		if(rawsBasecost==null || rawsBasecost.isEmpty()) return;
		this.rawsBasecost = Integer.parseInt(rawsBasecost.replace(",", ""));
	}
	private Integer rawsActucost;
	public void setRawsActucost(String rawsActucost) {
		if(rawsActucost==null || rawsActucost.isEmpty()) return;
		this.rawsActucost = Integer.parseInt(rawsActucost.replace(",", ""));
	}
	private Integer rawsStancost;
	public void setRawsStancost(String rawsStancost) {
		if(rawsStancost==null || rawsStancost.isEmpty()) return;
		this.rawsStancost = Integer.parseInt(rawsStancost.replace(",", ""));
	}
	private Integer rawInvqty;
	public void setRawInvqty(String rawInvqty) {
		if(rawInvqty==null || rawInvqty.isEmpty()) return;
		this.rawInvqty = Integer.parseInt(rawInvqty.replace(",", ""));
	}
	private Integer rawInvucost;
	public void setRawInvucost(String rawInvucost) {
		if(rawInvucost==null || rawInvucost.isEmpty()) return;
		this.rawInvucost = Integer.parseInt(rawInvucost.replace(",", ""));
	}
	private Integer rawInvcost;
	public void setRawInvcost(String rawInvcost) {
		if(rawInvcost==null || rawInvcost.isEmpty()) return;
		this.rawInvcost = Integer.parseInt(rawInvcost.replace(",", ""));
	}
	
	//현재고총괄현황을 위한 변수------------------------
	//기말재고
	private Integer rawsQty;
	//구매입고
	private Integer buyEnter;
	//생산입고
	private Integer prodEnter;
	//판매출고
	private Integer sellOut;
	//생산출고
	private Integer prodOut;
	//입고합계
	private Integer sumEnter;
	//출고합계
	private Integer sumOut;
	//---------------------------------------------

	
	// 현재고조회
	private Integer warCode;	// 창고코드
	private Integer wrQty;		// 수량
	private String warName;		// 창고명
	private List<WarehouseVO> warList; 
}
