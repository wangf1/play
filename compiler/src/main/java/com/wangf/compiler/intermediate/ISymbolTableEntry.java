package com.wangf.compiler.intermediate;

import java.util.ArrayList;

public interface ISymbolTableEntry {
	/**
	 * Getter.
	 * 
	 * @return the name of the entry.
	 */
	public String getName();

	/**
	 * Getter.
	 * 
	 * @return the symbol table that contains this entry.
	 */
	public ISymbolTable getSymTab();

	/**
	 * Append a source line number to the entry.
	 * 
	 * @param lineNumber
	 *            the line number to append.
	 */
	public void appendLineNumber(int lineNumber);

	/**
	 * Getter.
	 * 
	 * @return the list of source line numbers.
	 */
	public ArrayList<Integer> getLineNumbers();

	/**
	 * Set an attribute of the entry.
	 * 
	 * @param key
	 *            the attribute key.
	 * @param value
	 *            the attribute value.
	 */
	public void setAttribute(ISymbolTableKey key, Object value);

	/**
	 * Get the value of an attribute of the entry.
	 * 
	 * @param key
	 *            the attribute key.
	 * @return the attribute value.
	 */
	public Object getAttribute(ISymbolTableKey key);
}
