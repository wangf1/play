package com.wangf.compiler.frontend.pascal.tokens;

import java.io.IOException;

import com.wangf.compiler.frontend.Source;
import com.wangf.compiler.frontend.pascal.PascalErrorCode;
import com.wangf.compiler.frontend.pascal.PascalToken;
import com.wangf.compiler.frontend.pascal.PascalTokenType;

public class PascalErrorToken extends PascalToken {

	public PascalErrorToken(Source source, PascalErrorCode errorCode) throws IOException {
		super(source);
		type = PascalTokenType.ERROR;
		value = errorCode;
	}

	@Override
	protected void extract() throws IOException {
		text.append(source.currentChar());
		source.nextChar();
	}

}
