package kr.or.ddit.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HubSearchVO {
	//날짜
    public String dateStart;
    public String dateEnd;
    
    //품목
    public String rawsName;
    public String prodName;
    public String prodCode;
    public String rawsCode;
    public String rawsUse;
    
    //사원
    public String empName;
    public Integer empCode;
    //거래처
    public Integer buyerCode;
    public String buyerName;
    public String buyerUse;
    public String buyerCeo;
    public String buyerRegnumber;
    //프로젝트
    public Integer projCode;
    public String projName;
    public String projSort;
    public String projUse;
    //부서
    public Integer deptCode;
    public String deptName;
    //창고
    public Integer warCode;
    public String warName;
    public String warUse;
    public String warAdd1;
    public String outwarName; //출고창고 
    public String inwarName; //입고창고
    //규격
    public String gcommName;
    //단위
    public String ucommName;
    //공정
    public String kcommName;
    //조정구분 - j
    public String jcommName;
    //품목계정 - i
    public String icodeName;
    public String icodeNameProduce;//생산시 제품,반제품으로 제한하기 위함
    //분류
    public Integer lagDiv;
    public Integer midDiv;
    public Integer smlDiv;
    //마감여부
    public String finishYN;
    //견적서 마감여부
    public String estFinish;
    //수주서 마감여부
    public String orbFinish;
    // 출고지시서
    public String reoFinish;
    // 출골처리서
    public String relFinish;
    
    // 생산지시서번호
    public String instNum;
    // 생산지시서 마감여부
    public String instFinish;
    // 자재출고서 생산지시적용날짜검색
    public String piStartDate;
    public String piEndDate;
    //사용여부
    public String useYN;
    //유형(전체,입고,출고)
    public String adjSort;
    //수주서번호
    public String orbNum;
    //생산지시서-수주서적용날짜검색
    public String obStartDate;
    public String obEndDate;
    //입고처리서-발주서적용날짜검색
    public String ioStartDate;
    public String ioEndDate;
    //매입마감서-입고처리서 적용 날짜 검색
    public String ipStartDate;
    public String ipEndDate;
    //발주서 번호
    public String ordNum;
    //입고처리서 번호
    public String incNum;
    
    //적용날짜검색
    public String obdateStart;
    public String obdateEnd;
    
    //매입순위표
    public String docDate;
}