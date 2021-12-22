package kr.or.ddit.produce.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import org.hibernate.validator.constraints.Range;

import lombok.Data;

/**
 * 생산지시서-원자재 VO
 */
@Data
public class InstRawsVO implements Serializable{
	
	private Integer instNo;	//기본키
	
	@NotBlank(groups=Default.class)
	private String instNum;
	
	@NotBlank(groups=Default.class)
	private String rawsCode;
	@NotBlank(groups=Default.class)
	private String icodeName;
	@NotBlank(groups=Default.class)
	private String rawsName;
	
	private String gcommName;
	private String ucommName;
	
	@NotNull
	@Range(min=1,max=99)
	private Integer prQty;
}
