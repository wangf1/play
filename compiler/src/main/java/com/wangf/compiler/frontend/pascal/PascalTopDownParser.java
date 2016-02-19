package com.wangf.compiler.frontend.pascal;

import java.io.IOException;

import com.wangf.compiler.frontend.Parser;
import com.wangf.compiler.frontend.Scanner;
import com.wangf.compiler.frontend.Token;

public class PascalTopDownParser extends Parser {

	public PascalTopDownParser(Scanner scanner) {
		super(scanner);
	}

	@Override
	public void parse() throws IOException {
		Token token;
		while (!((token = scanner.nextToken()) instanceof EofToken)) {
			System.out.println(token);
		}
	}

}
