package kr.or.ddit.enumpkg;

@FunctionalInterface // 이 어노테이션을 사용해야한다.
public interface RealOperator {
	public double operate(double leftOp, double rightOp) ;
}

//인터페이스에 하나의 행위메서드밖에 없으면 functional interface라고 부른다.
