package com.wangf.compiler.intermediate;

import com.wangf.compiler.intermediate.symtabimpl.SymbolTableEntryImpl;
import com.wangf.compiler.intermediate.symtabimpl.SymbolTableImpl;
import com.wangf.compiler.intermediate.symtabimpl.SymbolTableStackImpl;

public class SymbolTableFactory {
	/**
	 * Create and return a symbol table stack implementation.
	 * 
	 * @return the symbol table implementation.
	 */
	public static ISymbolTableStack createSymTabStack() {
		return new SymbolTableStackImpl();
	}

	/**
	 * Create and return a symbol table implementation.
	 * 
	 * @param nestingLevel
	 *            the nesting level.
	 * @return the symbol table implementation.
	 */
	public static ISymbolTable createSymTab(int nestingLevel) {
		return new SymbolTableImpl(nestingLevel);
	}

	/**
	 * Create and return a symbol table entry implementation.
	 * 
	 * @param name
	 *            the identifier name.
	 * @param symTab
	 *            the symbol table that contains this entry.
	 * @return the symbol table entry implementation.
	 */
	public static ISymbolTableEntry createSymTabEntry(String name, ISymbolTable symTab) {
		return new SymbolTableEntryImpl(name, symTab);
	}
}
