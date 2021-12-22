package kr.or.ddit.stock.vo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import lombok.Data;

@Data
public class StockTakingVO implements Serializable{
	private Integer stotaNo;
	private String stotaDate;
	@NotNull(groups= {Default.class})
	private String prodCode;
	private String prodName;
	@NotNull(groups= {Default.class})
	private Integer stotaQty;
	@NotNull(groups= {Default.class})
	private String warName;
	private String stotaMemo;
	private String stotaYN;
	
	private String stotaSend;
	private String stotaRecv;
}
