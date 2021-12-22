package kr.or.ddit.stock.vo;

import java.io.Serializable;

import lombok.Data;

/**
 * 원자재-창고 VO
 */
@Data
public class WarRawVO implements Serializable{
	private String rawCode;
	private Integer warCode;
	private Integer wrQty;
	
	// 창고 코드 찾기 위한 창고이름
	private String warName;
	
	
	// 자재출고시 수량 적용하기위한변수
	private Integer mrQty;
}
