package kr.or.ddit.mvc.annotation;


// immutable 객체로 Getter만 구현되며 값이 바뀌지 않는다.
public class RequestMappingCondition {
	private String uri;
	private RequestMethod method;
	
	public RequestMappingCondition(String uri, RequestMethod method) {
		super();
		this.uri = uri;
		this.method = method;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((method == null) ? 0 : method.hashCode());
		result = prime * result + ((uri == null) ? 0 : uri.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RequestMappingCondition other = (RequestMappingCondition) obj;
		if (method != other.method)
			return false;
		if (uri == null) {
			if (other.uri != null)
				return false;
		} else if (!uri.equals(other.uri))
			return false;
		return true;
	}

	public String geturi() {
		return uri;
	}

	public RequestMethod getMethod() {
		return method;
	}

	@Override
	public String toString() {
		return "RequestMappingCondition [uri=" + uri + ", method=" + method + "]";
	};
	
	
}
