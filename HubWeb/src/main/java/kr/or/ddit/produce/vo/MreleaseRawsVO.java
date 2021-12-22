package kr.or.ddit.produce.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import org.hibernate.validator.constraints.Range;

import lombok.Data;

/**
 * 자재출고처리서-원자재 VO
 */
@Data
public class MreleaseRawsVO implements Serializable{
	private Integer mreNo;
	
	@NotBlank(groups=Default.class)
	private String mreNum;
	
	@NotBlank(groups=Default.class)
	private String rawsCode;	
	@NotBlank(groups=Default.class)
	private String icodeName;
	@NotBlank(groups=Default.class)
	private String rawsName;
	
	private String gcommName;
	private String ucommName;

	@NotNull(groups=Default.class)
	@Range(min=1,max=999)
	private Integer mrQty;
	
	@NotBlank(groups=Default.class)
	private String warName;
	
	private String projName;
	private String instNum;
}