package kr.or.ddit.sales.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.groups.Default;

import kr.or.ddit.validate.constraints.BlankPattern;
import kr.or.ddit.validate.groups.DetailUpdateGroup;
import lombok.Data;

/**
 * 매출마감서-제품 VO
 */
@Data
public class SalProdVO implements Serializable {
	private Integer salcNo;
	private String salcNum;
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
	@BlankPattern(placeholder="2000-01-01", regexp="\\d{4}/\\d{2}/\\d{2}", groups= {DetailUpdateGroup.class})
	private String scpDate;
	private long scpQty;
	private long scpUprice;
	private long scpSp;
	private long scpVat;
	private long scpSumcost;
	@NotBlank(groups= {Default.class})
	private String warName;
	@NotBlank(groups= {Default.class})
	private String projName;
	private String relNum;
}
