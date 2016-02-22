package com.wangf.compiler.frontend;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;

public class Source implements Closeable {

	public static final char EOL = '\n';
	public static final char EOF = (char) 0;

	/**
	 * This value indicate source not yet read any line.
	 */
	private static final int POSITION_BEFORE_FIRST_CHAR = -1;

	private BufferedReader reader;
	// current source line
	private String line;
	// current source line number
	private int lineNumber = 0;
	// current source line position
	private int currentPositionInLine = POSITION_BEFORE_FIRST_CHAR;

	public Source(Reader reader) throws IOException {
		if (reader instanceof BufferedReader) {
			this.reader = (BufferedReader) reader;
		} else {
			this.reader = new BufferedReader(reader);
		}
		initCurrentCharForNewLine();
	}

	private char initCurrentCharForNewLine() throws IOException {
		// Read first line immediately in order to make sure this.line not null,
		// and this.currentChar() wokrs fine.
		readLine();
		char firstChar = nextChar();
		return firstChar;
	}

	@Override
	public void close() throws IOException {
		reader.close();
	}

	public char currentChar() throws IOException {
		char currentChar;
		if (line == null) {
			currentChar = EOF;
		} else if (currentPositionInLine == line.length()) {
			currentChar = EOL;
		} else if (currentPositionInLine > line.length()) {
			currentChar = initCurrentCharForNewLine();
		} else {
			currentChar = line.charAt(currentPositionInLine);
		}
		return currentChar;
	}

	private void readLine() throws IOException {
		line = reader.readLine();
		currentPositionInLine = POSITION_BEFORE_FIRST_CHAR;
		if (line != null) {
			lineNumber++;
		}
	}

	public char nextChar() throws IOException {
		currentPositionInLine++;
		char currentChar = currentChar();
		return currentChar;
	}

	public char peekChar() {
		if (line == null) {
			return EOF;
		}
		int nextPosition = currentPositionInLine + 1;
		if (nextPosition < line.length()) {
			char nextChar = line.charAt(nextPosition);
			return nextChar;
		} else {
			return EOL;
		}
	}

}
