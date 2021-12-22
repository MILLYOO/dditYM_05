package kr.or.ddit.stock.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.groups.Default;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 재고이동VO
 */
@Data
@EqualsAndHashCode(of="stmNum")
public class StockMoveVO implements Serializable {
	private String stmNum;
	
	private String stmDate;
	@NotBlank(groups= {Default.class})
	private String outwarName;
	@NotBlank(groups= {Default.class})
	private String inwarName;
	private String empName;
	private String deptName;
	private String stmWhy;
	@NotBlank(groups= {Default.class})
	private String stockYy;
	@NotBlank(groups= {Default.class})
	private String stockMm;
	@NotBlank(groups= {Default.class})
	private String stockDd;
}
