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
 * 출고지시서 VO
 * @author pc14
 *
 */
@Data
public class ReleaseOrderVO implements Serializable {
	
	@NotNull(groups= {Default.class})
	private Integer reoDay;

	private String reoNum;
	@BlankPattern(placeholder="2000-01-01", regexp="\\d{4}/\\d{2}/\\d{2}", groups= {DetailUpdateGroup.class})
	private String reoDate;
	@NotNull(groups= {Default.class})
	private Integer buyerCode;
	@NotBlank(groups= {Default.class})
	private String buyerName;
	@NotBlank(groups= {Default.class})
	private String deptName;
	@NotBlank(groups= {Default.class})
	private String empName;
	private String reoVat;
	private String reoResult;
	private String reoFinish;         
	
	private List<ReleaseOrderVO> dataList;
	@Valid
	private List<ReleaseOrderProdVO> dataProdList;
}
