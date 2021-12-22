package kr.or.ddit.sales.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class SalesRankVO implements Serializable{
	
	public String startDate;
    public String endDate;
	
	private Integer rownum;
	private String buyerName;
	private String icodeName;
	private String prodName;
	private String prodCode;
	private String gcommName;
	private String ucommName;
	private String deptName;
	private String empName;
	private Integer salcQty;
	private Integer salcSp;
	private Integer salcVat;
	private long salcSumcost;
	private long scpQty;
	private long scpUprice;
	private long scpSp;
	private long scpVat;
	private long scpSumcost;
	private String  scpRatio;
	private String salcRatio;
	
	List<SalesRankVO> dataList;
	List<SalesRankVO> dataProdList;
	
	private Integer rowNum;
	private long sumCost;
}
