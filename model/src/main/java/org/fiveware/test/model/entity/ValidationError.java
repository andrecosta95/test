package org.fiveware.test.model.entity;

import java.io.Serializable;

public class ValidationError implements Serializable {

	private static final long serialVersionUID = -7902354596708361266L;

	private String message;
	private String field;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	@Override
	public String toString() {
		return "ValidationError [message=" + message + ", field=" + field + "]";
	}

}
