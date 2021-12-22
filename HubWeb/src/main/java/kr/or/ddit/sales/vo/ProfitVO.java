package kr.or.ddit.sales.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class ProfitVO implements Serializable {
	
	public String startDate;
    public String endDate;
	
	private String buyerName; // 거래처
	private String deptName; // 부서
	private String empName; // 사원
	private String icodeName; // 품목계정
	private String prodName; // 품명
	private String gcommName; // 규격
	private String ucommName; // 단위
	private long scpQty; // 출고수량
	private long scpSumcost; // 매출총액
	private long prodActucost; // 매출원가
	private long scpProfit; // 매출총이익
	private String profitRatio; // 이익율
	
}
