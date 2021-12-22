package kr.or.ddit.buy.purchclose.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.validation.groups.Default;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.buy.purchclose.service.PurchCloseService;
import kr.or.ddit.buy.vo.IncomingRawsVO;
import kr.or.ddit.buy.vo.IncomingVO;
import kr.or.ddit.common.vo.CommonListVO;
import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.validate.groups.DetailUpdateGroup;
import kr.or.ddit.validate.util.hubWebValidateUtil;
import kr.or.ddit.validate.util.hubWebValidateUtilImpl;
import lombok.extern.slf4j.Slf4j;

/**
 * 매입마감 - 입고적용
 */
@Controller
@Slf4j
public class PCIncomingApplyController {
	@Inject
	private WebApplicationContext context;	
	@Inject
	private PurchCloseService service;
	
	//입고처리서 적용 masterGrid
	@ResponseBody
	@RequestMapping(value="/buy/pCIncomingApply.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.POST)
	public List<IncomingVO> PCIncomingApply(@ModelAttribute("hubSearchVO") HubSearchVO hubSearchVO) {
		List<IncomingVO> incListForPclose = service.retrieveIncListForPclose(hubSearchVO);
		return incListForPclose;
	}
	
	//입고처리 적용 detailGrid
	@ResponseBody
	@RequestMapping(value="/buy/pCIncomingRawsApply.do",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<IncomingRawsVO> PCIncomingRawsApply(@RequestParam("incNum") String incNum){
		IncomingVO incomingVO = new IncomingVO();
		incomingVO.setIncNum(incNum);
		List<IncomingRawsVO> incomingRawsList = service.retrieveIncRawsListForPclose(incomingVO);
		return incomingRawsList;
	}
	
	//입고처리 적용
		@ResponseBody
		@RequestMapping(value="/buy/pCIncomingApplyInsert.do", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
		public Map<String, Object> PcIncomingApplyInsert(
				@Validated(DetailUpdateGroup.class)  @RequestBody CommonListVO<IncomingVO> obj
				,Errors errors
				) {
			hubWebValidateUtil valid = new hubWebValidateUtilImpl(obj, errors, context);
			if(!valid.checkError()) {
				valid.setResultMap(service.insertPcloseByInc(obj.getCommonList()));
			}
			return valid.getResultMap();
		}
	}