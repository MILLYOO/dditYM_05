package kr.or.ddit.buy.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import lombok.Data;

/**
 * 입고처리서-원자재 VO
 */
@Data
public class IncomingRawsVO implements Serializable {
	
	private Integer incNo;
	private String icodeName;
	private String incNum;
	@NotBlank(groups= {Default.class})
	private String rawsCode;
	private String rawsName;
	private String gcommName;
	private String ucommName;
	private String irDate;
	@NotNull(groups= {Default.class})
	private Integer irQty;
	@NotBlank(groups= {Default.class})
	private String warName;
	private Integer irUprice;
	private Integer irSp;
	private Integer irVat;
	private Integer irSumcost;
	private String ordNum;
	private String projName;

}
