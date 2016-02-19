package com.wangf.compiler.frontend;

import com.wangf.compiler.frontend.pascal.PascalScanner;
import com.wangf.compiler.frontend.pascal.PascalTopDownParser;

public class FrontendFactory {

	public static final String PASER_TYPE_TOP_DOWN = "top-down";
	public static final String LANGUAGE_PASCAL = "Pascal";

	public static Parser createParser(String language, String paserType, Source source) {
		if (language.equalsIgnoreCase(LANGUAGE_PASCAL) && paserType.equalsIgnoreCase(PASER_TYPE_TOP_DOWN)) {
			Scanner scanner = new PascalScanner(source);
			return new PascalTopDownParser(scanner);
		} else {
			String errorMessage = String.format("Not support language '%s' with parser type '%s'", language, paserType);
			throw new UnsupportedOperationException(errorMessage);
		}
	}

}
