package kr.or.ddit.sample.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import kr.or.ddit.sample.dao.SampleDAO;
import kr.or.ddit.sample.dao.SampleDAO_Mysql;
import kr.or.ddit.sample.dao.SampleDAO_Oracle;
import kr.or.ddit.sample.service.SampleService;
import kr.or.ddit.sample.service.SampleServiceImpl;

@Configuration
@Lazy
public class JavaConfiguration {
	
	// Bean -> xml의 bean element를 의미함
	@Bean(initMethod="init", destroyMethod="destroy")
	public SampleDAO_Oracle sampleDAO_Oracle() {
		return new SampleDAO_Oracle();
	}
	
	@Bean
	public SampleDAO_Mysql sampleDAO_Mysql() {
		return new SampleDAO_Mysql();
	}
	
	@Bean
	public SampleService sampleService1() {
		return new SampleServiceImpl(sampleDAO_Oracle());
	}
	
//	xml에서는 id를 다 줬는데 여기서는 그러지 않는 이유 - 메서드의 이름 자체가 id가 됨
//	@Bean("id")로 줄 경우 수동으로 이름 매핑 가능
	@Bean("sampleService2")
	@Scope("prototype")
	public SampleService sampleService2(SampleDAO_Mysql dao) {
		SampleServiceImpl service2 = new SampleServiceImpl();
		service2.setDaaaa(dao);
		return service2;
	}
}
