package kr.or.ddit.info1.vo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import kr.or.ddit.validate.constraints.BlankPattern;
import kr.or.ddit.validate.groups.DeleteGroup;
import kr.or.ddit.validate.groups.DetailUpdateGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 프로젝트 VO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="projCode")
public class ProjectVO implements Serializable{
	@NotNull(groups= {UpdateGroup.class, DeleteGroup.class})
	private Integer projCode;
	@NotNull(groups= {Default.class, DeleteGroup.class})
	private String projName;
	@BlankPattern(placeholder="2000-01-01", regexp="\\d{4}/\\d{2}/\\d{2}", groups= {DetailUpdateGroup.class})
	private String projStart;
	@BlankPattern(placeholder="2000-01-01", regexp="\\d{4}/\\d{2}/\\d{2}", groups= {DetailUpdateGroup.class})
	private String projEnd;
	@NotNull(groups= {Default.class})
	private String projSort;
	private String projPurpose;
	private String projContent;
	private Integer projBudget;
	public void setProjBudget(String projBudget) {
		if(projBudget==null || projBudget.isEmpty()) return;
		this.projBudget = Integer.parseInt(projBudget.replace(",", ""));
	}
	private String empName;
	private String deptName;
	@NotNull(groups= {Default.class})
	private String projUse;
	
}
