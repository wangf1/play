package com.wangf.compiler.frontend;

import java.io.IOException;

import com.wangf.compiler.frontend.pascal.PascalTokenType;

public class Token {

	protected Source source;
	protected PascalTokenType type;
	protected Object value;

	public Token(Source source) throws IOException {
		this.source = source;
		extract();
	}

	protected void extract() throws IOException {
		value = source.currentChar();
		// current char consumed by this token, source should read next char.
		source.nextChar();
	}

	@Override
	public String toString() {
		return "Token [value=" + value + "]";
	}

}
