package kr.or.ddit.sales.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.groups.Default;

import kr.or.ddit.validate.constraints.BlankPattern;
import kr.or.ddit.validate.groups.DetailUpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 출고처리서-제품VO
 */
@Data
@EqualsAndHashCode
public class RelProdVO implements Serializable {
	private Integer relNo;
	private String relNum;
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
	@BlankPattern(placeholder="2000-01-01", regexp="\\d{4}/\\d{2}/\\d{2}", groups= {DetailUpdateGroup.class})
	private String rpDate;
	@PositiveOrZero
	private long rpQty;
	@PositiveOrZero
	private long rpUprice;
	@PositiveOrZero
	private long rpSp;
	@PositiveOrZero
	private long rpVat;
	@PositiveOrZero
	private long rpSumcost;
	@NotBlank(groups= {Default.class})
	private String warName;
	@NotBlank(groups= {Default.class})
	private String projName;
	private String reoName;
	
	//By원균_211207 : 그래프 그리려고 추가했어요
	private String prodRatio;
}
