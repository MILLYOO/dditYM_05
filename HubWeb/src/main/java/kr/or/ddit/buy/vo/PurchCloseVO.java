package kr.or.ddit.buy.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.groups.Default;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 매입마감서VO
 */
@Data
@EqualsAndHashCode(of="purNum")
public class PurchCloseVO implements Serializable{
	private String purNum;
	private String docDate;
	private Integer buyerCode;
	private String buyerName;
	private String buyerReceiver;
	private String deptName;
	private String empName;
	private String purVat;
	
	@NotBlank(groups= {Default.class})
	private String buyYy;
	@NotBlank(groups= {Default.class})
	private String buyMm;
	@NotBlank(groups= {Default.class})
	private String buyDd;

	//매입순위표
	private String prcRatio;
	private String rowNum;
	
	// 제품 리스트
	List<IncomingRawsVO> incomingRawsList;
	
	//매입마감서-입고처리서 적용
	private String rawsCode;
	private String rawsName;
	private String gcommName;
	private String ucommName;
	private Integer pcrQty;
	private Integer prcUprice;
	private Integer prcSp;
	private Integer prcVat;
	private Integer prcSumcost;
	private String projName;
	
	//매입마감서 - 입고처리서 적용
	private String incDate;
	private String incVat;
	private Integer irQty;
	private Integer irUprice;
	private Integer irSp;
	private Integer irVat;
	private Integer irSumcost;
		
}




