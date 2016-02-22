package com.wangf.compiler.frontend.pascal;

public enum PascalErrorCode {
	UNEXPECTED_EOF("Unexpected end of file");

	private String message;

	PascalErrorCode(String message) {
		this.message = message;
	}
}
