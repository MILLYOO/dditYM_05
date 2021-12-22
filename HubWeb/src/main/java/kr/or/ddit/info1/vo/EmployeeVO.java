package kr.or.ddit.info1.vo;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import kr.or.ddit.validate.constraints.BlankPattern;
import kr.or.ddit.validate.groups.DeleteGroup;
import kr.or.ddit.validate.groups.DetailUpdateGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import lombok.Data;

/**
 * 회원 권한 데이터 관리
 */
@Data
public class EmployeeVO implements Serializable{
	@NotNull(groups= {UpdateGroup.class, DeleteGroup.class})
	private Integer empCode;
	private Integer deptCode;
	private String empDate;
	private String empName;
	@BlankPattern(placeholder="910101", regexp="\\d{6}", groups= {DetailUpdateGroup.class})
	private Integer empReg1;
	@BlankPattern(placeholder="0111111", regexp="\\d{7}", groups= {DetailUpdateGroup.class})
	private Integer empReg2;
	private String empAdd1;
	private String empAdd2;
	@BlankPattern(placeholder="00-0000-0000", regexp="\\d{2,3}-\\d{3,4}-\\d{4}", groups= {DetailUpdateGroup.class})
	private String empLine;
	@BlankPattern(placeholder="010-0000-0000", regexp="\\d{2,3}-\\d{3,4}-\\d{4}", groups= {UpdateGroup.class,DetailUpdateGroup.class})
	private String empTel;
	@Email(groups= {DetailUpdateGroup.class})
	private String empMail;
	private String empRank;
	private String empFire;
	private String empUse;
	private String deptName;
	
}