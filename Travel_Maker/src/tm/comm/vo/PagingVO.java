package tm.comm.vo;

public class PagingVO {

	//내가 알아야 할 필수 정보					   --> 서블릿 파라미터 지정(getter/setter이름 다름 주의)
	private int pageCount = 10;			// 페이지 목록 게시되는 페이지 수 (초기값: 10) --> 페이지목록 : 1 2 3.. 10 
	private int countPerPage = 10;		// 한 페이지당 게시되는 게시물 건 수(초기값: 10) --> 서블릿에서 파라미터로 지정
	private int currentPageNo;			// 현재 페이지 번호 
	private int totalCount;				// 전체 게시물 건 수 
	
	//위의 정보를 바탕으로 계산하는 것들(getter)
	private int totalPageCount;			// 전체 페이지 수
	private int firstPageNo;			// 현재 페이지 목록의 첫 페이지 번호
	private int lastPageNo;				// 현재 페이지 목록의 마지막 페이지 번호
	private int firstRecNo;				// 첫번째 레코드 번호 --> 현재페이지 번호가 3이라면 31
	private int lastRecNo;				// 마지막 레코드 번호 --> 현재페이지 번호가 3이라면 40 --> 게시글 10개씩 보여준다면
	
	
	public int getCountPerPage() {
		return countPerPage;
	}

	public void setCountPerPage(int countPerPage) {
		this.countPerPage = countPerPage;
	}

	public int getPageSize() {
		return pageCount;
	}

	public void setPageSize(int pageSize) {
		this.pageCount = pageSize;
	}

	public int getCurrentPageNo() {
		return currentPageNo;
	}

	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalCount() {
		return totalCount;
	}


	// 전체 페이지 수
	public int getTotalPageCount() {
		totalPageCount = ((getTotalCount() - 1) / getCountPerPage()) + 1;
		return totalPageCount;
	}

	// 시작페이지 번호
	public int getFirstPageNo() {
		firstPageNo = ((getCurrentPageNo() - 1) / getPageSize()) * getPageSize() + 1;
		return firstPageNo;
	}
	
	// 마지막 페이지 번호
	public int getLastPageNo() {
		lastPageNo = getFirstPageNo() + getPageSize() - 1;
		if (lastPageNo > getTotalPageCount()) {
			lastPageNo = getTotalPageCount();
		}
		return lastPageNo;
	}
	
	// 첫번째 레코드 번호
	public int getFirstRecNo() {
		firstRecNo = (getCurrentPageNo() - 1) * getCountPerPage() + 1;
		return firstRecNo;
	}
	
	// 마지막 레코드 번호
	public int getLastRecNo() {
		lastRecNo = getCurrentPageNo() * getCountPerPage();
		return lastRecNo;
	}
}