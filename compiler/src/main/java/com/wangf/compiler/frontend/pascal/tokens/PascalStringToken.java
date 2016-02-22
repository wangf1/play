package com.wangf.compiler.frontend.pascal.tokens;

import java.io.IOException;

import com.wangf.compiler.frontend.Source;
import com.wangf.compiler.frontend.pascal.PascalErrorCode;
import com.wangf.compiler.frontend.pascal.PascalToken;
import com.wangf.compiler.frontend.pascal.PascalTokenType;

public class PascalStringToken extends PascalToken {

	private static final char SINGLE_QUOTE = '\'';

	public PascalStringToken(Source source) throws IOException {
		super(source);
	}

	@Override
	protected void extract() throws IOException {
		StringBuilder valueBuffer = new StringBuilder();
		char currentChar = source.nextChar();
		while (currentChar != SINGLE_QUOTE && currentChar != Source.EOF) {
			if (currentChar != SINGLE_QUOTE && currentChar != Source.EOF) {
				valueBuffer.append(currentChar);
				currentChar = source.nextChar();
			}
			if (currentChar == SINGLE_QUOTE) {
				while (currentChar == SINGLE_QUOTE && source.peekChar() == SINGLE_QUOTE) {
					valueBuffer.append(currentChar);
					currentChar = source.nextChar();
					currentChar = source.nextChar();
				}
			}
		}
		if (currentChar == SINGLE_QUOTE) {
			source.nextChar();
			type = PascalTokenType.STRING;
			value = valueBuffer.toString();
		} else {
			type = PascalTokenType.ERROR;
			value = PascalErrorCode.UNEXPECTED_EOF;
		}
	}

}
