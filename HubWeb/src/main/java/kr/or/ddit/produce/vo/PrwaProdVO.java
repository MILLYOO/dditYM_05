package kr.or.ddit.produce.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import org.hibernate.validator.constraints.Range;

import lombok.Data;

/**
 * 생산품입고-품목 VO
 */
@Data
public class PrwaProdVO implements Serializable{
	private Integer arrNo;
	
	@NotBlank(groups=Default.class)
	private String arrNum;
	
	
	@NotBlank(groups=Default.class)
	private String prodCode;
	private String rawsCode;
	
	@NotBlank(groups=Default.class)
	private String icodeName;
	@NotBlank(groups=Default.class)
	private String prodName;
	
	private String gcommName;
	
	@NotNull(groups=Default.class)
	@Range(min=1,max=99)
	private Integer ppQty;
	
	private String kcommName;
	
	@NotBlank(groups=Default.class)
	private String warName;

	private String projName;
	private String instNum;
	private Integer ppBadnum;
}
