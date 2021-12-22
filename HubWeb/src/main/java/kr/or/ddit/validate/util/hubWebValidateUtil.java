package kr.or.ddit.validate.util;

import java.util.Map;

import kr.or.ddit.enumpkg.ServiceResult;

public interface hubWebValidateUtil {

	public boolean checkError();
	
	public void setResultMap(ServiceResult result);
	
	public Map<String, Object> getResultMap();
	
}
