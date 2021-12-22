package kr.or.ddit.common.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.groups.Default;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchedulerVO implements Serializable{
	private Integer scNo;
	private String memId;
	
	@NotBlank(groups=Default.class)
	private String scTitle;
	@NotBlank(groups=Default.class)
	private String scStart;
	
	private String scEnd;
	
	private Integer groupId;
}
