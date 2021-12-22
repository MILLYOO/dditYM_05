package kr.or.ddit.sales.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import kr.or.ddit.validate.constraints.BlankPattern;
import kr.or.ddit.validate.groups.DetailUpdateGroup;
import lombok.Data;

/**
 * 견적서VO
 */

@Data
public class EstimateVO implements Serializable{
	
	@NotNull(groups= {Default.class})
	private Integer salDd;
	
	private String estCode;
	@BlankPattern(placeholder="2000-01-01", regexp="\\d{4}/\\d{2}/\\d{2}", groups= {DetailUpdateGroup.class})
	private String estDate;
	@NotNull(groups= {Default.class})
	private Integer buyerCode;
	@NotBlank(groups= {Default.class})
	private String buyerName;
	private String buyerReceiver;
	@NotBlank(groups= {Default.class})
	private String deptName;
	@NotBlank(groups= {Default.class})
	private String empName;
	private String estVat;
	private String estResult;
	private String estFinish;
	private String estYear;
	private String estMonth;
	private String estDay;
	
	private List<EstimateVO> dataList;
	
	@Valid
	private List<EstimateProdVO> dataProdList;
	

}
