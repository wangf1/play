package com.wangf.compiler.frontend;

import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;

public class Source implements Closeable {
	public static final char EOF = (char) 0;

	private Reader reader;
	private char currentChar;

	public Source(Reader reader) throws IOException {
		this.reader = reader;
		// Call next char immediately in order to make currentChar a valid
		// value.
		nextChar();
	}

	@Override
	public void close() throws IOException {
		reader.close();
	}

	public char currentChar() throws IOException {
		return currentChar;
	}

	public char nextChar() throws IOException {
		int charInt = reader.read();
		if (charInt != -1) {
			currentChar = (char) charInt;
		} else {
			currentChar = EOF;
		}
		return currentChar;
	}

}
