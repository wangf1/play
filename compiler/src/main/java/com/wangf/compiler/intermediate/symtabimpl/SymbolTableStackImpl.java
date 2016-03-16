package com.wangf.compiler.intermediate.symtabimpl;

import java.util.Stack;

import com.wangf.compiler.intermediate.ISymbolTable;
import com.wangf.compiler.intermediate.ISymbolTableEntry;
import com.wangf.compiler.intermediate.ISymbolTableStack;
import com.wangf.compiler.intermediate.SymbolTableFactory;

public class SymbolTableStackImpl implements ISymbolTableStack {

	private Stack<ISymbolTable> symbolTableStack = new Stack<>();

	public SymbolTableStackImpl() {
		symbolTableStack.push(SymbolTableFactory.createSymTab(0));
	}

	@Override
	public int getCurrentNestingLevel() {
		int currentNestingLevel = symbolTableStack.size();
		return currentNestingLevel;
	}

	@Override
	public ISymbolTable getLocalSymTab() {
		ISymbolTable symbolTable = symbolTableStack.peek();
		return symbolTable;
	}

	@Override
	public ISymbolTableEntry createLocal(String name) {
		ISymbolTable symbolTable = getLocalSymTab();
		ISymbolTableEntry entry = symbolTable.create(name);
		return entry;
	}

	@Override
	public ISymbolTableEntry lookupLocal(String name) {
		ISymbolTable symbolTable = getLocalSymTab();
		ISymbolTableEntry entry = symbolTable.lookup(name);
		return entry;
	}

	@Override
	public ISymbolTableEntry lookup(String name) {
		for (ISymbolTable table : symbolTableStack) {
			ISymbolTableEntry entry = table.lookup(name);
			if (entry != null) {
				return entry;
			}
		}
		return null;
	}

}
