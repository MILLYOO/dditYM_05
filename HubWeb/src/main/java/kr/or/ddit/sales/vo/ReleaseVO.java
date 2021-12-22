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
 * 출고처리서 VO
 */
@Data
public class ReleaseVO implements Serializable {
	
	@NotNull(groups= {Default.class})
	private Integer relDay;
	
	private String relNum;
	@BlankPattern(placeholder="2000-01-01", regexp="\\d{4}/\\d{2}/\\d{2}", groups= {DetailUpdateGroup.class})
	private String relDate;
	@NotNull(groups= {Default.class})
	private Integer buyerCode;
	@NotBlank(groups= {Default.class})
	private String buyerName;
	@NotBlank(groups= {Default.class})
	private String deptName;
	@NotBlank(groups= {Default.class})
	private String empName;
	private String relVat;
	private String relResult;
	private String relFinish;

	private List<ReleaseVO> dataList;
	@Valid
	private List<RelProdVO> dataProdList;
}
