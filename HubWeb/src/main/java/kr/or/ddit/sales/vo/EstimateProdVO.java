package kr.or.ddit.sales.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import lombok.Data;

/**
 * 견적서-제품 VO
 */
@Data
public class EstimateProdVO implements Serializable {
	private Integer estNo;
	private String estCode;
	@NotBlank(groups= {Default.class})
	private String prodCode;
	@NotBlank(groups= {Default.class})
	private String icodeName;
	@NotBlank(groups= {Default.class})
	private String prodName;
	@NotBlank(groups= {Default.class})
	private String gcommName;
	@NotBlank(groups= {Default.class})
	private String ucommName;
	@NotBlank(groups= {Default.class})
	private String epDate;
	private String projName;
	@NotNull(groups= {Default.class})
	private Integer epQty;
	@NotNull(groups= {Default.class})
	private long epUprice;
	@NotNull(groups= {Default.class})
	private long epSp;
	@NotNull(groups= {Default.class})
	private long epVat;
	@NotNull(groups= {Default.class})
	private long epScost;
	private String itemCode;
	
	
	
}
