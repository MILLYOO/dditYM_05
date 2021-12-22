package kr.or.ddit.common.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.sales.vo.SalesRankVO;

@Repository
public interface SalesEmpDAO {
	public List<SalesRankVO> selectSalesEmpMonth();
}
