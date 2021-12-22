package kr.or.ddit.buy.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.groups.Default;

import lombok.Data;

/**
 * 매입마감서-원자재 VO
 */
@Data
public class CloseRawsVO implements Serializable{
	private Integer purNo; 
	@NotBlank(groups= {Default.class})
	private String icodeName;
	private String purNum;
	private String rawsCode;
	private String rawsName;
	private String gcommName;
	private String ucommName;
	@NotBlank(groups= {Default.class})
	private Integer pcrQty;
	private Integer prcUprice;
	private Integer prcSp;
	private Integer prcVat;
	private Integer prcSumcost;
	@NotBlank(groups= {Default.class})
	private String warName;
	private String projName;
	private String incNum;
	
	private String prcRatio;
	private String deptName;
	
	
}
