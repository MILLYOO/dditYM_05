package kr.or.ddit.stock.vo;

import java.io.Serializable;

import lombok.Data;

//재고자산수불부 VO
@Data
public class StockAssetVO implements Serializable{
	private String stoassDate; // 수불일자
	private String prodCode;
	private Integer enterQty;
	private Integer enterUcost;
	private Integer enterCost;
	private Integer outQty;
	private Integer outUcost;
	private Integer outCost;
	private Integer stoQty;
	private Integer stoUcost;
	private Integer stoCost;
	
	private String dateStart;
	private String dateEnd;
}
