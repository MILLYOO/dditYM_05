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
 * 매출마감서VO
 */
@Data
public class SalesCloseVO implements Serializable {
	
	private String buyerReceiver;
	
	
	@NotNull(groups= {Default.class})
	private Integer salcDay;
	
	private String salcNum;
	@BlankPattern(placeholder="2000-01-01", regexp="\\d{4}/\\d{2}/\\d{2}", groups= {DetailUpdateGroup.class})
	private String salcDate;
	@NotNull(groups= {Default.class})
	private Integer buyerCode;
	@NotBlank(groups= {Default.class})
	private String buyerName;
	@NotBlank(groups= {Default.class})
	private String deptName;
	@NotBlank(groups= {Default.class})
	private String empName;
	private String salcVat;
	
	private List<SalesCloseVO> dataList;
	@Valid
	private List<SalProdVO> dataProdList; 
}
