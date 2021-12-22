package kr.or.ddit.stock.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class WarItemVO implements Serializable{

	private String prodCode;
	private Integer wpQty;
	
	private Integer wrQty;
	
	private Integer warQty;

	//창고목록 가져올때 사용하는 변수
	private Integer warCode;
	private String warName;
	private Integer warSum;
	private String rawCode;
	
	//창고에 대한 품목 가져올때 사용하는 변수
	private String icodeName;
	private String rawsCode;
	private String rawsName;
	private String gcommName;
	private Integer baseQty;
	private Integer enterQty;
	private String ucommInname;
	private Integer outQty;
	private String ucommOutname;
	private Integer stoQty;
	private String ucommStname;
	private Integer rawsAdqinv;
	private Integer retroExcess;
	
	//재고실사 시 필요 변수 추가
	private String warAdd1;
	private String warAdd2;
	private String deptName;
	private String empName;
	private String ldivName;
	private String mdivName;
	private String sdivName;
	
}
