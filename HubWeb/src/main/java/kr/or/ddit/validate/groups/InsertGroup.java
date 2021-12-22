package kr.or.ddit.validate.groups;

import javax.validation.groups.Default;

// markup interface
// 기본 그룹과 insert그룹을 두 개가 같이 쓰일 수 없어 자바의 상속성 이용, Default extends해서 기본 그룹이면서 insert그룹으로 만듬
public interface InsertGroup extends Default {

}
