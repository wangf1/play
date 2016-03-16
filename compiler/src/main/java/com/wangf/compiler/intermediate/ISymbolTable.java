package com.wangf.compiler.intermediate;

import java.util.List;

public interface ISymbolTable {
	/**
	 * Getter.
	 * 
	 * @return the scope nesting level of this entry.
	 */
	public int getNestingLevel();

	/**
	 * Create and enter a new entry into the symbol table.
	 * 
	 * @param name
	 *            the name of the entry.
	 * @return the new entry.
	 */
	public ISymbolTableEntry create(String name);

	/**
	 * Look up an existing symbol table entry.
	 * 
	 * @param name
	 *            the name of the entry.
	 * @return the entry, or null if it does not exist.
	 */
	public ISymbolTableEntry lookup(String name);

	/**
	 * @return a list of symbol table entries sorted by name.
	 */
	public List<ISymbolTableEntry> sortedEntries();
}
