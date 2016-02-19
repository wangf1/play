package com.wangf.compiler;

import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.Test;

import com.wangf.compiler.backend.Backend;
import com.wangf.compiler.backend.BackendFactory;
import com.wangf.compiler.frontend.FrontendFactory;
import com.wangf.compiler.frontend.Parser;
import com.wangf.compiler.frontend.Source;
import com.wangf.compiler.intermediate.IIntermediateCode;
import com.wangf.compiler.intermediate.ISymbolTable;

public class ParseTest {
	@Test
	public void testParse() throws Exception {

		try (InputStream in = getClass().getResourceAsStream("hello.pas");
				InputStreamReader reader = new InputStreamReader(in);
				Source source = new Source(reader)) {

			Parser parser = FrontendFactory.createParser("Pascal", "top-down", source);

			Backend backend = BackendFactory.createBackend(BackendFactory.OPERATION_COMPILE);

			parser.parse();

			IIntermediateCode intermediateCode = parser.getIntermediateCode();
			ISymbolTable symbolTable = parser.getSymbolTable();

			backend.process(intermediateCode, symbolTable);
		}
	}
}
