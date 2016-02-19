package com.wangf.compiler.frontend.pascal;

import java.io.IOException;

import com.wangf.compiler.frontend.Source;
import com.wangf.compiler.frontend.Token;

public class EofToken extends Token {

	public EofToken(Source source) throws IOException {
		super(source);
	}

}
