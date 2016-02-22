package com.wangf.compiler.frontend.pascal.tokens;

import java.io.IOException;

import com.wangf.compiler.frontend.Source;
import com.wangf.compiler.frontend.Token;
import com.wangf.compiler.frontend.pascal.PascalTokenType;

public class PascalWordToken extends Token {

	public PascalWordToken(Source source) throws IOException {
		super(source);
	}

	@Override
	protected void extract() throws IOException {
		StringBuilder valueBuffer = new StringBuilder();
		char currentChar = source.currentChar();
		do {
			valueBuffer.append(currentChar);
			currentChar = source.nextChar();
		} while (Character.isLetterOrDigit(currentChar));
		value = valueBuffer.toString();
		setType();
	}

	private void setType() {
		String text = value.toString();
		boolean isKeyword = PascalTokenType.RESERVED_WORDS.contains(text.toLowerCase());
		type = isKeyword ? PascalTokenType.valueOf(text.toUpperCase()) : PascalTokenType.IDENTIFIER;
	}
}
