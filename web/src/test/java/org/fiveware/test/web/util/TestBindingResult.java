package org.fiveware.test.web.util;

import org.springframework.validation.AbstractBindingResult;

public class TestBindingResult extends AbstractBindingResult {

	private static final long serialVersionUID = -3903919240191952094L;

	private boolean hasErrors;
	
	public TestBindingResult(String objectName, boolean hasErrors) {
		super(objectName);
		this.hasErrors = hasErrors;
	}

	@Override
	public Object getTarget() {
		return null;
	}

	@Override
	protected Object getActualFieldValue(String paramString) {
		return null;
	}

	@Override
	public boolean hasErrors() {
		return this.hasErrors;
	}
	
}
