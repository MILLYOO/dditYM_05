package kr.or.ddit.stock.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.groups.Default;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 입출고조정서 VO
 */
@Data
@EqualsAndHashCode(of="adjNum")
public class AdjustmentVO implements Serializable{
	private String adjNum;
	private String adjDate;
	@NotBlank(groups= {Default.class})
	private String adjSort;//유형
	private String adjType;//조정구분
	private String deptName;
	private String empName;
	private String buyerName;
	
	@NotBlank(groups= {Default.class})
	private String stockYy;
	@NotBlank(groups= {Default.class})
	private String stockMm;
	@NotBlank(groups= {Default.class})
	private String stockDd;
	
}
