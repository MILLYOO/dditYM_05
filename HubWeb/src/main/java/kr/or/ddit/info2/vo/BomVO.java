package kr.or.ddit.info2.vo;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import kr.or.ddit.validate.groups.DeleteGroup;
import kr.or.ddit.validate.groups.InsertGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * BOM VO
 */
@Data
@EqualsAndHashCode(of="bomCode")
public class BomVO {
	//bomCode
	@NotNull(groups = DeleteGroup.class)
	private Integer bomCode;
	//제품코드
	private String prodCode;
	//원자재코드
	@NotBlank(groups= InsertGroup.class)
	private String rawsCode;
	//품목계정
	@NotBlank(groups= InsertGroup.class)
	private String icodeName;
	//품명
	private String prodName;
	@NotBlank(groups= InsertGroup.class)
	private String rawsName;
	//규격
	private String gcommName;
	//단위
	private String ucommName;
	
	// BOM원재료 리스트
	@Valid
	private List<BomRawsVO> bomRawsList;
}

