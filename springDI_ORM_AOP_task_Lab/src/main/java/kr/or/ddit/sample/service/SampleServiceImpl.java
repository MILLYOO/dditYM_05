package kr.or.ddit.sample.service;

import java.util.Calendar;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import kr.or.ddit.sample.dao.SampleDAOFactory;
import kr.or.ddit.sample.dao.SampleDAO_Mysql;
import kr.or.ddit.sample.dao.SampleDAO_Oracle;
import kr.or.ddit.sample.dao.SampleDAO;

@Service
public class SampleServiceImpl implements SampleService{
//	1. new 키워드로 의존객체의 인스턴스를 직접 생성. 결합력 최상
//		sampleDAO dao = new SampleDAO_Oracle();
//		sampleDAO dao = new SampleDAO_Mysql();
//	2. Factory object pattern
//		sampleDAO dao = SampleDAOFactory.getSampleDAO();
//	3. Strategy pattern(전략패턴) -> DI(Dependency Injection)
//		전략패턴은 전략의 주입자는 전략의 주입이 무조건 되어야 한다.
//	4. DI 컨테이너 활용 - 주입자가 밖으로 밖으로 나감	

	// 마지막에 만들었던 이 과정을 새로 만들어보기
	// DI컨테이너와 웹을 접목시킬꺼임. 기존의 프레임워크를 다 버리고 시작함 그니까 다시 한번 훑어보기.
	private SampleDAO dao;
	
	public SampleServiceImpl() {
		System.out.println(getClass().getSimpleName()+" 객체 생성, 기본 생성자");
	}
	
	// 어노테이션 조건에 맞는것이 있으면 자동적으로 생성자에 주입이 된다.
//	@Autowired
	public SampleServiceImpl(SampleDAO dao) {
	super();
	this.dao = dao;
	System.out.println(String.format("%s 객체 생성, %s 주입", getClass().getSimpleName(), dao.getClass().getSimpleName()));
	}
	
//	@Autowired
	// 반드시 이것을 한번은 호출해야한다. 반드시 인젝션이 되야함.
	@Required
//	@Resource(name="sampleDAO_Oracle") // coc에 따른 이름 사용, Autowired는 단 하나의 구현체만 있을때 사용, 두개 이상일 경우에는 Resource
	@Inject
	public void setDaaaa(SampleDAO dao) {
		this.dao = dao;
		System.out.printf("%s setter injection \n", dao.getClass().getSimpleName());
	}
	
	public void init() {
		System.out.println(getClass().getSimpleName()+" 초기화 종료");
	}
	
	public void destroy(){
		System.out.println(getClass().getSimpleName()+" 소멸");
	}
	
	@Override
	public StringBuffer retrieveSampleData(String pk) {
		String rawData = dao.selectSampleData(pk);
		StringBuffer info = new StringBuffer(rawData);
		info.append(String.format(", 조회된 시점 : %tc", Calendar.getInstance()));
		return info;
	}
	

}
