package kr.or.ddit.stock.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import lombok.Data;

/**
 * 재고이동서-품목 VO
 */
@Data
public class StomovItemVO implements Serializable{
	private Integer stmNo;
	private String stmNum;
	private String rawsCode;
	private String prodCode;
	private String itemCode; //rawsCode + prodCode
	@NotBlank(groups= {Default.class})
	private String icodeName;
	private String rawsName;
	private String prodName;
	private String gcommName;
	private String ucommName;
	@NotNull(groups= {Default.class})
	private Integer siQty;
	@NotBlank(groups= {Default.class})
	private String inwarName;
	@NotBlank(groups= {Default.class})
	private String outwarName;
}
