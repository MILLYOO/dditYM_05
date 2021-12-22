package kr.or.ddit.common.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class DocCheckVO implements Serializable{
	public String rowNum;
    public String chkNum;
    public String chkDate;
    public String chkYn;
    public Integer chkUnit;
}
