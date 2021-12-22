package kr.or.ddit.info2.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode(of= {"divChild"})
@NoArgsConstructor
public class DivisionVO implements Serializable{
	public DivisionVO(String divChild, String divName) {
		super();
		this.divChild = divChild;
		this.divName = divName;
	}
	
	private String divCategory;
	private String divChild;
	
	@NotNull
	private String divName;
	private String divParent;
	
	private List<DivisionVO> divisionList;
}
