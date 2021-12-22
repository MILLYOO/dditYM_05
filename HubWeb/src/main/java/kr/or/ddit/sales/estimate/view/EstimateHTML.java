package kr.or.ddit.sales.estimate.view;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import kr.or.ddit.common.util.ConvertHanguel;
import kr.or.ddit.info1.vo.BuyerVO;
import kr.or.ddit.sales.vo.EstimateProdVO;
import kr.or.ddit.sales.vo.EstimateVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EstimateHTML {

	StringBuffer makeHtml(EstimateVO estimateVO, List<EstimateProdVO> estimateProdList, List<BuyerVO> buyerList) {
		//거래처 정보 가져오기
		BuyerVO buyerVO = buyerList.get(0);

		// 날짜정보 만들기
		Date nowDate = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
		String strNowDate = simpleDateFormat.format(nowDate);
		String year = strNowDate.substring(0, 4);
		String month = strNowDate.substring(6, 8);
		String date = strNowDate.substring(10, 12);
		
		// 거래처 전화번호 만들기
		String empLine = null;
		String empLine1 = null;
		String empLine2 = null;
		String empLine3 = null;
		if(buyerVO.getBuyerTel() != null) {
			empLine = (buyerVO.getBuyerTel()).toString();
			empLine1 = empLine.substring(0, 3);
			empLine2 = empLine.substring(4, 7);
			empLine3 = empLine.substring(8, 12);
		}
		
		// 금액 한글로 만들기
		Integer epQty = 0; // 금액의 총 수량
		int epSpSum = 0; // 금액의 총 공급가액
		int epVatSum = 0; // 금액의 총 부가세
		int epScostSum = 0; // 금액의 총 합계액
		for (EstimateProdVO estimateProdVO : estimateProdList) {
			epQty += estimateProdVO.getEpQty();
			epSpSum += estimateProdVO.getEpSp();
			epVatSum += estimateProdVO.getEpVat();
			epScostSum += estimateProdVO.getEpScost();
		}
		String epScostSumString = Integer.toString(epScostSum);
		String convertedEpScost = ConvertHanguel.convert(epScostSumString); // 한글로 변환된 최종값
		
		//숫자 포멧 지정
		DecimalFormat formatter = new DecimalFormat("###,###");

		StringBuffer sb = new StringBuffer();

		sb.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">\r\n");
		sb.append("<html>\r\n");
		sb.append("  <head>\r\n");
		sb.append("      <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\r\n");
		sb.append(
				"      <meta name=\"generator\" content=\"PhpSpreadsheet, https://github.com/PHPOffice/PhpSpreadsheet\">\r\n");
		sb.append("      <title>Untitled Spreadsheet</title>\r\n");
		sb.append("      <meta name=\"author\" content=\"Unknown Creator\" />\r\n");
		sb.append("      <meta name=\"title\" content=\"Untitled Spreadsheet\" />\r\n");
		sb.append("      <meta name=\"company\" content=\"Microsoft Corporation\" />\r\n");
		sb.append("    <style type=\"text/css\">\r\n");
		sb.append(
				"      html { font-family:Calibri, Arial, Helvetica, sans-serif; font-size:11pt; background-color:white }\r\n");
		sb.append(
				"      a.comment-indicator:hover );   sb.append( div.comment { background:#ffd; position:absolute; display:block; border:1px solid black; padding:0.5em }\r\n");
		sb.append(
				"      a.comment-indicator { background:red; display:inline-block; border:1px solid black; width:0.5em; height:0.5em }\r\n");
		sb.append("      div.comment { display:none }\r\n");
		sb.append("      table { border-collapse:collapse; page-break-after:always }\r\n");
		sb.append("      .gridlines td { border:1px dotted black }\r\n");
		sb.append("      .gridlines th { border:1px dotted black }\r\n");
		sb.append("      .b { text-align:center }\r\n");
		sb.append("      .e { text-align:center }\r\n");
		sb.append("      .f { text-align:right }\r\n");
		sb.append("      .inlineStr { text-align:left }\r\n");
		sb.append("      .n { text-align:right }\r\n");
		sb.append("      .s { text-align:left }\r\n");
		sb.append(
				"      td.style0 { vertical-align:bottom; border-bottom:none #000000; border-top:none #000000; border-left:none #000000; border-right:none #000000; color:#000000; font-family:'Arial'; font-size:10pt; background-color:white }\r\n");
		sb.append(
				"      th.style0 { vertical-align:bottom; border-bottom:none #000000; border-top:none #000000; border-left:none #000000; border-right:none #000000; color:#000000; font-family:'Arial'; font-size:10pt; background-color:white }\r\n");
		sb.append(
				"      td.style1 { vertical-align:middle; text-align:center; border-bottom:2px solid #000000 !important; border-top:2px solid #000000 !important; border-left:2px solid #000000 !important; border-right:2px solid #000000 !important; font-weight:bold; color:#000000; font-family:'돋움체'; font-size:25pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      th.style1 { vertical-align:middle; text-align:center; border-bottom:2px solid #000000 !important; border-top:2px solid #000000 !important; border-left:2px solid #000000 !important; border-right:2px solid #000000 !important; font-weight:bold; color:#000000; font-family:'돋움체'; font-size:25pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      td.style2 { vertical-align:middle; text-align:center; border-bottom:1px solid #000000 !important; border-top:2px solid #000000 !important; border-left:2px solid #000000 !important; border-right:1px solid #000000 !important; font-weight:bold; color:#000000; font-family:'돋움체'; font-size:11pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      th.style2 { vertical-align:middle; text-align:center; border-bottom:1px solid #000000 !important; border-top:2px solid #000000 !important; border-left:2px solid #000000 !important; border-right:1px solid #000000 !important; font-weight:bold; color:#000000; font-family:'돋움체'; font-size:11pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      td.style3 { vertical-align:middle; text-align:right; padding-right:0px; border-bottom:1px solid #000000 !important; border-top:2px solid #000000 !important; border-left:1px solid #000000 !important; border-right:none #000000; font-weight:bold; color:#000000; font-family:'돋움체'; font-size:11pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      th.style3 { vertical-align:middle; text-align:right; padding-right:0px; border-bottom:1px solid #000000 !important; border-top:2px solid #000000 !important; border-left:1px solid #000000 !important; border-right:none #000000; font-weight:bold; color:#000000; font-family:'돋움체'; font-size:11pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      td.style4 { vertical-align:middle; text-align:right; padding-right:0px; border-bottom:1px solid #000000 !important; border-top:2px solid #000000 !important; border-left:none #000000; border-right:none #000000; color:#000000; font-family:'바탕체'; font-size:11pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      th.style4 { vertical-align:middle; text-align:right; padding-right:0px; border-bottom:1px solid #000000 !important; border-top:2px solid #000000 !important; border-left:none #000000; border-right:none #000000; color:#000000; font-family:'바탕체'; font-size:11pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      td.style5 { vertical-align:middle; text-align:center; border-bottom:1px solid #000000 !important; border-top:2px solid #000000 !important; border-left:none #000000; border-right:none #000000; font-weight:bold; color:#000000; font-family:'돋움체'; font-size:11pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      th.style5 { vertical-align:middle; text-align:center; border-bottom:1px solid #000000 !important; border-top:2px solid #000000 !important; border-left:none #000000; border-right:none #000000; font-weight:bold; color:#000000; font-family:'돋움체'; font-size:11pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      td.style6 { vertical-align:middle; text-align:center; border-bottom:1px solid #000000 !important; border-top:2px solid #000000 !important; border-left:1px solid #000000 !important; border-right:1px solid #000000 !important; font-weight:bold; color:#000000; font-family:'돋움체'; font-size:11pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      th.style6 { vertical-align:middle; text-align:center; border-bottom:1px solid #000000 !important; border-top:2px solid #000000 !important; border-left:1px solid #000000 !important; border-right:1px solid #000000 !important; font-weight:bold; color:#000000; font-family:'돋움체'; font-size:11pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      td.style7 { vertical-align:middle; text-align:left; padding-left:0px; border-bottom:1px solid #000000 !important; border-top:2px solid #000000 !important; border-left:1px solid #000000 !important; border-right:2px solid #000000 !important; color:#000000; font-family:'바탕체'; font-size:11pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      th.style7 { vertical-align:middle; text-align:left; padding-left:0px; border-bottom:1px solid #000000 !important; border-top:2px solid #000000 !important; border-left:1px solid #000000 !important; border-right:2px solid #000000 !important; color:#000000; font-family:'바탕체'; font-size:11pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      td.style8 { vertical-align:middle; text-align:center; border-bottom:1px solid #000000 !important; border-top:1px solid #000000 !important; border-left:2px solid #000000 !important; border-right:1px solid #000000 !important; font-weight:bold; color:#000000; font-family:'돋움체'; font-size:11pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      th.style8 { vertical-align:middle; text-align:center; border-bottom:1px solid #000000 !important; border-top:1px solid #000000 !important; border-left:2px solid #000000 !important; border-right:1px solid #000000 !important; font-weight:bold; color:#000000; font-family:'돋움체'; font-size:11pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      td.style9 { vertical-align:middle; text-align:center; border-bottom:1px solid #000000 !important; border-top:1px solid #000000 !important; border-left:1px solid #000000 !important; border-right:1px solid #000000 !important; color:#000000; font-family:'바탕체'; font-size:11pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      th.style9 { vertical-align:middle; text-align:center; border-bottom:1px solid #000000 !important; border-top:1px solid #000000 !important; border-left:1px solid #000000 !important; border-right:1px solid #000000 !important; color:#000000; font-family:'바탕체'; font-size:11pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      td.style10 { vertical-align:middle; text-align:center; border-bottom:1px solid #000000 !important; border-top:1px solid #000000 !important; border-left:1px solid #000000 !important; border-right:1px solid #000000 !important; font-weight:bold; color:#000000; font-family:'돋움체'; font-size:11pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      th.style10 { vertical-align:middle; text-align:center; border-bottom:1px solid #000000 !important; border-top:1px solid #000000 !important; border-left:1px solid #000000 !important; border-right:1px solid #000000 !important; font-weight:bold; color:#000000; font-family:'돋움체'; font-size:11pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      td.style11 { vertical-align:middle; text-align:left; padding-left:0px; border-bottom:1px solid #000000 !important; border-top:1px solid #000000 !important; border-left:1px solid #000000 !important; border-right:2px solid #000000 !important; color:#000000; font-family:'바탕체'; font-size:11pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      th.style11 { vertical-align:middle; text-align:left; padding-left:0px; border-bottom:1px solid #000000 !important; border-top:1px solid #000000 !important; border-left:1px solid #000000 !important; border-right:2px solid #000000 !important; color:#000000; font-family:'바탕체'; font-size:11pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      td.style12 { vertical-align:middle; text-align:center; border-bottom:1px solid #000000 !important; border-top:1px solid #000000 !important; border-left:2px solid #000000 !important; border-right:1px solid #000000 !important; font-weight:bold; color:#000000; font-family:'바탕체'; font-size:16pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      th.style12 { vertical-align:middle; text-align:center; border-bottom:1px solid #000000 !important; border-top:1px solid #000000 !important; border-left:2px solid #000000 !important; border-right:1px solid #000000 !important; font-weight:bold; color:#000000; font-family:'바탕체'; font-size:16pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      td.style13 { vertical-align:middle; text-align:center; border-bottom:1px solid #000000 !important; border-top:1px solid #000000 !important; border-left:2px solid #000000 !important; border-right:1px solid #000000 !important; color:#000000; font-family:'바탕체'; font-size:11pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      th.style13 { vertical-align:middle; text-align:center; border-bottom:1px solid #000000 !important; border-top:1px solid #000000 !important; border-left:2px solid #000000 !important; border-right:1px solid #000000 !important; color:#000000; font-family:'바탕체'; font-size:11pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      td.style14 { vertical-align:middle; text-align:center; border-bottom:1px solid #000000 !important; border-top:1px solid #000000 !important; border-left:1px solid #000000 !important; border-right:none #000000; color:#000000; font-family:'바탕체'; font-size:11pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      th.style14 { vertical-align:middle; text-align:center; border-bottom:1px solid #000000 !important; border-top:1px solid #000000 !important; border-left:1px solid #000000 !important; border-right:none #000000; color:#000000; font-family:'바탕체'; font-size:11pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      td.style15 { vertical-align:middle; text-align:center; border-bottom:1px solid #000000 !important; border-top:1px solid #000000 !important; border-left:none #000000; border-right:none #000000; font-weight:bold; color:#000000; font-family:'돋움체'; font-size:11pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      th.style15 { vertical-align:middle; text-align:center; border-bottom:1px solid #000000 !important; border-top:1px solid #000000 !important; border-left:none #000000; border-right:none #000000; font-weight:bold; color:#000000; font-family:'돋움체'; font-size:11pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      td.style16 { vertical-align:middle; text-align:center; border-bottom:1px solid #000000 !important; border-top:1px solid #000000 !important; border-left:none #000000; border-right:none #000000; color:#000000; font-family:'바탕체'; font-size:11pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      th.style16 { vertical-align:middle; text-align:center; border-bottom:1px solid #000000 !important; border-top:1px solid #000000 !important; border-left:none #000000; border-right:none #000000; color:#000000; font-family:'바탕체'; font-size:11pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      td.style17 { vertical-align:middle; text-align:center; border-bottom:1px solid #000000 !important; border-top:none #000000; border-left:none #000000; border-right:none #000000; color:#000000; font-family:'바탕체'; font-size:11pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      th.style17 { vertical-align:middle; text-align:center; border-bottom:1px solid #000000 !important; border-top:none #000000; border-left:none #000000; border-right:none #000000; color:#000000; font-family:'바탕체'; font-size:11pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      td.style18 { vertical-align:middle; text-align:center; border-bottom:1px solid #000000 !important; border-top:none #000000; border-left:none #000000; border-right:none #000000; font-weight:bold; color:#000000; font-family:'돋움체'; font-size:11pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      th.style18 { vertical-align:middle; text-align:center; border-bottom:1px solid #000000 !important; border-top:none #000000; border-left:none #000000; border-right:none #000000; font-weight:bold; color:#000000; font-family:'돋움체'; font-size:11pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      td.style19 { vertical-align:middle; text-align:center; border-bottom:1px solid #000000 !important; border-top:none #000000; border-left:1px solid #000000 !important; border-right:1px solid #000000 !important; font-weight:bold; color:#000000; font-family:'돋움체'; font-size:11pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      th.style19 { vertical-align:middle; text-align:center; border-bottom:1px solid #000000 !important; border-top:none #000000; border-left:1px solid #000000 !important; border-right:1px solid #000000 !important; font-weight:bold; color:#000000; font-family:'돋움체'; font-size:11pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      td.style20 { vertical-align:middle; text-align:left; padding-left:0px; border-bottom:1px solid #000000 !important; border-top:1px solid #000000 !important; border-left:none #000000; border-right:none #000000; font-weight:bold; color:#000000; font-family:'돋움체'; font-size:11pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      th.style20 { vertical-align:middle; text-align:left; padding-left:0px; border-bottom:1px solid #000000 !important; border-top:1px solid #000000 !important; border-left:none #000000; border-right:none #000000; font-weight:bold; color:#000000; font-family:'돋움체'; font-size:11pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      td.style21 { vertical-align:middle; text-align:center; border-bottom:1px solid #000000 !important; border-top:1px solid #000000 !important; border-left:none #000000; border-right:2px solid #000000 !important; color:#000000; font-family:'바탕체'; font-size:11pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      th.style21 { vertical-align:middle; text-align:center; border-bottom:1px solid #000000 !important; border-top:1px solid #000000 !important; border-left:none #000000; border-right:2px solid #000000 !important; color:#000000; font-family:'바탕체'; font-size:11pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      td.style22 { vertical-align:middle; text-align:center; border-bottom:1px solid #000000 !important; border-top:1px solid #000000 !important; border-left:2px solid #000000 !important; border-right:1px solid #FFFFFF !important; font-weight:bold; color:#000000; font-family:'돋움체'; font-size:11pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      th.style22 { vertical-align:middle; text-align:center; border-bottom:1px solid #000000 !important; border-top:1px solid #000000 !important; border-left:2px solid #000000 !important; border-right:1px solid #FFFFFF !important; font-weight:bold; color:#000000; font-family:'돋움체'; font-size:11pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      td.style23 { vertical-align:middle; text-align:right; padding-right:0px; border-bottom:1px solid #000000 !important; border-top:1px solid #000000 !important; border-left:none #000000; border-right:none #000000; color:#000000; font-family:'바탕체'; font-size:11pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      th.style23 { vertical-align:middle; text-align:right; padding-right:0px; border-bottom:1px solid #000000 !important; border-top:1px solid #000000 !important; border-left:none #000000; border-right:none #000000; color:#000000; font-family:'바탕체'; font-size:11pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      td.style24 { vertical-align:middle; text-align:right; padding-right:0px; border-bottom:1px solid #000000 !important; border-top:1px solid #000000 !important; border-left:none #000000; border-right:none #000000; color:#000000; font-family:'바탕체'; font-size:11pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      th.style24 { vertical-align:middle; text-align:right; padding-right:0px; border-bottom:1px solid #000000 !important; border-top:1px solid #000000 !important; border-left:none #000000; border-right:none #000000; color:#000000; font-family:'바탕체'; font-size:11pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      td.style25 { vertical-align:middle; text-align:left; padding-left:0px; border-bottom:1px solid #000000 !important; border-top:1px solid #000000 !important; border-left:none #000000; border-right:2px solid #000000 !important; font-weight:bold; color:#000000; font-family:'돋움체'; font-size:11pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      th.style25 { vertical-align:middle; text-align:left; padding-left:0px; border-bottom:1px solid #000000 !important; border-top:1px solid #000000 !important; border-left:none #000000; border-right:2px solid #000000 !important; font-weight:bold; color:#000000; font-family:'돋움체'; font-size:11pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      td.style26 { vertical-align:middle; text-align:center; border-bottom:1px solid #000000 !important; border-top:1px solid #000000 !important; border-left:2px solid #000000 !important; border-right:none #000000; font-weight:bold; color:#000000; font-family:'돋움체'; font-size:11pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      th.style26 { vertical-align:middle; text-align:center; border-bottom:1px solid #000000 !important; border-top:1px solid #000000 !important; border-left:2px solid #000000 !important; border-right:none #000000; font-weight:bold; color:#000000; font-family:'돋움체'; font-size:11pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      td.style27 { vertical-align:middle; text-align:left; padding-left:0px; border-bottom:1px solid #000000 !important; border-top:1px solid #000000 !important; border-left:none #000000; border-right:2px solid #000000 !important; color:#000000; font-family:'돋움체'; font-size:8pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      th.style27 { vertical-align:middle; text-align:left; padding-left:0px; border-bottom:1px solid #000000 !important; border-top:1px solid #000000 !important; border-left:none #000000; border-right:2px solid #000000 !important; color:#000000; font-family:'돋움체'; font-size:8pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      td.style28 { vertical-align:middle; text-align:center; border-bottom:1px solid #000000 !important; border-top:1px solid #000000 !important; border-left:none #000000; border-right:none #000000; font-weight:bold; color:#000000; font-family:'돋움체'; font-size:11pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      th.style28 { vertical-align:middle; text-align:center; border-bottom:1px solid #000000 !important; border-top:1px solid #000000 !important; border-left:none #000000; border-right:none #000000; font-weight:bold; color:#000000; font-family:'돋움체'; font-size:11pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      td.style29 { vertical-align:middle; text-align:left; padding-left:0px; border-bottom:1px solid #000000 !important; border-top:1px solid #000000 !important; border-left:none #000000; border-right:none #000000; font-weight:bold; color:#000000; font-family:'돋움체'; font-size:11pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      th.style29 { vertical-align:middle; text-align:left; padding-left:0px; border-bottom:1px solid #000000 !important; border-top:1px solid #000000 !important; border-left:none #000000; border-right:none #000000; font-weight:bold; color:#000000; font-family:'돋움체'; font-size:11pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      td.style30 { vertical-align:middle; text-align:right; padding-right:0px; border-bottom:1px solid #000000 !important; border-top:1px solid #000000 !important; border-left:none #000000; border-right:none #000000; font-weight:bold; color:#000000; font-family:'돋움체'; font-size:11pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      th.style30 { vertical-align:middle; text-align:right; padding-right:0px; border-bottom:1px solid #000000 !important; border-top:1px solid #000000 !important; border-left:none #000000; border-right:none #000000; font-weight:bold; color:#000000; font-family:'돋움체'; font-size:11pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      td.style31 { vertical-align:middle; text-align:right; padding-right:0px; border-bottom:1px solid #000000 !important; border-top:1px solid #000000 !important; border-left:none #000000; border-right:2px solid #000000 !important; font-weight:bold; color:#000000; font-family:'돋움체'; font-size:11pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      th.style31 { vertical-align:middle; text-align:right; padding-right:0px; border-bottom:1px solid #000000 !important; border-top:1px solid #000000 !important; border-left:none #000000; border-right:2px solid #000000 !important; font-weight:bold; color:#000000; font-family:'돋움체'; font-size:11pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      td.style32 { vertical-align:middle; text-align:center; border-bottom:none #000000; border-top:none #000000; border-left:2px solid #000000 !important; border-right:none #000000; color:#000000; font-family:'바탕체'; font-size:10pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      th.style32 { vertical-align:middle; text-align:center; border-bottom:none #000000; border-top:none #000000; border-left:2px solid #000000 !important; border-right:none #000000; color:#000000; font-family:'바탕체'; font-size:10pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      td.style33 { vertical-align:middle; text-align:center; border-bottom:none #000000; border-top:none #000000; border-left:none #000000; border-right:none #000000; color:#000000; font-family:'바탕체'; font-size:10pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      th.style33 { vertical-align:middle; text-align:center; border-bottom:none #000000; border-top:none #000000; border-left:none #000000; border-right:none #000000; color:#000000; font-family:'바탕체'; font-size:10pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      td.style34 { vertical-align:middle; text-align:left; padding-left:0px; border-bottom:none #000000; border-top:none #000000; border-left:none #000000; border-right:none #000000; color:#000000; font-family:'바탕체'; font-size:10pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      th.style34 { vertical-align:middle; text-align:left; padding-left:0px; border-bottom:none #000000; border-top:none #000000; border-left:none #000000; border-right:none #000000; color:#000000; font-family:'바탕체'; font-size:10pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      td.style35 { vertical-align:middle; text-align:right; padding-right:0px; border-bottom:none #000000; border-top:none #000000; border-left:none #000000; border-right:none #000000; color:#000000; font-family:'바탕체'; font-size:10pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      th.style35 { vertical-align:middle; text-align:right; padding-right:0px; border-bottom:none #000000; border-top:none #000000; border-left:none #000000; border-right:none #000000; color:#000000; font-family:'바탕체'; font-size:10pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      td.style36 { vertical-align:middle; text-align:right; padding-right:0px; border-bottom:none #000000; border-top:none #000000; border-left:none #000000; border-right:none #000000; color:#000000; font-family:'바탕체'; font-size:10pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      th.style36 { vertical-align:middle; text-align:right; padding-right:0px; border-bottom:none #000000; border-top:none #000000; border-left:none #000000; border-right:none #000000; color:#000000; font-family:'바탕체'; font-size:10pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      td.style37 { vertical-align:middle; text-align:right; padding-right:0px; border-bottom:none #000000; border-top:none #000000; border-left:none #000000; border-right:none #000000; color:#000000; font-family:'바탕체'; font-size:10pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      th.style37 { vertical-align:middle; text-align:right; padding-right:0px; border-bottom:none #000000; border-top:none #000000; border-left:none #000000; border-right:none #000000; color:#000000; font-family:'바탕체'; font-size:10pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      td.style38 { vertical-align:middle; text-align:right; padding-right:0px; border-bottom:none #000000; border-top:none #000000; border-left:none #000000; border-right:none #000000; color:#000000; font-family:'바탕체'; font-size:10pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      th.style38 { vertical-align:middle; text-align:right; padding-right:0px; border-bottom:none #000000; border-top:none #000000; border-left:none #000000; border-right:none #000000; color:#000000; font-family:'바탕체'; font-size:10pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      td.style39 { vertical-align:middle; text-align:right; padding-right:0px; border-bottom:none #000000; border-top:none #000000; border-left:none #000000; border-right:2px solid #000000 !important; color:#000000; font-family:'바탕체'; font-size:10pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      th.style39 { vertical-align:middle; text-align:right; padding-right:0px; border-bottom:none #000000; border-top:none #000000; border-left:none #000000; border-right:2px solid #000000 !important; color:#000000; font-family:'바탕체'; font-size:10pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      td.style40 { vertical-align:middle; text-align:center; border-bottom:1px solid #000000 !important; border-top:1px solid #000000 !important; border-left:2px solid #000000 !important; border-right:none #000000; color:#000000; font-family:'돋움체'; font-size:10pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      th.style40 { vertical-align:middle; text-align:center; border-bottom:1px solid #000000 !important; border-top:1px solid #000000 !important; border-left:2px solid #000000 !important; border-right:none #000000; color:#000000; font-family:'돋움체'; font-size:10pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      td.style41 { vertical-align:middle; text-align:right; padding-right:0px; border-bottom:1px solid #000000 !important; border-top:1px solid #000000 !important; border-left:none #000000; border-right:none #000000; color:#000000; font-family:'바탕체'; font-size:10pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      th.style41 { vertical-align:middle; text-align:right; padding-right:0px; border-bottom:1px solid #000000 !important; border-top:1px solid #000000 !important; border-left:none #000000; border-right:none #000000; color:#000000; font-family:'바탕체'; font-size:10pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      td.style42 { vertical-align:middle; text-align:right; padding-right:0px; border-bottom:1px solid #000000 !important; border-top:1px solid #000000 !important; border-left:none #000000; border-right:none #000000; color:#000000; font-family:'돋움체'; font-size:10pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      th.style42 { vertical-align:middle; text-align:right; padding-right:0px; border-bottom:1px solid #000000 !important; border-top:1px solid #000000 !important; border-left:none #000000; border-right:none #000000; color:#000000; font-family:'돋움체'; font-size:10pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      td.style43 { vertical-align:middle; text-align:right; padding-right:0px; border-bottom:1px solid #000000 !important; border-top:1px solid #000000 !important; border-left:none #000000; border-right:none #000000; color:#000000; font-family:'바탕체'; font-size:10pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      th.style43 { vertical-align:middle; text-align:right; padding-right:0px; border-bottom:1px solid #000000 !important; border-top:1px solid #000000 !important; border-left:none #000000; border-right:none #000000; color:#000000; font-family:'바탕체'; font-size:10pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      td.style44 { vertical-align:middle; text-align:right; padding-right:0px; border-bottom:1px solid #000000 !important; border-top:1px solid #000000 !important; border-left:none #000000; border-right:2px solid #000000 !important; color:#000000; font-family:'바탕체'; font-size:10pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      th.style44 { vertical-align:middle; text-align:right; padding-right:0px; border-bottom:1px solid #000000 !important; border-top:1px solid #000000 !important; border-left:none #000000; border-right:2px solid #000000 !important; color:#000000; font-family:'바탕체'; font-size:10pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      td.style45 { vertical-align:middle; text-align:left; padding-left:0px; border-bottom:1px solid #000000 !important; border-top:1px solid #000000 !important; border-left:2px solid #000000 !important; border-right:2px solid #000000 !important; font-weight:bold; color:#000000; font-family:'돋움체'; font-size:12pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      th.style45 { vertical-align:middle; text-align:left; padding-left:0px; border-bottom:1px solid #000000 !important; border-top:1px solid #000000 !important; border-left:2px solid #000000 !important; border-right:2px solid #000000 !important; font-weight:bold; color:#000000; font-family:'돋움체'; font-size:12pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      td.style46 { vertical-align:middle; text-align:center; border-bottom:1px solid #000000 !important; border-top:1px solid #000000 !important; border-left:2px solid #000000 !important; border-right:none #000000; font-weight:bold; color:#000000; font-family:'돋움체'; font-size:12pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      th.style46 { vertical-align:middle; text-align:center; border-bottom:1px solid #000000 !important; border-top:1px solid #000000 !important; border-left:2px solid #000000 !important; border-right:none #000000; font-weight:bold; color:#000000; font-family:'돋움체'; font-size:12pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      td.style47 { vertical-align:middle; text-align:left; padding-left:0px; border-bottom:1px solid #000000 !important; border-top:1px solid #000000 !important; border-left:none #000000; border-right:1px solid #000000 !important; font-weight:bold; color:#000000; font-family:'바탕체'; font-size:12pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      th.style47 { vertical-align:middle; text-align:left; padding-left:0px; border-bottom:1px solid #000000 !important; border-top:1px solid #000000 !important; border-left:none #000000; border-right:1px solid #000000 !important; font-weight:bold; color:#000000; font-family:'바탕체'; font-size:12pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      td.style48 { vertical-align:middle; text-align:center; border-bottom:1px solid #000000 !important; border-top:1px solid #000000 !important; border-left:1px solid #000000 !important; border-right:none #000000; font-weight:bold; color:#000000; font-family:'돋움체'; font-size:12pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      th.style48 { vertical-align:middle; text-align:center; border-bottom:1px solid #000000 !important; border-top:1px solid #000000 !important; border-left:1px solid #000000 !important; border-right:none #000000; font-weight:bold; color:#000000; font-family:'돋움체'; font-size:12pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      td.style49 { vertical-align:middle; text-align:left; padding-left:0px; border-bottom:1px solid #000000 !important; border-top:1px solid #000000 !important; border-left:none #000000; border-right:2px solid #000000 !important; font-weight:bold; color:#000000; font-family:'바탕체'; font-size:12pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      th.style49 { vertical-align:middle; text-align:left; padding-left:0px; border-bottom:1px solid #000000 !important; border-top:1px solid #000000 !important; border-left:none #000000; border-right:2px solid #000000 !important; font-weight:bold; color:#000000; font-family:'바탕체'; font-size:12pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      td.style50 { vertical-align:middle; text-align:right; padding-right:0px; border-bottom:2px solid #000000 !important; border-top:1px solid #000000 !important; border-left:2px solid #000000 !important; border-right:none #000000; font-weight:bold; color:#000000; font-family:'바탕체'; font-size:12pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      th.style50 { vertical-align:middle; text-align:right; padding-right:0px; border-bottom:2px solid #000000 !important; border-top:1px solid #000000 !important; border-left:2px solid #000000 !important; border-right:none #000000; font-weight:bold; color:#000000; font-family:'바탕체'; font-size:12pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      td.style51 { vertical-align:middle; text-align:left; padding-left:0px; border-bottom:2px solid #000000 !important; border-top:1px solid #000000 !important; border-left:none #000000; border-right:2px solid #000000 !important; font-weight:bold; color:#000000; font-family:'바탕체'; font-size:12pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      th.style51 { vertical-align:middle; text-align:left; padding-left:0px; border-bottom:2px solid #000000 !important; border-top:1px solid #000000 !important; border-left:none #000000; border-right:2px solid #000000 !important; font-weight:bold; color:#000000; font-family:'바탕체'; font-size:12pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      td.style52 { vertical-align:middle; text-align:center; border-bottom:1px solid #000000 !important; border-top:1px solid #000000 !important; border-left:2px solid #000000 !important; border-right:1px solid #000000 !important; color:#000000; font-family:'바탕체'; font-size:11pt; background-color:#FFFFFF }\r\n");
		sb.append(
				"      th.style52 { vertical-align:middle; text-align:center; border-bottom:1px solid #000000 !important; border-top:1px solid #000000 !important; border-left:2px solid #000000 !important; border-right:1px solid #000000 !important; color:#000000; font-family:'바탕체'; font-size:11pt; background-color:#FFFFFF }\r\n");
		sb.append("      table.sheet0 col.col0 { width:32.53333296pt }\r\n");
		sb.append("      table.sheet0 col.col1 { width:56.93333268pt }\r\n");
		sb.append("      table.sheet0 col.col2 { width:23.72222195pt }\r\n");
		sb.append("      table.sheet0 col.col3 { width:28.46666634pt }\r\n");
		sb.append("      table.sheet0 col.col4 { width:21.01111087pt }\r\n");
		sb.append("      table.sheet0 col.col5 { width:28.46666634pt }\r\n");
		sb.append("      table.sheet0 col.col6 { width:9.48888878pt }\r\n");
		sb.append("      table.sheet0 col.col7 { width:28.46666634pt }\r\n");
		sb.append("      table.sheet0 col.col8 { width:33.21111073pt }\r\n");
		sb.append("      table.sheet0 col.col9 { width:52.18888829pt }\r\n");
		sb.append("      table.sheet0 col.col10 { width:37.27777735pt }\r\n");
		sb.append("      table.sheet0 col.col11 { width:61.67777707pt }\r\n");
		sb.append("      table.sheet0 col.col12 { width:33.21111073pt }\r\n");
		sb.append("      table.sheet0 col.col13 { width:53.54444383pt }\r\n");
		sb.append("      table.sheet0 tr { height:12.75pt }\r\n");
		sb.append("      table.sheet0 tr.row0 { height:49.6pt }\r\n");
		sb.append("      table.sheet0 tr.row1 { height:16.8pt }\r\n");
		sb.append("      table.sheet0 tr.row2 { height:17.65pt }\r\n");
		sb.append("      table.sheet0 tr.row3 { height:16.8pt }\r\n");
		sb.append("      table.sheet0 tr.row4 { height:15.95pt }\r\n");
		sb.append("      table.sheet0 tr.row5 { height:16.8pt }\r\n");
		sb.append("      table.sheet0 tr.row6 { height:16.8pt }\r\n");
		sb.append("      table.sheet0 tr.row7 { height:14.65pt }\r\n");
		sb.append("      table.sheet0 tr.row8 { height:25.7pt }\r\n");
		sb.append("      table.sheet0 tr.row9 { height:22.7pt }\r\n");
		sb.append("      table.sheet0 tr.row10 { height:12.75pt }\r\n");
		sb.append("      table.sheet0 tr.row11 { height:22.5pt }\r\n");
		sb.append("      table.sheet0 tr.row12 { height:21.75pt }\r\n");
		sb.append("      table.sheet0 tr.row13 { height:21.75pt }\r\n");
		sb.append("      table.sheet0 tr.row14 { height:21.75pt }\r\n");
		sb.append("      table.sheet0 tr.row15 { height:21.75pt }\r\n");
		sb.append("      table.sheet0 tr.row16 { height:21.75pt }\r\n");
		sb.append("      table.sheet0 tr.row17 { height:21.75pt }\r\n");
		sb.append("      table.sheet0 tr.row18 { height:21.75pt }\r\n");
		sb.append("      table.sheet0 tr.row19 { height:21.75pt }\r\n");
		sb.append("      table.sheet0 tr.row20 { height:21.75pt }\r\n");
		sb.append("      table.sheet0 tr.row21 { height:21.75pt }\r\n");
		sb.append("      table.sheet0 tr.row22 { height:21.75pt }\r\n");
		sb.append("      table.sheet0 tr.row23 { height:21.75pt }\r\n");
		sb.append("      table.sheet0 tr.row24 { height:21.65pt }\r\n");
		sb.append("      table.sheet0 tr.row25 { height:12.75pt }\r\n");
		sb.append("      table.sheet0 tr.row26 { height:21.7pt }\r\n");
		sb.append("      table.sheet0 tr.row27 { height:15pt }\r\n");
		sb.append("      table.sheet0 tr.row28 { height:14.95pt }\r\n");
		sb.append("      table.sheet0 tr.row29 { height:15.75pt }\r\n");
		sb.append("      table.sheet0 tr.row30 { height:16.5pt }\r\n");
		sb.append("      table.sheet0 tr.row31 { height:14.95pt }\r\n");
		sb.append("      table.sheet0 tr.row32 { height:15pt }\r\n");
		sb.append("      table.sheet0 tr.row33 { height:14.95pt }\r\n");
		sb.append("      table.sheet0 tr.row34 { height:15pt }\r\n");
		sb.append("      table.sheet0 tr.row35 { height:14.95pt }\r\n");
		sb.append("      table.sheet0 tr.row36 { height:44.2pt }\r\n");
		sb.append("    </style>\r\n");
		sb.append("  </head>\r\n");
		sb.append("\r\n");
		sb.append("  <body>\r\n");
		sb.append("<style>\r\n");
		sb.append(
				"@page { margin-left: 0.19791667163372in; margin-right: 0in; margin-top: 0.59375in; margin-bottom: 0in; }\r\n");
		sb.append(
				"body { margin-left: 0.19791667163372in; margin-right: 0in; margin-top: 0.59375in; margin-bottom: 0in; }\r\n");
		sb.append("</style>\r\n");
		sb.append(
				"    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" id=\"sheet0\" class=\"sheet0 gridlines\">\r\n");
		sb.append("        <col class=\"col0\">\r\n");
		sb.append("        <col class=\"col1\">\r\n");
		sb.append("        <col class=\"col2\">\r\n");
		sb.append("        <col class=\"col3\">\r\n");
		sb.append("        <col class=\"col4\">\r\n");
		sb.append("        <col class=\"col5\">\r\n");
		sb.append("        <col class=\"col6\">\r\n");
		sb.append("        <col class=\"col7\">\r\n");
		sb.append("        <col class=\"col8\">\r\n");
		sb.append("        <col class=\"col9\">\r\n");
		sb.append("        <col class=\"col10\">\r\n");
		sb.append("        <col class=\"col11\">\r\n");
		sb.append("        <col class=\"col12\">\r\n");
		sb.append("        <col class=\"col13\">\r\n");
		sb.append("        <tbody>\r\n");
		sb.append("          <tr class=\"row0\">\r\n");
		sb.append(
				"            <td class=\"column0 style1 s style1\" colspan=\"14\">견           적           서</td>\r\n");
		sb.append("          </tr>\r\n");
		sb.append("          <tr class=\"row1\">\r\n");
		sb.append("            <td class=\"column0 style2 s style2\" colspan=\"2\">견적일자</td>\r\n");
		sb.append("            <td class=\"column2 style3 s\">&nbsp;</td>\r\n");
		sb.append("            <td class=\"column3 style4 s\">");
		sb.append(year);
		sb.append("</td>\r\n");
		sb.append("            <td class=\"column4 style5 s\">년</td>\r\n");
		sb.append("            <td class=\"column5 style4 s\">");
		sb.append(month);
		sb.append("</td>\r\n");
		sb.append("            <td class=\"column6 style5 s\">월</td>\r\n");
		sb.append("            <td class=\"column7 style4 s\">");
		sb.append(date);
		sb.append("</td>\r\n");
		sb.append("            <td class=\"column8 style5 s\">일</td>\r\n");
		sb.append("            <td class=\"column9 style6 s\">사업장명</td>\r\n");
		sb.append("            <td class=\"column10 style7 s style7\" colspan=\"4\">");
		sb.append(estimateVO.getBuyerName());
		sb.append("</td>\r\n");
		sb.append("          </tr>\r\n");
		sb.append("          <tr class=\"row2\">\r\n");
		sb.append("            <td class=\"column0 style8 s style8\" colspan=\"2\">견적번호</td>\r\n");
		sb.append("            <td class=\"column2 style9 s style9\" colspan=\"7\">");
		sb.append(estimateVO.getEstCode());
		sb.append("</td>\r\n");
		sb.append("            <td class=\"column9 style10 s\">사업자No</td>\r\n");
		sb.append("            <td class=\"column10 style11 s style11\" colspan=\"4\">");
		sb.append(buyerVO.getBuyerRegnumber());
		sb.append("</td>\r\n");
		sb.append("          </tr>\r\n");
		sb.append("          <tr class=\"row3\">\r\n");
		sb.append("            <td class=\"column0 style12 s style12\" colspan=\"9\" rowspan=\"3\">");
		sb.append(buyerVO.getBuyerName());
		sb.append("</td>\r\n");
		sb.append("            <td class=\"column9 style10 s\">업    태</td>\r\n");
		sb.append("            <td class=\"column10 style11 s style11\" colspan=\"4\">");
		sb.append(buyerVO.getBuyerWorktype());
		sb.append("</td>\r\n");
		sb.append("          </tr>\r\n");
		sb.append("          <tr class=\"row4\">\r\n");
		sb.append("            <td class=\"column9 style10 s\">업    종</td>\r\n");
		sb.append("            <td class=\"column10 style11 s style11\" colspan=\"4\">");
		sb.append(buyerVO.getBuyerEvent());
		sb.append("</td>\r\n");
		sb.append("          </tr>\r\n");
		sb.append("          <tr class=\"row5\">\r\n");
		sb.append("            <td class=\"column9 style10 s\">TEL/FAX</td>\r\n");
		sb.append("            <td class=\"column10 style11 s style11\" colspan=\"4\">");
		sb.append(buyerVO.getBuyerTel());
		sb.append("/");
		sb.append(buyerVO.getBuyerFax());
		sb.append("</td>\r\n");
		sb.append("          </tr>\r\n");
		sb.append("          <tr class=\"row6\">\r\n");
		sb.append("            <td class=\"column0 style52 s style13\" rowspan=\"2\">담당자</td>\r\n");
		sb.append("            <td class=\"column1 style14 n\">");
		sb.append(empLine1);
		sb.append("</td>\r\n");
		sb.append("            <td class=\"column2 style15 s\">)</td>\r\n");
		sb.append("            <td class=\"column3 style16 n\">");
		sb.append(empLine2);
		sb.append("</td>\r\n");
		sb.append("            <td class=\"column4 style15 s\">-</td>\r\n");
		sb.append("            <td class=\"column5 style16 n\">");
		sb.append(empLine3);
		sb.append("</td>\r\n");
		sb.append("            <td class=\"column6 style15 s\">(</td>\r\n");
		sb.append("            <td class=\"column7 style16 s\">&nbsp;</td>\r\n");
		sb.append("            <td class=\"column8 style15 s\">)</td>\r\n");
		sb.append("            <td class=\"column9 style10 s\">소 재 지</td>\r\n");
		sb.append("            <td class=\"column10 style11 s style11\" colspan=\"4\">");
		sb.append(buyerVO.getBuyerAdd1());
		sb.append(buyerVO.getBuyerAdd2());
		sb.append("</td>\r\n");
		sb.append("          </tr>\r\n");
		sb.append("          <tr class=\"row7\">\r\n");
		sb.append("            <td class=\"column1 style14 n\"></td>\r\n");
		sb.append("            <td class=\"column2 style15 s\">)</td>\r\n");
		sb.append("            <td class=\"column3 style17 n\"></td>\r\n");
		sb.append("            <td class=\"column4 style18 s\">-</td>\r\n");
		sb.append("            <td class=\"column5 style17 n\"></td>\r\n");
		sb.append("            <td class=\"column6 style15 s style15\" colspan=\"3\">&nbsp;</td>\r\n");
		sb.append("            <td class=\"column9 style19 s\">부서/사원</td>\r\n");
		sb.append("            <td class=\"column10 style14 s\">");
		sb.append(estimateVO.getDeptName());
		sb.append("/");
		sb.append(estimateVO.getEmpName());
		sb.append("</td>\r\n");
		sb.append("            <td class=\"column11 style20 null\"></td>\r\n");
		sb.append("            <td class=\"column12 style21 null style21\" colspan=\"2\"></td>\r\n");
		sb.append("          </tr>\r\n");
		sb.append("          <tr class=\"row8\">\r\n");
		sb.append("            <td class=\"column0 style22 s style22\" colspan=\"2\">견적금액</td>\r\n");
		sb.append("            <td class=\"column2 style15 s\">일금</td>\r\n");
		sb.append("            <td class=\"column3 style23 s style23\" colspan=\"7\">");
		sb.append(convertedEpScost);
		sb.append("</td>\r\n");
		sb.append("            <td class=\"column10 style20 s\">원정( \\</td>\r\n");
		sb.append("            <td class=\"column11 style24 n\">");
		sb.append(formatter.format(epScostSum));
		sb.append("</td>\r\n");
		sb.append("            <td class=\"column12 style20 s\">)</td>\r\n");
		sb.append("            <td class=\"column13 style25 s\">VAT포함</td>\r\n");
		sb.append("          </tr>\r\n");
		sb.append("          <tr class=\"row9\">\r\n");
		sb.append("            <td class=\"column0 style26 s style26\" colspan=\"2\">견적명세</td>\r\n");
		sb.append("            <td class=\"column2 style27 s style27\" colspan=\"12\">&nbsp;</td>\r\n");
		sb.append("          </tr>\r\n");
		sb.append("          <tr class=\"row10\">\r\n");
		sb.append("            <td class=\"column0 style0 null\"></td>\r\n");
		sb.append("            <td class=\"column1 style0 null\"></td>\r\n");
		sb.append("            <td class=\"column2 style0 null\"></td>\r\n");
		sb.append("            <td class=\"column3 style0 null\"></td>\r\n");
		sb.append("            <td class=\"column4 style0 null\"></td>\r\n");
		sb.append("            <td class=\"column5 style0 null\"></td>\r\n");
		sb.append("            <td class=\"column6 style0 null\"></td>\r\n");
		sb.append("            <td class=\"column7 style0 null\"></td>\r\n");
		sb.append("            <td class=\"column8 style0 null\"></td>\r\n");
		sb.append("            <td class=\"column9 style0 null\"></td>\r\n");
		sb.append("            <td class=\"column10 style0 null\"></td>\r\n");
		sb.append("            <td class=\"column11 style0 null\"></td>\r\n");
		sb.append("            <td class=\"column12 style0 null\"></td>\r\n");
		sb.append("            <td class=\"column13 style0 null\"></td>\r\n");
		sb.append("          </tr>\r\n");
		sb.append("          <tr class=\"row11\">\r\n");
		sb.append("            <td class=\"column0 style26 s\">No.</td>\r\n");
		sb.append("            <td class=\"column1 style28 s\">품목코드</td>\r\n");
		sb.append("            <td class=\"column2 style29 s style29\" colspan=\"2\">품목명</td>\r\n");
		sb.append("            <td class=\"column4 style29 s\">규격</td>\r\n");
		sb.append("            <td class=\"column5 style30 s style30\" colspan=\"3\">단위</td>\r\n");
		sb.append("            <td class=\"column8 style30 s\">수량</td>\r\n");
		sb.append("            <td class=\"column9 style30 s\">단가</td>\r\n");
		sb.append("            <td class=\"column10 style30 s\">공급가</td>\r\n");
		sb.append("            <td class=\"column11 style30 s\">부가세</td>\r\n");
		sb.append("            <td class=\"column12 style31 s style31\" colspan=\"2\">합계액</td>\r\n");
		sb.append("          </tr>\r\n");

//		품목에 대한 데이터 시작점   //
		int itemCnt = 0;
		for(int i=0; i<estimateProdList.size(); i++) {
			sb.append("          <tr class=\"row");
			sb.append(12+i);
			sb.append("\">\r\n");
			sb.append("            <td class=\"column0 style32 s\">");
			sb.append(1+itemCnt); //품목 리스트 넘버링
			sb.append("</td>\r\n");
			sb.append("            <td class=\"column1 style33 s\">");
			sb.append(estimateProdList.get(i).getProdCode());
			sb.append("</td>\r\n");
			sb.append("            <td class=\"column2 style34 s style34\" colspan=\"2\">");
			sb.append(estimateProdList.get(i).getProdName());
			sb.append("</td>\r\n");
			sb.append("            <td class=\"column4 style34 s\">");
			sb.append(estimateProdList.get(i).getGcommName());
			sb.append("</td>\r\n");
			sb.append("            <td class=\"column5 style35 s style35\" colspan=\"3\">");
			sb.append(estimateProdList.get(i).getUcommName());
			sb.append("</td>\r\n");
			sb.append("            <td class=\"column8 style36 n\">");
			sb.append(formatter.format(estimateProdList.get(i).getEpQty()));
			sb.append("</td>\r\n");
			sb.append("            <td class=\"column9 style37 n\">");
			sb.append("\\"+formatter.format(estimateProdList.get(i).getEpUprice()));
			sb.append("</td>\r\n");
			sb.append("            <td class=\"column10 style38 n\">");
			sb.append("\\"+formatter.format(estimateProdList.get(i).getEpSp()));
			sb.append("</td>\r\n");
			sb.append("            <td class=\"column11 style38 n\">");
			sb.append("\\"+formatter.format(estimateProdList.get(i).getEpVat()));
			sb.append("</td>\r\n");
			sb.append("            <td class=\"column12 style39 n style39\" colspan=\"2\">");
			sb.append("\\"+formatter.format(estimateProdList.get(i).getEpScost()));
			sb.append("</td>\r\n");
			itemCnt ++;
		}
		//빈 값으로 채운다
		for(int i=0; i<12-estimateProdList.size(); i++) {
			sb.append("          <tr class=\"row");
			sb.append(12+i+itemCnt);
			sb.append("\">\r\n");
			sb.append("            <td class=\"column0 style32 s\">");
			sb.append(i+1+itemCnt); //품목 리스트 넘버링
			sb.append("</td>\r\n");
			sb.append("            <td class=\"column1 style33 s\">");
			sb.append("</td>\r\n");
			sb.append("            <td class=\"column2 style34 s style34\" colspan=\"2\">");
			sb.append("</td>\r\n");
			sb.append("            <td class=\"column4 style34 s\">");
			sb.append("</td>\r\n");
			sb.append("            <td class=\"column5 style35 s style35\" colspan=\"3\">");
			sb.append("</td>\r\n");
			sb.append("            <td class=\"column8 style36 n\">");
			sb.append("</td>\r\n");
			sb.append("            <td class=\"column9 style37 n\">");
			sb.append("</td>\r\n");
			sb.append("            <td class=\"column10 style38 n\">");
			sb.append("</td>\r\n");
			sb.append("            <td class=\"column11 style38 n\">");
			sb.append("</td>\r\n");
			sb.append("            <td class=\"column12 style39 n style39\" colspan=\"2\">");
			sb.append("</td>\r\n");
		}
		sb.append("          </tr>\r\n");
		//여기까지 품목에 대한 정보----------------------------------------------------------
		sb.append("			 <tr class=\"row24\">\r\n" ); 
		sb.append("            <td class=\"column0 style40 s style40\" colspan=\"8\">합계</td>\r\n" );
		sb.append("            <td class=\"column8 style41 n\">"+formatter.format(epQty)+"</td>\r\n" );
		sb.append("            <td class=\"column9 style42 null\"></td>\r\n" );
		sb.append("            <td class=\"column10 style43 s\">\\ "+formatter.format(epSpSum)+"</td>\r\n" );
		sb.append("            <td class=\"column11 style43 s\">\\ "+formatter.format(epVatSum)+"</td>\r\n" );
		sb.append("            <td class=\"column12 style44 s style44\" colspan=\"2\">\\ "+formatter.format(epScostSum)+"</td>\r\n" ); 
		sb.append("          </tr>");
		sb.append("          <tr class=\"row26\">\r\n");
		sb.append("            <td class=\"column0 style45 s style45\" colspan=\"14\">견적조건</td>\r\n");
		sb.append("          </tr>\r\n");
		sb.append("          <tr class=\"row27\">\r\n");
		sb.append("            <td class=\"column0 style46 s\">1.</td>\r\n");
		sb.append("            <td class=\"column1 style47 s style47\" colspan=\"7\">&nbsp;</td>\r\n");
		sb.append("            <td class=\"column8 style48 s\">2.</td>\r\n");
		sb.append("            <td class=\"column9 style49 s style49\" colspan=\"5\">&nbsp;</td>\r\n");
		sb.append("          </tr>\r\n");
		sb.append("          <tr class=\"row28\">\r\n");
		sb.append("            <td class=\"column0 style46 s\">3.</td>\r\n");
		sb.append("            <td class=\"column1 style47 s style47\" colspan=\"7\">&nbsp;</td>\r\n");
		sb.append("            <td class=\"column8 style48 s\">4.</td>\r\n");
		sb.append("            <td class=\"column9 style49 s style49\" colspan=\"5\">&nbsp;</td>\r\n");
		sb.append("          </tr>\r\n");
		sb.append("          <tr class=\"row29\">\r\n");
		sb.append("            <td class=\"column0 style46 s\">5.</td>\r\n");
		sb.append("            <td class=\"column1 style47 s style47\" colspan=\"7\">&nbsp;</td>\r\n");
		sb.append("            <td class=\"column8 style48 s\">6.</td>\r\n");
		sb.append("            <td class=\"column9 style49 s style49\" colspan=\"5\">&nbsp;</td>\r\n");
		sb.append("          </tr>\r\n");
		sb.append("          <tr class=\"row30\">\r\n");
		sb.append("            <td class=\"column0 style45 s style45\" colspan=\"14\">특이사항</td>\r\n");
		sb.append("          </tr>\r\n");
		sb.append("          <tr class=\"row31\">\r\n");
		sb.append("            <td class=\"column0 style46 s\">1.</td>\r\n");
		sb.append("            <td class=\"column1 style47 s style47\" colspan=\"7\">&nbsp;</td>\r\n");
		sb.append("            <td class=\"column8 style48 s\">2.</td>\r\n");
		sb.append("            <td class=\"column9 style49 s style49\" colspan=\"5\">&nbsp;</td>\r\n");
		sb.append("          </tr>\r\n");
		sb.append("          <tr class=\"row32\">\r\n");
		sb.append("            <td class=\"column0 style45 s style45\" colspan=\"14\">비고</td>\r\n");
		sb.append("          </tr>\r\n");
		sb.append("          <tr class=\"row33\">\r\n");
		sb.append("            <td class=\"column0 style46 s\">1.</td>\r\n");
		sb.append("            <td class=\"column1 style49 s style49\" colspan=\"13\">&nbsp;</td>\r\n");
		sb.append("          </tr>\r\n");
		sb.append("          <tr class=\"row34\">\r\n");
		sb.append("            <td class=\"column0 style46 s\">2.</td>\r\n");
		sb.append("            <td class=\"column1 style49 s style49\" colspan=\"13\">&nbsp;</td>\r\n");
		sb.append("          </tr>\r\n");
		sb.append("          <tr class=\"row35\">\r\n");
		sb.append("            <td class=\"column0 style46 s\">3.</td>\r\n");
		sb.append("            <td class=\"column1 style49 s style49\" colspan=\"13\">&nbsp;</td>\r\n");
		sb.append("          </tr>\r\n");
		sb.append("          <tr class=\"row36\">\r\n");
		sb.append("            <td class=\"column0 style50 s style50\" colspan=\"11\">"); 
		sb.append("</td>\r\n");
		sb.append("            <td class=\"column11 style51 s style51\" colspan=\"3\">"); 
		sb.append("</td>\r\n");
		sb.append("          </tr>\r\n");
		sb.append("        </tbody>\r\n");
		sb.append("    </table>\r\n");
		sb.append("  </body>\r\n");
		sb.append("</html>");

		return sb;
	}

}
