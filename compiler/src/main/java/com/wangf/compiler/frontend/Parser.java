package com.wangf.compiler.frontend;

import java.io.IOException;

import com.wangf.compiler.intermediate.IIntermediateCode;
import com.wangf.compiler.intermediate.ISymbolTableStack;
import com.wangf.compiler.intermediate.SymbolTableFactory;

public abstract class Parser {

	protected Scanner scanner;
	protected ISymbolTableStack symbolTableStack;

	public Parser(Scanner scanner) {
		this.scanner = scanner;
		symbolTableStack = SymbolTableFactory.createSymTabStack();
	}

	public abstract void parse() throws IOException;

	public IIntermediateCode getIntermediateCode() {
		return null;
	}

	public ISymbolTableStack getSymTabStack() {
		return symbolTableStack;
	}
}
