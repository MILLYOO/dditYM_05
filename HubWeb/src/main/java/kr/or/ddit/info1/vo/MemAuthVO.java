package kr.or.ddit.info1.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import kr.or.ddit.validate.groups.UpdateGroup;
import lombok.Data;

/**
 * 회원의 권한에 관한 VO
 */
@Data
public class MemAuthVO implements Serializable{
	
	@NotNull(groups=UpdateGroup.class)
	private Integer authNum;
	@NotNull(groups=UpdateGroup.class)
	private Integer empCode;
	@NotBlank
	private String authName;
	@NotBlank(groups=UpdateGroup.class)
	private String authUse;
	@NotBlank
	private String authKname;
}
