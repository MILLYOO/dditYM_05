package kr.or.ddit.info1.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import kr.or.ddit.validate.constraints.BlankPattern;
import kr.or.ddit.validate.groups.DeleteGroup;
import kr.or.ddit.validate.groups.DetailUpdateGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import lombok.Data;

/**
 * 거래처 VO
 */
@Data
public class BuyerVO implements Serializable{

	@NotNull(groups= {UpdateGroup.class, DeleteGroup.class})
	private Integer buyerCode;
	
	@NotBlank(groups= {Default.class})
	private String buyerName;
	@BlankPattern(placeholder="012-34-56789", regexp="\\d{3}-\\d{2}-\\d{5}", groups= {DetailUpdateGroup.class})
	private String buyerRegnumber;
	@NotBlank(groups= {Default.class})
	private String buyerCeo;
	@NotBlank(groups= {Default.class})
	private String buyerSort;
	private String buyerWorktype;
	private String buyerEvent;
	private String buyerAdd1;
	private String buyerAdd2;
	@BlankPattern(placeholder="00-0000-0000", regexp="\\d{2,3}-\\d{3,4}-\\d{4}", groups= {DetailUpdateGroup.class})
	private String buyerTel;
	@BlankPattern(placeholder="00-0000-0000", regexp="\\d{2,3}-\\d{3,4}-\\d{4}", groups= {DetailUpdateGroup.class})
	private String buyerFax;
	private String buyerPartner;
	private String buyerDesk;
	@BlankPattern(placeholder="2000-01-01", regexp="\\d{4}/\\d{2}/\\d{2}", groups= {DetailUpdateGroup.class})
	private String buyerStart;
	@BlankPattern(placeholder="2000-01-01", regexp="\\d{4}/\\d{2}/\\d{2}", groups= {DetailUpdateGroup.class})
	private String buyerEnd;
	private String shipCode;
	private String shipName;
	private String shipAdd;
	private String buyerReceiver;
	private Integer empLine;
	private String empMail;
	@NotBlank(groups= {Default.class})
	private String buyerUse;
}
