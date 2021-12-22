package tm.comm.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

/**
 * FileUpload API를 사용하는 HttpServletRequestWrapper 클래스
 * (HttpServletRequest에 기반한 API를 사용하면서 멀티파트처리 기능이 추가되었음.)
 */
public class FileUploadRequestWrapper extends HttpServletRequestWrapper {

	private static Logger logger = Logger.getLogger(FileUploadRequestWrapper.class);	// 로거 생성

    public static final String UPLOAD_DIRECTORY = "d:/A_TeachingMaterial/4.MiddleProject/workspace/Travel_Maker/WebContent/imageFile";// 업로드 경로 설정
    private boolean isMultipart = false;												// 멀티파트여부

    private Map<String, String[]> parameterMap;	// 폼필드(파라미터) 데이터를 담기 위한 맵
    private Map<String, Object[]> fileItemMap;	// fileItem객체를 담기위한 맵

    
    
    
    
    /**
     * 생성자
     * @param request
     * @throws FileUploadException
     */
    public FileUploadRequestWrapper(HttpServletRequest request)
    throws FileUploadException{
        this(request, -1, -1, -1, null);
    }
    
    
    

    /**
     * 생성자
     * @param request 기본 요청 객체
     * @param memoryThreshold 메모리 임계크기(이 크기가 넘어가면 레파지토리 위치에 임시파일로 저장됨)
     * @param fileSizeMax 파일 1개당 최대크기
     * @param maxRequestSize 요청 파일 최대 크기
     * @param repositoryPath 임시파일 저장경로
     * @throws FileUploadException
     */
    public FileUploadRequestWrapper(HttpServletRequest request,
        int memoryThreshold, long fileSizeMax, long maxRequestSize, String repositoryPath) throws FileUploadException {
        super(request);

        parsing(request, memoryThreshold, fileSizeMax, maxRequestSize, repositoryPath);
    }
    
    
    
    
    

