package kr.or.ddit.info1.buyermanage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.info1.vo.BuyerVO;

@Repository
public interface BuyerManageDAO {
	//거래처 리스트 조회
	public List<BuyerVO> selectBuyerList(@Param("hubSearchVO")HubSearchVO hubSearch);
	
	//거래처 등록
	public int insertBuyer(BuyerVO buyer);
	
	//거래처 수정
	public int updateBuyer(BuyerVO buyer);
	
	//거래처 디테일 수정
	public int updateBuyerDetail(BuyerVO buyer);
	
	//거래처 삭제
	public int deleteBuyer(BuyerVO buyer);
}
