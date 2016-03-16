package com.wangf.compiler.frontend;

import java.io.IOException;

public class Token {

	protected Source source;
	protected ITokenType type;
	protected Object value;
	protected int lineNumber;
	protected StringBuilder text = new StringBuilder();

	public Token(Source source) throws IOException {
		this.source = source;
		this.lineNumber = source.getLineNumber();
		extract();
	}

	protected void extract() throws IOException {
		value = source.currentChar();
		// current char consumed by this token, source should read next char.
		source.nextChar();
	}

	@Override
	public String toString() {
		return "Token [type=" + type + ", value=" + value + ", text='" + text + "']";
	}

	public ITokenType getType() {
		return type;
	}

	public String getText() {
		return text.toString();
	}

	public int getLineNumber() {
		return lineNumber;
	}

}
