package kr.or.ddit.stock.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.groups.Default;

import lombok.Data;

/**
 * 재고평가서 VO
 */
@Data
public class StoValuVO implements Serializable{
	private String stvNum;
	@NotBlank(groups= {Default.class})
	private String stvStart;
	@NotBlank(groups= {Default.class})
	private String stvEnd;
	private String stvDate;
	private String deptName;
	private String empName;
	private String stvCheck;
}
