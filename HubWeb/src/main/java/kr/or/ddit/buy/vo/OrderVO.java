package kr.or.ddit.buy.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.groups.Default;

import kr.or.ddit.validate.groups.DetailUpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 발주서 VO
 */
@Data
@EqualsAndHashCode(of="ordNum")
public class OrderVO implements Serializable{
	
	@NotBlank(groups= {DetailUpdateGroup.class})
	private String ordNum;
	private String ordDate; //DB에 저장되는 형태는 이 모양이지만 밑에서 년월일 다 쪼개서 검증했기 때문에 얜 검증x
	private Integer buyerCode;
	@NotBlank(groups= {Default.class, DetailUpdateGroup.class}) //UpdateGroup, InsertGroup은 Default를 상속받았어. Delete는 아님
	private String buyerName;
	private String deptName;
	private String empName;
	private String ordVat;
	private String ordResult;
	private String ordFinish;
	
	//년월일 쪼개려고
	@NotBlank(groups= {Default.class})
	private String buyYy;
	@NotBlank(groups= {Default.class})
	private String buyMm;
	@NotBlank(groups= {Default.class})
	private String buyDd;
	
	//발주서 적용 여부
	private String odAppYn;
}
