package tm.comm.service;

import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.fileupload.FileItem;

import tm.comm.dao.IImgDao;
import tm.comm.dao.ImgDaoImpl;
import tm.comm.util.FileUploadRequestWrapper;
import tm.comm.vo.ImgVO;

public class ImgServiceImple implements IImgService{
	
	private static ImgServiceImple service;
	private IImgDao dao;
	
	private ImgServiceImple() {
		dao = ImgDaoImpl.getInstance();
	}
	
	public static IImgService getInstacne() {
		
		if(service == null) {
			service = new ImgServiceImple();
		}
		return service;
	}
	
	
	//-----------------------------------------------

	@Override
	public ImgVO saveAtchFileList(Map<String, Object[]> fileItemMap) {
		
		ImgVO imgVO = new ImgVO();
		
		try {
			// 기본 파일정보 저장(VO에 atchFileId가 저장된다.)
			dao.insertAtchFile(imgVO);
			
			for(Object[] objArr : fileItemMap.values()) {
				for(Object obj : objArr) {
					
					FileItem item = (FileItem) obj;
					
					File uploadDir = new File(FileUploadRequestWrapper.UPLOAD_DIRECTORY);
					if(!uploadDir.exists()) {
						uploadDir.mkdir();
					}
					
					String orignFileName = 
							new File(item.getName()).getName();
					long fileSize = item.getSize(); // 파일 사이즈 가져오기
					String storeFileName = "";
					String filePath = "";
					File storeFile = null;
					
					do {
						// 저장 파일명 추출
						storeFileName = UUID.randomUUID()
								.toString().replace("-", "");
						filePath = FileUploadRequestWrapper.UPLOAD_DIRECTORY 
								+ File.separator
								+ storeFileName;
						storeFile = new File(filePath);
					}while(storeFile.exists()); // 파일명이 중복되지 않을때까지
					
					//확장자명 추출	
					String fileExtension = orignFileName
										.lastIndexOf(".") < 0 ?
										"" 
										: orignFileName.substring(
										  orignFileName
										  .lastIndexOf(".") + 1);
											//. 기준으로 substring 해서 확장자 떼어오기
					item.write(storeFile);// 파일 업로드
					
					//db에 파일 세부 정보 저장
					imgVO.setTm_bimgd_sname(storeFileName);
					imgVO.setTm_bimgd_size(fileSize);
					imgVO.setTm_bimgd_oname(orignFileName);
					imgVO.setTm_bimgd_course(filePath);
					imgVO.setTm_bimgd_extsn(fileExtension);
					
					dao.insertAtchFileDetail(imgVO);
					
					item.delete(); // 임시 업로드 파일 삭제하기
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return imgVO;
	}

	@Override
	public List<ImgVO> getAtchFileList(ImgVO imgVO) {
		
		List<ImgVO> atchFileList = null;
		
		try {
			atchFileList = dao.getAtchFileList(imgVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return atchFileList;
	}

	@Override
	public ImgVO getAtchFileDetail(ImgVO imgVO) {
		
		ImgVO fileVO = null;
		
		try {
			fileVO = dao.getAtchFileDetail(imgVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return fileVO;
	}



	
	
}
