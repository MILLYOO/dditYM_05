package kr.or.ddit.generic;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class GenericTest {
	public static class Box<T,E> {
	    private T object;
	    private List<E> contents = new ArrayList<>(); // 1.7이후에 나온 다이아몬드 문법 <> 안의 엘리먼트 속성을 지우면 앞에 타입을 따라감.
//	    public static T prop; // static으로 생성되어 있는것은 객체가 생성이 되기전에 메모리에 접근을 하는데 아직 객체 자체가 생성이 되어있지 않아 T의 타입이 적용되어 있지 않기 때문에 오류가 발생한다.

	    public void set(T object) { this.object = object; }
	    public T get() { return object; }
	    public void add(E element) {
	    	contents.add(element);
	    }
	    
	    public E getFromList(int index) {
	    	return contents.get(index);
	    }
	    
	    // 메소드가 호출되며 파라미터가 설정될때 P의 타입이 결정됨
	    public static <P> void method(P param) { // 파라미터의 타입을 고정시키지 않기 위해 사용
	    	System.out.println(param.getClass());
	    }
	    
	    public static <C extends Collection> int estimateSize(C param){ // UpperBound - Collection의 자식들만 C를 구현 할 수 있다.
	    	return param.size(); 
	    	
	    	// param.이후 코드어시스트를 받아보면 메소드를 통해 컴파일 되기전에는 Object를 가지고 있다는것을 알 수 있음
	    }
	}
	
	public static void main(String[] args) {
//		Box box = new Box();
//		box.set(new Date());
//		Object obj = box.get();
//		if(obj instanceof Date) {
//			Date date = (Date) obj;
//		}
		Box<Date, Calendar> box2 = new Box<>();
		box2.set(new Date());
		Date date2 = box2.get();
		box2.add(Calendar.getInstance());
		Calendar cal = box2.getFromList(0);
		
		Box.method("text");
		Box.method(4);
		
//		Box.estimateSize(new Date());// Date는 Collection의 자식이 아니기때문에 구현을 할 수 없다.
		Box.estimateSize(new ArrayList<>());
		Box.estimateSize(new HashSet<>());
	}
}
