package kr.or.ddit.produce.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.groups.Default;

import lombok.Data;

/**
 * 자재출고처리서 VO
 */
@Data
public class MreleaseVO implements Serializable{
	private String mreNum;

	private String mreDate;
	@NotBlank(groups=Default.class)
	private String pdcYy;
	@NotBlank(groups=Default.class)
	private String pdcMm;
	@NotBlank(groups=Default.class)
	private String pdcDd;
	
	@NotBlank(groups=Default.class)
	private String icodeName;
	@NotBlank(groups=Default.class)
	private String prodCode;
	@NotBlank(groups=Default.class)
	private String prodName;
	
	private String gcommName;
	private Integer buyerCode;
	private String buyerName;
	private String deptName;
	private String empName;
	
	private String instNum;
}
