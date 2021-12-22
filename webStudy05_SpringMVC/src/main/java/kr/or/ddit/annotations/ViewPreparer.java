package kr.or.ddit.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component // 이 어노테이션을 사용 하는 순간 상위컨테이너에 포함이된다.
public @interface ViewPreparer {

}
