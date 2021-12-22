package kr.or.ddit.produce.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

import lombok.Data;

/**
 * 생산품입고처리서 VO
 */
@Data
public class ProdWareVO implements Serializable{
	@NotBlank(groups=Default.class)
	@Size(min=4,max=4)
	private String pdcYy;
	@NotBlank(groups=Default.class)
	@Size(min=1,max=2)
	private String pdcMm;
	@NotBlank(groups=Default.class)
	@Size(min=1,max=2)
	private String pdcDd;
	
	private String arrNum;
	private String arrDate;
	private Integer buyerCode;
	private String buyerName;
	private String deptName;
	private String empName;
}
