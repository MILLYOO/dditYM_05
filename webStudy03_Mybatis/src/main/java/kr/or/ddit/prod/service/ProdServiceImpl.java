package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.common.servlet.PKNotFoundException;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.prod.dao.ProdDAO;
import kr.or.ddit.prod.dao.ProdDAOImpl;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

public class ProdServiceImpl implements ProdService{
	private ProdDAO dao = new ProdDAOImpl();

	@Override
	public ServiceResult createProd(ProdVO prod) {
		ServiceResult result = null;
			int rowcnt = dao.insertProd(prod);
			if(rowcnt > 0 ) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAILED;
			}
		return result;
	}

	@Override
	   public List<ProdVO> retrieveProdList(PagingVO<ProdVO> pagingVO) {
	      int totalRecord = dao.selectTotalRecord(pagingVO);
	      pagingVO.setTotalRecord(totalRecord);
	      List<ProdVO> prodList = dao.selectProdList(pagingVO);
	      pagingVO.setDataList(prodList);
	      return prodList;
	   }

	@Override
	public ServiceResult modifyProd(ProdVO prod) {
		ServiceResult result = null;
		if(dao.selectProd(prod.getProdId()) == null) {
			int rowcnt = dao.updateProd(prod);
			if(rowcnt > 0 ) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAILED;
			}
		}else if (ServiceResult.NOTEXIST.equals(result)) {
			throw new PKNotFoundException(prod.getProdId() + "해당 상품 없음.");
		}
		return result;
	}

	@Override
	public ProdVO retrieveProd(String prodId) {
		ProdVO prod = dao.selectProd(prodId);
		if(prod == null) {
			throw new PKNotFoundException(prodId+"에 해당하는 상품이 없음.");
		}else {
			return prod;
		}
	}

}
