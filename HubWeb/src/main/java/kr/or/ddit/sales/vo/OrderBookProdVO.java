package kr.or.ddit.sales.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import kr.or.ddit.validate.groups.DetailUpdateGroup;
import lombok.Data;

/**
 * 수주서-제품 VO
 */
@Data
public class OrderBookProdVO implements Serializable{
	
	// 생산지시서에서 수주서적용 시 필요한 검증그룹으로 DetailUpdateGroup 사용했음 (211207인혜)
	
	private Integer orbNo;
	@NotBlank(groups=DetailUpdateGroup.class)
	private String orbNum;
	@NotBlank(groups= {Default.class,DetailUpdateGroup.class})
	private String prodCode;
	@NotBlank(groups= {Default.class,DetailUpdateGroup.class})
	private String icodeName;
	@NotBlank(groups= {Default.class,DetailUpdateGroup.class})
	private String prodName;
	private String rawsName;
	@NotBlank(groups= {Default.class})
	private String gcommName;
	@NotBlank(groups= {Default.class})
	private String ucommName;
	@NotBlank(groups= {Default.class})
	private String opDate;
	@NotNull(groups= {Default.class})
	private Integer opQty;
	@NotNull(groups= {Default.class})
	private long opUprice;
	@NotNull(groups= {Default.class})
	private long opSp;
	@NotNull(groups= {Default.class})
	private long opVat;
	@NotNull(groups= {Default.class})
	private long opSumcost;
	private String projName;
	private String estCode;
	private String itemCode;
}
