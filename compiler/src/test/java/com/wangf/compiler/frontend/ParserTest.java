package com.wangf.compiler.frontend;

import java.io.FileReader;
import java.io.Reader;

import org.junit.Test;

public class ParserTest {
	@Test
	public void testParse() throws Exception {

		try (Source source = new Source(new FileReader("hello.pas"))) {

			Parser parser = FrontendFactory.createParser("Pascal", "top-down", source);

			Backend backend = BackendFactory.createBackend(BackendFactory.OPERATION_COMPILE);

			parser.parse();

			IIntermediateCode intermediateCode = parser.getIntermediateCode();
			ISymbolTable symbolTable = parser.getSymbolTable();

			backend.process(intermediateCode, symbolTable);
		}
	}
}
