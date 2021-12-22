package kr.or.ddit.board.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.apache.ibatis.annotations.Update;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 공지사항VO
 */
@EqualsAndHashCode(of="notiNum")
@ToString
@Data
public class NoticeVO implements Serializable{
	
	int rnum;

	@NotNull(groups=UpdateGroup.class)
	private Integer notiNum;
	private String notiWriter;
	private String notiDate;
	@NotBlank(groups= {InsertGroup.class,UpdateGroup.class})
	private String notiTitle;
	@NotBlank(groups= {InsertGroup.class,UpdateGroup.class})
	private String notiContent;
	private Integer notiHits;
	
	// 첨부파일 목록
	private MultipartFile[] boFiles;
	public void setBoFiles(MultipartFile[] boFiles) {
		if(boFiles==null) return;
		this.boFiles = boFiles;
		this.attatchList = new ArrayList<>();
		for(MultipartFile tmp : boFiles) {
			if(tmp.isEmpty()) continue;
			attatchList.add(new AttatchVO(tmp));
		}
	}
	
	//	첨부파일 목록
	List<AttatchVO> attatchList;
	// insert all 사용 시 attno의 기준이되는 시작 번호
	private int startAttNo;
	// 삭제하려는 첨부파일 번호들
	private int[] delAttNos;
	
	private int attatchCount;
}
