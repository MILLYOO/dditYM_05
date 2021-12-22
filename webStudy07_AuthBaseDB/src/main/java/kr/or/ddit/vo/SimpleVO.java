package kr.or.ddit.vo;

import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class SimpleVO {
	@NotBlank
	private String prop1;
	@NotBlank
	private String prop2;
	@NotBlank
	private String prop3;
	
	List<SimpleVO> simpleList;
}
