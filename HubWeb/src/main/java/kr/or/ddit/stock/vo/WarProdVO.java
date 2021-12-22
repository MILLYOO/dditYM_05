package kr.or.ddit.stock.vo;

import java.io.Serializable;

import lombok.Data;

/**
 * 제품-창고 VO
 */
@Data
public class WarProdVO implements Serializable{
	private Integer warCode;
	private String prodCode;
	private Integer wpQty;
	
	// 창고 코드 찾기 위한 창고 이름
	private String warName;
	
	
	// 생산품입고처리 재고변경위한변수
	private Integer ppQty;
}
