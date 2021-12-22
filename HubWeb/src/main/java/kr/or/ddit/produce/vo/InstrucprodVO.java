package kr.or.ddit.produce.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import org.hibernate.validator.constraints.Range;

import kr.or.ddit.validate.constraints.BlankPattern;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 생산지시서 VO
 */
@Data
@EqualsAndHashCode(of="instNum")
public class InstrucprodVO implements Serializable{
	
	private String instNum;
	
	private String instDate;
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
	
	@BlankPattern(placeholder="2021/01/01 및 지시일보다 나중",regexp="^\\d{4}/(0[1-9]|1[012])/(0[1-9]|[12][0-9]|3[01])$")
	private String instComplete;
	
	@NotNull(groups=Default.class)
	@Range(min=1,max=99)
	private Integer instLeadqty;

	private String deptName;
	private String empName;
	private String kcommName;
	@BlankPattern(placeholder="Y/N",regexp="Y|N")
	private String instFinish;
	
	private String orbNum;
	private String arrNum;
	
	private String mrAppyn;
	private String pwAppyn;
}