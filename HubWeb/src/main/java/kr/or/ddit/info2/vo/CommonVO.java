package kr.or.ddit.info2.vo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * 공정,규격,단위,조정구분,품명계정코드 VO
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(of="commCode")
public class CommonVO implements Serializable{
	
	public CommonVO(String commCategory) {
		super();
		this.commCategory = commCategory;
	}
	
	private String commCategory;
	@NonNull
	private String commCode;
	@NotNull
	private String commName;
}
	