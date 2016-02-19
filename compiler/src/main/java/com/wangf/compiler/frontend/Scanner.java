package com.wangf.compiler.frontend;

import java.io.IOException;

public abstract class Scanner {

	protected Source source;

	private Token currentToken;

	public Scanner(Source source) {
		this.source = source;
	}

	public Token nextToken() throws IOException {
		currentToken = extractToken();
		return currentToken;
	}

	protected abstract Token extractToken() throws IOException;

	public Token getCurrentToken() {
		return currentToken;
	}

}
