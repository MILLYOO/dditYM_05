package kr.or.ddit.sales.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.groups.Default;

import kr.or.ddit.validate.constraints.BlankPattern;
import kr.or.ddit.validate.groups.DetailUpdateGroup;
import lombok.Data;

/**
 * 출고지시서 제품 VO
 * @author pc14
 *
 */
@Data
public class ReleaseOrderProdVO implements Serializable {

	private Integer reoNo;
	private String reoNum;
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
	private String ropDate;
	@NotNull(groups= {Default.class})
	private Integer ropQty;
	private long ropUprice;
	private long ropSp;
	private long ropVat;
	private long ropSumcost;
	@NotBlank(groups= {Default.class})
	private String warName;
	@NotBlank(groups= {Default.class})
	private String projName;
	private String orbNum;
	
}
