package com.wangf.compiler.frontend.pascal;

import java.io.IOException;

import com.wangf.compiler.frontend.EofToken;
import com.wangf.compiler.frontend.Scanner;
import com.wangf.compiler.frontend.Source;
import com.wangf.compiler.frontend.Token;
import com.wangf.compiler.frontend.pascal.tokens.PascalStringToken;
import com.wangf.compiler.frontend.pascal.tokens.PascalWordToken;

public class PascalScanner extends Scanner {

	public PascalScanner(Source source) {
		super(source);
	}

	@Override
	protected Token extractToken() throws IOException {
		skipWhiteSpace();

		Token token;
		char currentChar = source.currentChar();

		// Construct the next token. The current character determines the
		// token type.
		if (currentChar == Source.EOF) {
			token = new EofToken(source);
		} else if (Character.isLetter(currentChar)) {
			token = new PascalWordToken(source);
		} else if (currentChar == '\'') {
			token = new PascalStringToken(source);
		} else {
			token = new Token(source);
		}

		return token;
	}

	private void skipWhiteSpace() throws IOException {
		char currentChar = source.currentChar();
		while (Character.isWhitespace(currentChar) || currentChar == KeyCharacterConst.COMMENT_START) {
			if (currentChar == KeyCharacterConst.COMMENT_START) {
				do {
					currentChar = source.nextChar();
				} while (currentChar != KeyCharacterConst.COMMENT_END && currentChar != Source.EOF);
			} else {
				currentChar = source.nextChar();
			}
		}
	}

}
