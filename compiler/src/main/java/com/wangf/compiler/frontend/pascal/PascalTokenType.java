package com.wangf.compiler.frontend.pascal;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

import com.wangf.compiler.frontend.ITokenType;

public enum PascalTokenType implements ITokenType {

	// Reserved words
	AND, ARRAY, BEGIN, CASE, CONST, DIV, DO, DOWNTO, ELSE, END, FILE, FOR, FUNCTION, GOTO, IF, IN, LABEL, MOD, NIL, NOT, OF, OR, PACKED, PROCEDURE, PROGRAM, RECORD, REPEAT, SET, THEN, TO, TYPE, UNTIL, VAR, WHILE, WITH,
	// General types
	IDENTIFIER, INTEGER, STRING, ERROR, REAL,
	// Special symbols.
	PLUS("+"), MINUS("-"), STAR("*"), SLASH("/"), COLON_EQUALS(":="), DOT("."), COMMA(","), SEMICOLON(";"), COLON(
			":"), QUOTE("'"), EQUALS("="), NOT_EQUALS("<>"), LESS_THAN("<"), LESS_EQUALS("<="), GREATER_EQUALS(
					">="), GREATER_THAN(">"), LEFT_PAREN("("), RIGHT_PAREN(")"), LEFT_BRACKET("["), RIGHT_BRACKET(
							"]"), LEFT_BRACE("{"), RIGHT_BRACE("}"), UP_ARROW("^"), DOT_DOT("..");

	private String text;

	private PascalTokenType() {
		this.text = this.toString().toLowerCase();
	}

	private PascalTokenType(String text) {
		this.text = text;
	}

	// Set of lower-cased Pascal reserved word text strings.
	public static Set<String> RESERVED_WORDS = initReservedWords();

	private static Set<String> initReservedWords() {
		Set<String> keyWords = new HashSet<>();
		PascalTokenType values[] = PascalTokenType.values();
		for (int i = AND.ordinal(); i <= WITH.ordinal(); i++) {
			keyWords.add(values[i].text.toLowerCase());
		}
		return keyWords;
	}

	public static Hashtable<String, PascalTokenType> SPECIAL_SYMBOLS = new Hashtable<String, PascalTokenType>();

	static {
		PascalTokenType values[] = PascalTokenType.values();
		for (int i = PLUS.ordinal(); i <= DOT_DOT.ordinal(); ++i) {
			SPECIAL_SYMBOLS.put(values[i].text, values[i]);
		}
	}
}
