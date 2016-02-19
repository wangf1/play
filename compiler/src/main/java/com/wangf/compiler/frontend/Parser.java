package com.wangf.compiler.frontend;

import java.io.IOException;

import com.wangf.compiler.intermediate.IIntermediateCode;
import com.wangf.compiler.intermediate.ISymbolTable;

public abstract class Parser {

	protected Scanner scanner;

	public Parser(Scanner scanner) {
		this.scanner = scanner;
	}

	public abstract void parse() throws IOException;

	public IIntermediateCode getIntermediateCode() {
		return null;
	}

	public ISymbolTable getSymbolTable() {
		return null;
	}

}
