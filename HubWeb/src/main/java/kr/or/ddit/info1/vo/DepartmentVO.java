package kr.or.ddit.info1.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import kr.or.ddit.validate.groups.DeleteGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 부서 VO
 */
@Data
@ToString
@EqualsAndHashCode(of= {"deptCode"})
public class DepartmentVO implements Serializable{
	@NotNull(message="부서 코드 누락", groups= {DeleteGroup.class, UpdateGroup.class})
	private Integer deptCode;
	@NotBlank(message="부서명 누락",groups= {Default.class})
	private String deptName;
	private Integer deptCount;
}
