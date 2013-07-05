package com.framework.common.ajax.buffalo;
import java.io.IOException;
import net.buffalo.request.*;
import net.buffalo.service.*;
import net.buffalo.service.invoker.ServiceInvoker;
import net.buffalo.service.invoker.burlap.BurlapInvoker;

public class RequestWorkerImpl extends AbstractRequestWorker implements RequestWorker {
	
	public RequestWorkerImpl() {
	}

	public void processRequest() throws IOException {	
		String requestService = getWorkerRelativePath();		
		ServiceRepository repository = ServiceRepositoryUtil
				.getServiceRepository(requestContext.getContext());		
		Object service = repository.get(requestService);			
		if (service instanceof AjaxService)
			((AjaxService) service).init(requestContext);		
		ServiceInvoker invoker = new BurlapInvoker(service);
		java.io.InputStream is = requestContext.getRequest().getInputStream();
		java.io.OutputStream os = requestContext.getResponse()
				.getOutputStream();
		try {
			invoker.invoke(is, os);
		} catch (Throwable ex) {
			ex.printStackTrace();
			throw new ServiceInvocationException(
					"An exception occured when invoke a service", ex);
		}
	}

	public void validate() throws ValidationException {
		if (!requestContext.getRequest().getMethod().equals("POST"))
			throw new ValidationException("Sg worker support POST only!");
		else
			return;
	}

}
