package com.wangf.compiler.intermediate.symtabimpl;

import java.util.ArrayList;
import java.util.Map;

import com.wangf.compiler.intermediate.ISymbolTable;
import com.wangf.compiler.intermediate.ISymbolTableEntry;
import com.wangf.compiler.intermediate.ISymbolTableKey;

public class SymbolTableEntryImpl implements ISymbolTableEntry {
	private String name;
	private ISymbolTable symTab;
	private ArrayList<Integer> lineNumbers = new ArrayList<Integer>();
	private Map<ISymbolTableKey, Object> attributes;

	public SymbolTableEntryImpl(String name, ISymbolTable symTab) {
		this.name = name;
		this.symTab = symTab;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public ISymbolTable getSymTab() {
		return symTab;
	}

	@Override
	public void appendLineNumber(int lineNumber) {
		lineNumbers.add(lineNumber);
	}

	@Override
	public ArrayList<Integer> getLineNumbers() {
		return lineNumbers;
	}

	@Override
	public void setAttribute(ISymbolTableKey key, Object value) {
		attributes.put(key, value);
	}

	@Override
	public Object getAttribute(ISymbolTableKey key) {
		Object attribute = attributes.get(key);
		return attribute;
	}

	@Override
	public String toString() {
		return "SymbolTableEntryImpl@" + Integer.toHexString(hashCode()) + " [name=" + name + ", symTab=" + symTab
				+ ", lineNumbers=" + lineNumbers + ", attributes=" + attributes + "]";
	}

}
