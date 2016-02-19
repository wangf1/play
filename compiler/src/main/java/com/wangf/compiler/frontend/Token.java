package com.wangf.compiler.frontend;

import java.io.IOException;

public class Token {
	private Object value;

	public Token(Source source) throws IOException {
		value = source.currentChar();
		// current char consumed by this token, source should read next char.
		source.nextChar();
	}

	@Override
	public String toString() {
		return "Token [value=" + value + "]";
	}

}
