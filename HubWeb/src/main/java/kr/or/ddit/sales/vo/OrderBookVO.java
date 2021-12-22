package kr.or.ddit.sales.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import kr.or.ddit.validate.groups.DetailUpdateGroup;
import lombok.Data;

/**
 * 수주서 VO
 */
@Data
public class OrderBookVO implements Serializable{
	
	@NotNull(groups= {Default.class})
	private Integer orbDay;
	

	// 생산지시서에서 수주서적용 시 필요한 검증그룹으로 DetailUpdateGroup 사용했음 (211207인혜)
	
	private Integer orbNo;
	
	@NotBlank(groups=DetailUpdateGroup.class)
	private String orbNum;

	@NotBlank(groups= {Default.class})
	private String orbDate;
	@NotNull(groups= {Default.class})
	private Integer buyerCode;
	@NotBlank(groups= {Default.class})
	private String buyerName;
	@NotBlank(groups= {Default.class})
	private String deptName;
	@NotBlank(groups= {Default.class})
	private String empName;
	private String orbVat;
	private String orbResult;
	private String orbFinish;
	
	private List<OrderBookVO> dataList;
	@Valid
	private List<OrderBookProdVO> dataProdList;

	
	// 수주서 적용 여부
	private String orbAppyn;
}
