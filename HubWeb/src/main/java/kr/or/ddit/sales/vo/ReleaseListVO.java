package kr.or.ddit.sales.vo;


import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

import lombok.Data;
/**
 * 출고처리서 - 제품 트랜잭션 관리 브이오
 * @author pc14
 *
 */
@Data
public class ReleaseListVO implements Serializable {
	@Valid
	private List<ReleaseVO> dataList;
	@Valid
	private List<RelProdVO> dataProdList;
}
