package com.wangf.compiler.frontend;

import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;

public class Source implements Closeable {
	private Reader reader;

	public Source(Reader reader) {
		this.reader = reader;
	}

	@Override
	public void close() throws IOException {
		reader.close();
	}

}