    /**
     * 멀티파트 데이터를 파싱하여 두개의 맵에 나누어 담는다.
     * @param request
     * @param memoryThreshold 메모리 임계크기(이 크기가 넘어가면 레파지토리 위치에 임시파일로 저장됨)
     * @param fileSizeMax 파일 1개당 최대 크기
     * @param maxRequestSize 요청 파일 최대 크기
     * @param repositoryPath 임시파일 저장경로
     * @throws FileUploadException
     */
    private void parsing(HttpServletRequest request,
        int memoryThreshold, long fileSizeMax, long maxRequestSize, String repositoryPath) throws FileUploadException {

        if (ServletFileUpload.isMultipartContent(request)) {
            isMultipart = true; // 멀티파트 일 때만 실행되는 메서드!!

            parameterMap = new HashMap<>();
            fileItemMap = new HashMap<>();

            DiskFileItemFactory factory = new DiskFileItemFactory();
            if (memoryThreshold != -1) {
            	factory.setSizeThreshold(memoryThreshold);
            }
            if (repositoryPath != null) {// 저장 경로가 설정되지 않았으면...
            	factory.setRepository(new File(repositoryPath));
            }else {
            	factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
            }

            ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
            servletFileUpload.setFileSizeMax(fileSizeMax);
            servletFileUpload.setSizeMax(maxRequestSize);

            List<FileItem> list = servletFileUpload.parseRequest(request);
            for (int i = 0 ; i < list.size() ; i++) {
                FileItem fileItem = (FileItem) list.get(i);
                String name = fileItem.getFieldName(); // 필드명 가져오기 (태그 name)

                if (fileItem.isFormField()) { // 폼필드인 경우...
                    String value = "";
					try {
						// 폼필드의 값이 한글인 경우에는 해당 문자열을 적절히 변환을 해주어야 한다.
						//value = new String(fileItem.getString().getBytes("8859_1"), "UTF-8");
						value = fileItem.getString("UTF-8");
						logger.info(name +  " : " + value);
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					//파라미터에 동일한 이름으로 여러개가 올 수 있어서 배열로 매핑하고 있음 --> input name이 동일 할 수 있음
                    String[] values = (String[]) parameterMap.get(name);
                    if (values == null) { // 처음 만드는 경우...
                        values = new String[] { value };
                    } else { // 기존에 이미 존재하는 경우... --> 전에 저장된 배열이 있다면 (for문 돌고 있으니까)
                        String[] tempValues = new String[values.length + 1];		//기존 배열 복사해서 인덱스 한 칸 추가(새 데이터 저장 할 장소) --> 근데 리스트 쓰면 이 작업 안해도 될 듯
                        System.arraycopy(values, 0, tempValues, 0, values.length);	//기존 값 넣고
                        tempValues[tempValues.length - 1] = value;					//새로운 데이터 맨 끝에 저장
                        values = tempValues;
                    }	
                    			//체크박스 같은걸로 입력값을 n개 받으면, name에는 input태그의 name이 저장되고.. hobby food 이런거..
                    			//values는 배열! 배열 0번 자리에 산책, 1번 자리에 독서.. 이런식으로 중복선택일 경우 n개 저장 하는 거
                    			//맵에 키로 name, 값으로 values(배열) 저장 
                    parameterMap.put(name, values);

                } else { // 파일인 경우... --> fileItem.isFormField()의 결과

                	if(fileItem.getSize() > 0) { // 파일이 존재하는 경우...

                		//파일이니까 object 타입 배열
                		Object[] values = (Object[]) fileItemMap.get(name);
                        if (values == null) { // 처음 만드는 경우...
                            values = new Object[] { fileItem };
                          //기존에 이미 존재하는 경우... --> input name이 동일 할 경우
                          //input type=file name=myfile
                          //input type=file name=myfile 이런식으로 같은 name의 파일 input을 여러개 쓸 경우 배열로 저장
                        } else { 
                        	Object[] tempValues = new Object[values.length + 1];
                            System.arraycopy(values, 0, tempValues, 0, values.length);
                            tempValues[tempValues.length - 1] = fileItem;
                            values = tempValues;
                        }		//맵에 키로 name, 값으로 values(배열) 저장 
                        fileItemMap.put(name, values);
                	}
                }
            }
            //addTo(); // 현재 객체를 속성값으로 설정한다.(멀티파트 여부 체크를 위해 설정함)
        }
    }
    
    
    
    
    

    /**
     * 멀티파트 컨텐츠인지 확인하기 위한 메서드
     * @return 멀티파트이면 true, 아니면 false
     */
    public boolean isMultipartContent() {
        return this.isMultipart;
    }
    

    /**
     * 파라미터 정보 가져오기
     */
    public String getParameter(String name) {
        if (this.isMultipart) {
            String[] values = (String[])parameterMap.get(name);	//내가 위에서 구현한 메서드
            if (values == null) return null;
            return values[0];
        } else
            return super.getParameter(name);	//아무것도 안하는 메서드 --> 기존 request객체 사용
    }

    public String[] getParameterValues(String name) {
        if (this.isMultipart)
            return (String[])parameterMap.get(name);
        else
            return super.getParameterValues(name);
    }

    public Enumeration<String> getParameterNames() {
        if (this.isMultipart) {
            return new Enumeration<String>() {
                Iterator<String> iter = parameterMap.keySet().iterator();

                public boolean hasMoreElements() {
                    return iter.hasNext();
                }
                public String nextElement() {
                    return iter.next();
                }
            };
        } else {
            return super.getParameterNames();
        }
    }

    public Map<String, String[]> getParameterMap() {
        if (this.isMultipart)
            return parameterMap;
        else
            return super.getParameterMap();
    }

    public Map<String, Object[]> getFileItemMap() {
    	 if (this.isMultipart)
             return fileItemMap;
         else
             return null;
    }

    
    //파일만 꺼내는 메서드
    public FileItem[] getFileItems(String name) {
        if (this.isMultipart)
            return (FileItem[]) fileItemMap.get(name);
        else
            return null;
    }

    /**
     * fileItemMap에 존재하는 FileItem을 삭제한다.
     */
    public void delete() {
        if (this.isMultipart) {
            Iterator<Object[]> fileItemIter = fileItemMap.values().iterator();
            while( fileItemIter.hasNext()) {
                FileItem[] fileItemArr = (FileItem[])fileItemIter.next();
                for(FileItem fileItem : fileItemArr) {
                	fileItem.delete(); // 임시저장 데이터 삭제
                }
            }
        }
    }

}
