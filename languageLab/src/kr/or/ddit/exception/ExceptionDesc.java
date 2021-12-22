package kr.or.ddit.exception;

import java.sql.SQLException;

/**
 *	예외(Throwable)
 *	 에러(Error) : 개발자가 처리하지 않는, 시스템 fault
 *	 예외(Exception) : 코드상에서 처리가 가능한 예외
 *		checked exception(Exception) : 발생 가능한 코드는 반드시 예외 처리 코드의 블럭으로 처리하거나 throws로 제어전달.
 *				SQLException, IOException
 *		unchecked exception(RuntimeException) : 직접 처리하지 않더라도, 호출자에게(최종 JVM에게) 제어권이 전달.
 *				NullpointerException, IllegalArgumentException, ArithemticException
 *
 *	throws(호출자에게 예외 제어권 전달), try~catch~finally(직접 예외 처리)
 *
 *	custom exception 정의
 * : 정의 하고 싶은 예외의 종류에 따른 상위 결정.
 */
public class ExceptionDesc {
	public static void main(String[] args) {
		// 1. checked exception sample
//		try {
//			String retValue = target();
//			System.out.println(retValue);
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}
		try {
			String retValue2 = targetForUnchecked();
			System.out.println(retValue2);
		}catch (RuntimeException e) {
			System.out.println("ㅋㅋㄹㅃㅃ");
		}
		
		String retValue3 = target();
		System.out.println(retValue3);
	}
	
	private static String targetForUnchecked() /*throws RuntimeException*/ { // 모든 메서드는 throws RuntimeException가 있는것과 같다.
		if(1==1) {
			throw new NullPointerException("강제 발생 예외");
		}
		return "targerValueForUnchecked";
	}
	
	private static String target() {
		try {
			if(1==1) {
				throw new SQLException("강제 발생 예외");
			}
			return "targetValue";
		}catch (SQLException e) {
			System.err.println(e.getMessage());
//			throw e;
			throw new CustomException(e);
		}
	}
}
