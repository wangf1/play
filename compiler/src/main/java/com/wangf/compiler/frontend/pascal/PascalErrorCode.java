package com.wangf.compiler.frontend.pascal;

public enum PascalErrorCode {
	INVALID_NUMBER("Invalid number"), UNEXPECTED_EOF("Unexpected end of file"), INVALID_CHARACTER("Invalid character");

	private String message;

	PascalErrorCode(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return super.toString() + "[" + message + "]";
	}
}
