package kr.or.ddit.validate.view;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

public class TestJsonModelValidationView extends MappingJackson2JsonView{
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		OutputStream stream = (this.updateContentLength ? createTemporaryOutputStream() : response.getOutputStream());
		Object value = filterAndWrapModel(model, request);

		Map<String, Object> target = (Map<String, Object>) value;
		
		RequestContext requestContext = createRequestContext(request, response, model);
		
//		String objectName = (String) model.get("objectName");
//		Errors errors = (Errors) model.get(BindingResult.class.getName() + "."+objectName);
//		Map<String, String> errorMessages = new HashMap<String, String>();
//		target.put("errorMessages", errorMessages);
//		for(FieldError fe : errors.getFieldErrors()) {
//			String path = objectName +"."+ fe.getField();
//			BindStatus status = requestContext.getBindStatus(path);
////			BindStatus status = new BindStatus(requestContext, path, true);
//			String errorMsg = status.getErrorMessage();
//			errorMessages.put(path, errorMsg);
//		}

		
		writeContent(stream, target);
		if (this.updateContentLength) {
			writeToResponse(response, (ByteArrayOutputStream) stream);
		}
	}
}
