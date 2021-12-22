package kr.or.ddit.stock.vo;

import java.io.Serializable;

import lombok.Data;
//불량품재고현황
@Data
public class DefectiveStockVO implements Serializable {
	private String icodeName;
	private String prodCode;
	private String prodName;
	private String gcommName;
	private String kcommName;
	private String ppBadsum;
}
