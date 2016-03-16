package com.wangf.compiler.frontend.pascal;

import java.io.IOException;

import com.wangf.compiler.frontend.EofToken;
import com.wangf.compiler.frontend.ITokenType;
import com.wangf.compiler.frontend.Parser;
import com.wangf.compiler.frontend.Scanner;
import com.wangf.compiler.frontend.Token;
import com.wangf.compiler.intermediate.ISymbolTableEntry;

public class PascalTopDownParser extends Parser {

	public PascalTopDownParser(Scanner scanner) {
		super(scanner);
	}

	@Override
	public void parse() throws IOException {
		Token token;
		while (!((token = scanner.nextToken()) instanceof EofToken)) {
			System.out.println(token);
			ITokenType tokenType = token.getType();
			if (tokenType == PascalTokenType.IDENTIFIER) {
				String name = token.getText().toLowerCase();
				ISymbolTableEntry entry = symbolTableStack.lookup(name);
				if (entry == null) {
					entry = symbolTableStack.createLocal(name);
				}
				entry.appendLineNumber(token.getLineNumber());
			}
		}
	}

}
