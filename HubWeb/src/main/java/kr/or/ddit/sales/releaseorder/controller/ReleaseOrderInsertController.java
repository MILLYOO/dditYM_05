package kr.or.ddit.sales.releaseorder.controller;

import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.common.vo.CommonListVO;
import kr.or.ddit.sales.releaseorder.service.ReleaseOrderService;
import kr.or.ddit.sales.vo.ReleaseOrderProdVO;
import kr.or.ddit.sales.vo.ReleaseOrderVO;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.validate.util.hubWebValidateUtil;
import kr.or.ddit.validate.util.hubWebValidateUtilImpl;

/**
 * 출고지시서
 */
@Controller
public class ReleaseOrderInsertController {
	@Inject
	private ReleaseOrderService service;
	@Inject
	private WebApplicationContext context;
	
		// 출고지시서 등록
		@ResponseBody
		@RequestMapping(value="/sales/ReleaseOrderInsert.do", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
		public Map<String, Object> ReleaseOrderInsert(
				@RequestBody @Validated(UpdateGroup.class) CommonListVO<ReleaseOrderVO> obj,
				Errors errors
				) {
			hubWebValidateUtil valid = new hubWebValidateUtilImpl(obj, errors, context); // 검증조건에서 에러가 발생하면
			if (!valid.checkError()) {
				valid.setResultMap(service.createORupdateReleaseOrder(obj.getCommonList()));
			}
			return valid.getResultMap();
		}
	
		// 출고지시서 상세 품목 등록
		@RequestMapping(value="/sales/ReleaseOrderProdInsert.do", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
		@ResponseBody
		public Map<String, Object> ReleaseOrderProdInsert(
				@RequestBody @Validated(UpdateGroup.class) CommonListVO<ReleaseOrderProdVO> obj,
				Errors errors
				) {
			hubWebValidateUtil valid = new hubWebValidateUtilImpl(obj, errors, context);
			if (!valid.checkError()) {
				valid.setResultMap(service.createRaws(obj.getCommonList()));
			}
			return valid.getResultMap();
		}
}
