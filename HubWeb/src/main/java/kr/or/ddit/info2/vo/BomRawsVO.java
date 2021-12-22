package kr.or.ddit.info2.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import kr.or.ddit.validate.groups.DeleteGroup;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import lombok.Data;

/**
 * BOM-원재료 VO
 */
@Data
public class BomRawsVO implements Serializable{
	
	private String gcommName;
	@NotNull(groups= {InsertGroup.class})
	private Integer brawNetqty;
	@NotNull(groups= {InsertGroup.class})
	private Integer brawRequireqty;
	@NotNull(groups= {InsertGroup.class})
	private Integer brawLossrate;
	private Integer brawLevel;
	private Integer brawRegnum;
	private Integer rawAdqinv;
	@NotNull(groups= {DeleteGroup.class, UpdateGroup.class})
	private Integer bomNo;
	@NotNull(groups= {InsertGroup.class})
	private Integer bomCode;
	@NotBlank(groups= {InsertGroup.class})
	private String rawsCode;
	private String rawsName;
	private String icodeName;
	
	
	
	////////생산지시서 BOM전개를 위한 부분//////////
	private String itemCode;
	private List<BomRawsVO> bomRawsList;
	private String instNum;
	private String prodCode;
	private Integer instNo;
	private Integer prQty;
	private Integer instLeadqty;	//지시수량
	////////자재출고서 BOM전개를 위한 부분//////////
	private Integer mreNo;
	private String mreNum;
	private Integer mrQty;
	private String warName;
	//////////////////////////////////////
	
}
