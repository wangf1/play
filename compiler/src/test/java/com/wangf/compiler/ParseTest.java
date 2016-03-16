package com.wangf.compiler;

import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.Test;

import com.wangf.compiler.backend.Backend;
import com.wangf.compiler.backend.BackendFactory;
import com.wangf.compiler.frontend.FrontendFactory;
import com.wangf.compiler.frontend.Parser;
import com.wangf.compiler.frontend.Source;
import com.wangf.compiler.intermediate.IIntermediateCode;
import com.wangf.compiler.intermediate.ISymbolTableStack;
import com.wangf.compiler.util.CrossReferencer;

public class ParseTest {
	@Test
	public void testParse() throws Exception {

		try (InputStream in = getClass().getResourceAsStream("hello.pas");
				InputStreamReader reader = new InputStreamReader(in);
				Source source = new Source(reader)) {

			Parser parser = FrontendFactory.createParser(FrontendFactory.LANGUAGE_PASCAL,
					FrontendFactory.PASER_TYPE_TOP_DOWN, source);

			Backend backend = BackendFactory.createBackend(BackendFactory.OPERATION_COMPILE);

			parser.parse();

			IIntermediateCode intermediateCode = parser.getIntermediateCode();
			ISymbolTableStack symbolTableStack = parser.getSymTabStack();

			new CrossReferencer().print(symbolTableStack);

			backend.process(intermediateCode, symbolTableStack);
		}
	}
}
