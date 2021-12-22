package kr.or.ddit.annotation;


import kr.or.ddit.annotation.stereotype.FirstAnnotation;
import kr.or.ddit.annotation.stereotype.MultiValueAnnotation;
import kr.or.ddit.annotation.stereotype.SingleValueAnnotation;

/**
 * Annotation 
 * : 사람과 시스템에게 일정 정보를 전달할 목적의 주석.
 * 
 * 
 * 	1. marker annotation
 *  2. single value
 */
@FirstAnnotation
@SingleValueAnnotation(value = "text")
//@MultiValueAnnotation(value="text", floatNumber=2.0f)
@MultiValueAnnotation(value="text")
public class AnnotationTracing {
	@FirstAnnotation
	String prop;
	
	public static void main(String[] args) {
		FirstAnnotation annotation = AnnotationTracing.class.getAnnotation(FirstAnnotation.class);
		System.out.println(annotation);
		SingleValueAnnotation annotation2 = AnnotationTracing.class.getAnnotation(SingleValueAnnotation.class);
		System.out.println(annotation2);
	}
}
