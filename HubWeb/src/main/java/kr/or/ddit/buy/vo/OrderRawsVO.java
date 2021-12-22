package kr.or.ddit.buy.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

import lombok.Data;

/**
 * 발주-원자재 VO
 */
@Data
public class OrderRawsVO implements Serializable
{
	private Integer ordNo;
	@NotBlank(groups= {Default.class})
	private String icodeName;
	private String ordNum;
	@NotBlank(groups= {Default.class})
	private String rawsCode;
	private String rawsName;
	private String gcommName;
	private String ucommName;
	@NotBlank(groups= {Default.class})
	private String orrDate;
	@NotBlank(groups= {Default.class})
	private Integer orrQty;
	@NotBlank(groups= {Default.class})
	private Integer orrUprice;
	private Integer orrSp;
	private Integer orrVat;
	private Integer orrSumcost;
	private String projName;
	
	//입고처리서 - 발주서 적용
	@NotBlank(groups= {Default.class})
	private String ordDate;
	private Integer buyerCode;
	private String buyerName;
	private String deptName;
	private String empName;
	private String ordVat;
	private String ordResult;
	private String ordFinish;
	private String warName;
	
	private String incNum;
	private List<OrderRawsVO> orderRawsList;
	
	
	//pdf
	@NotNull(groups= {Default.class})
	private Integer epQty;
	@NotNull(groups= {Default.class})
	private float epUprice;
	@NotNull(groups= {Default.class})
	private float epSp;
	@NotNull(groups= {Default.class})
	private float epVat;
	@NotNull(groups= {Default.class})
	private float epScost;
	private String itemCode;
	private String prodCode;
	private String prodName;
	
	
	
}
