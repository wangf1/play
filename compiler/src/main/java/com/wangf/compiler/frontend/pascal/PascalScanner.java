package com.wangf.compiler.frontend.pascal;

import java.io.IOException;

import com.wangf.compiler.frontend.Scanner;
import com.wangf.compiler.frontend.Source;
import com.wangf.compiler.frontend.Token;

public class PascalScanner extends Scanner {

	public PascalScanner(Source source) {
		super(source);
	}

	@Override
	protected Token extractToken() throws IOException {
		Token token;
		char currentChar = source.currentChar();

		// Construct the next token. The current character determines the
		// token type.
		if (currentChar == Source.EOF) {
			token = new EofToken(source);
		} else {
			token = new Token(source);
		}

		return token;
	}

}
