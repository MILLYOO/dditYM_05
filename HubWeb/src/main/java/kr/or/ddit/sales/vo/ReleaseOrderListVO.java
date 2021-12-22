package kr.or.ddit.sales.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 출고지시서 - 제품 트랜잭션 관리 브이오
 * @author pc14
 *
 */
@Data
public class ReleaseOrderListVO implements Serializable {

	private List<ReleaseOrderVO> dataList;
	private List<ReleaseOrderProdVO> dataProdList;
}
