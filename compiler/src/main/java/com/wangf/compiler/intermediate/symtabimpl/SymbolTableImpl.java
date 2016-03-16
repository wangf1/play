package com.wangf.compiler.intermediate.symtabimpl;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.wangf.compiler.intermediate.ISymbolTable;
import com.wangf.compiler.intermediate.ISymbolTableEntry;
import com.wangf.compiler.intermediate.SymbolTableFactory;

public class SymbolTableImpl implements ISymbolTable {
	private Map<String, ISymbolTableEntry> entryMap = new HashMap<>();
	private int nestingLevel;

	public SymbolTableImpl(int nestingLevel) {
		this.nestingLevel = nestingLevel;
	}

	@Override
	public int getNestingLevel() {
		return nestingLevel;
	}

	@Override
	public ISymbolTableEntry create(String name) {
		ISymbolTableEntry entry = SymbolTableFactory.createSymTabEntry(name, this);
		entryMap.put(name, entry);
		return entry;
	}

	@Override
	public ISymbolTableEntry lookup(String name) {
		ISymbolTableEntry entry = entryMap.get(name);
		return entry;
	}

	@Override
	public List<ISymbolTableEntry> sortedEntries() {
		Collection<ISymbolTableEntry> entries = entryMap.values();
		List<ISymbolTableEntry> entryList = new LinkedList<>(entries);
		Comparator<ISymbolTableEntry> sortByName = Comparator.comparing(ISymbolTableEntry::getName);
		entryList.sort(sortByName);
		return entryList;
	}

}
