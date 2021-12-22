package kr.or.ddit.stock.vo;

import java.io.Serializable;

import lombok.Data;

/**
 * 재고평가서-품목 VO
 */
@Data
public class StovalItemVO implements Serializable{
	private Integer stvNo;
	private String stvNum;
	private String rawsCode;
	private String prodCode;
	private String itemCode; //rawsCode + prodCode <= raws든 prod든 grid에 그리기 위해서
	private String icodeName;
	private String prodName;
	private String gcommName;
	private String ucommName;
	private String ucommStname;
	private Integer prodBaseqty;
	private Integer prodBaseucost;
	private Integer prodBasecost;
	private Integer sviInqty;
	private Integer sviIncost;
	private Integer sviOutqty;
	private Integer sviOutcost;
	private Integer prodInvqty;
	private Integer prodInvucost;
	private Integer prodInvcost;
}
