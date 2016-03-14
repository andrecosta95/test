package org.fiveware.test.web.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public final class WebUtils {

	private WebUtils() {
	}
	
	public static void addInfoMessage(String detail) {
		addInfoMessage(null, detail);
	}
	
	public static void addInfoMessage(String summary, String detail) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail));
	}
	
	public static void addErrorMessage(String detail) {
		addInfoMessage(null, detail);
	}
	
	public static void addErrorMessage(String summary, String detail) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
	}
	
}
