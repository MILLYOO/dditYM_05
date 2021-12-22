package kr.or.ddit.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import lombok.Data;

@Data
public class ReplyVO {
	@NotBlank(groups=UpdateGroup.class)
	private Integer repNo;
	
	@NotBlank
	private Integer boNo;
	private String repContent;
	
	@NotNull
	private String repWriter;
	private String repMail;
	
	@NotNull
	private String repPass;
	
	@Pattern(regexp="\\d{4}-\\d{2}-\\d{2}")
	private String repDate;
}
