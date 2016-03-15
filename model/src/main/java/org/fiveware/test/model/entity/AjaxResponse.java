package org.fiveware.test.model.entity;

import java.io.Serializable;

public class AjaxResponse implements Serializable {

	private static final long serialVersionUID = -5149350871910046912L;
	
	private int code;
	private String message;
	private Object result;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "AjaxResponse [code=" + code + ", message=" + message + ", result=" + result + "]";
	}

}
