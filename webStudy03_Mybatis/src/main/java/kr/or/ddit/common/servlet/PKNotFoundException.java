package kr.or.ddit.common.servlet;

/**
 *	Primary key로 조회한 데이터가 존재하지 않을때 발생하는 예외.
 */
public class PKNotFoundException extends RuntimeException {

	public PKNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PKNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public PKNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public PKNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public PKNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
