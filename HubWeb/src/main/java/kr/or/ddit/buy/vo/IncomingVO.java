package kr.or.ddit.buy.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import kr.or.ddit.validate.groups.DetailUpdateGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 입고처리서 VO
 */
@Data
@EqualsAndHashCode(of="incNum")
public class IncomingVO  implements Serializable{
	
	@NotBlank(groups= {DetailUpdateGroup.class})
	private String incNum;
	private String incDate;
	private Integer buyerCode;
	@NotBlank(groups= {Default.class,DetailUpdateGroup.class})
	private String buyerName;
	private String deptName;
	private String empName;
	private String incVat;
	private String incResult;
	private String incFinish;
	
	@NotBlank(groups= {Default.class})
	private String buyYy;
	@NotBlank(groups= {Default.class})
	private String buyMm;
	@NotBlank(groups= {Default.class})
	private String buyDd;
	
	//입고처리서 <- 발주서 적용
	private String icodeName;
	@NotNull(groups= {UpdateGroup.class})
	private Integer irQty;
	private String ordNum;
	
	private List<IncomingRawsVO> dataProdList;
}
