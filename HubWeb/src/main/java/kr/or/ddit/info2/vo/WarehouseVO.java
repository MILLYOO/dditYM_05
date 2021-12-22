package kr.or.ddit.info2.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 창고VO
 */
@Data
public class WarehouseVO implements Serializable{
	private Integer warCode;
	private String warName;
	private String warAdd1;
	private String warAdd2;
	private String deptName;
	private String empName;
	private String warLine;
	private String warUse;
	
	private List<ItemVO> itemList;
}
