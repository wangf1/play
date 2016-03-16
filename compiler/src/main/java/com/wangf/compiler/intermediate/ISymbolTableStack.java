package com.wangf.compiler.intermediate;

public interface ISymbolTableStack {
	/**
	 * Getter.
	 * 
	 * @return the current nesting level.
	 */
	public int getCurrentNestingLevel();

	/**
	 * Return the local symbol table which is at the top of the stack.
	 * 
	 * @return the local symbol table.
	 */
	public ISymbolTable getLocalSymTab();

	/**
	 * Create and enter a new entry into the local symbol table.
	 * 
	 * @param name
	 *            the name of the entry.
	 * @return the new entry.
	 */
	public ISymbolTableEntry createLocal(String name);

	/**
	 * Look up an existing symbol table entry in the local symbol table.
	 * 
	 * @param name
	 *            the name of the entry.
	 * @return the entry, or null if it does not exist.
	 */
	public ISymbolTableEntry lookupLocal(String name);

	/**
	 * Look up an existing symbol table entry throughout the stack.
	 * 
	 * @param name
	 *            the name of the entry.
	 * @return the entry, or null if it does not exist.
	 */
	public ISymbolTableEntry lookup(String name);
}
