package com.wangf.compiler.frontend.pascal.tokens;

import java.io.IOException;

import com.wangf.compiler.frontend.Source;
import com.wangf.compiler.frontend.pascal.PascalToken;
import com.wangf.compiler.frontend.pascal.PascalTokenType;

public class PascalSpecialSymbolToken extends PascalToken {

	public PascalSpecialSymbolToken(Source source) throws IOException {
		super(source);
	}

	@Override
	protected void extract() throws IOException {
		char currentChar = source.currentChar();
		text.append(currentChar);
		char nextChar = source.nextChar();
		String possibleTwoCharSymbol = currentChar + "" + nextChar;
		if (PascalTokenType.SPECIAL_SYMBOLS.containsKey(possibleTwoCharSymbol)) {
			text.append(nextChar);
		}
		type = PascalTokenType.SPECIAL_SYMBOLS.get(text.toString());
	}
}
