package kr.or.ddit.stock.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import lombok.Data;

/**
 * 입출고조정서-품목 VO
 */
@Data
public class AdjItemVO implements Serializable{
	@NotBlank(groups= {Default.class})
	private String itemCode; //원자재+제품 view
	private String rawsName;
	private String prodName; //품명
	private String gcommName;
	private String ucommName;
	@NotBlank(groups= {Default.class})
	private String adjSort; //유형(출고/입고)
	private Integer adjNo; //순번
	private String adjNum; //조정번호
	private String rawsCode; //원자재코드
	private String prodCode; //제품코드
	@NotBlank(groups= {Default.class})
	private String icodeName; //품목계정
	private String gcommInname; //입고규격
	private String ucommInname; //입고단위\
	private Integer adjMqty; //이동수량
	private String gcommOutname; //출고규격
	private String ucommOutname; //출고단위
	@NotNull(groups= {Default.class})
	private Integer adjQty; //수량
	private Integer adjPrice; //단가
	private Integer adjCost; //금액
	@NotBlank(groups= {Default.class})
	private String warName; //창고이름
	private String projName; //프로젝트이름

	
}
