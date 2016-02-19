package com.wangf.compiler.backend;

import com.wangf.compiler.backend.compiler.CodeGenerator;
import com.wangf.compiler.backend.interpreter.Executor;

public class BackendFactory {

	public static final String OPERATION_COMPILE = "compile";
	public static final String OPERATION_EXECUTE = "execute";

	public static Backend createBackend(String operation) {
		if (operation.equalsIgnoreCase(OPERATION_COMPILE)) {
			return new CodeGenerator();
		} else if (operation.equalsIgnoreCase(OPERATION_EXECUTE)) {
			return new Executor();
		} else {
			throw new UnsupportedOperationException("Backend factory: Invalid operation '" + operation + "'");
		}
	}

}
