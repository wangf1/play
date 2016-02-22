package com.wangf.compiler.frontend.pascal.tokens;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

import com.wangf.compiler.frontend.Source;
import com.wangf.compiler.frontend.Token;
import com.wangf.compiler.frontend.pascal.KeyCharacterConst;
import com.wangf.compiler.frontend.pascal.PascalErrorCode;
import com.wangf.compiler.frontend.pascal.PascalTokenType;

public class PascalNumberToken extends Token {

	public PascalNumberToken(Source source) throws IOException {
		super(source);
	}

	@Override
	protected void extract() throws IOException {
		// assume INTEGER token type for now
		type = PascalTokenType.INTEGER;

		// extract digits of the whole part
		String wholeDigits = unsignedIntegerDigits();
		if (type == PascalTokenType.ERROR) {
			return;
		}

		// case of have decimal point or have double dot
		char currentChar = source.currentChar();
		String fractionDigits = null;
		if (currentChar == KeyCharacterConst.DOT) {
			char nextChar = source.peekChar();
			if (nextChar == KeyCharacterConst.DOT) {
				// digits before the double dot is a integer, can return now
				BigInteger bigInt = computeIntValue(wholeDigits);
				value = bigInt;
				return;
			} else {
				type = PascalTokenType.REAL;
				text.append(currentChar);
				currentChar = source.nextChar();// consume the dot character
				fractionDigits = unsignedIntegerDigits();
				if (type == PascalTokenType.ERROR) {
					return;
				}
			}
		}

		// Is there an exponent part?
		char exponentSign = KeyCharacterConst.PLUS;
		String exponentDigits = null;
		currentChar = source.currentChar();
		if (Character.toUpperCase(currentChar) == KeyCharacterConst.E) {
			type = PascalTokenType.REAL;
			text.append(currentChar);
			currentChar = source.nextChar();// consume 'E'
			if (currentChar == KeyCharacterConst.PLUS || currentChar == KeyCharacterConst.MINUS) {
				text.append(currentChar);
				exponentSign = currentChar;
				currentChar = source.nextChar();
			}
			exponentDigits = unsignedIntegerDigits();
		}

		if (type == PascalTokenType.INTEGER) {
			value = computeIntValue(wholeDigits);
		} else if (type == PascalTokenType.REAL) {
			value = computeDecimalValue(wholeDigits, fractionDigits, exponentDigits, exponentSign);
		}
	}

	private BigDecimal computeDecimalValue(String wholeDigits, String fractionDigits, String exponentDigits,
			char exponentSign) {
		// Each part of the number may used for validation purpose, but I
		// just ignore it for now
		BigDecimal bigDecimal = new BigDecimal(text.toString());
		return bigDecimal;
	}

	private BigInteger computeIntValue(String digits) {
		if (digits == null) {
			return BigInteger.valueOf(0l);
		}
		return new BigInteger(digits);
	}

	/**
	 * <pre>
	 * ----->[digit]--->
	 *       |     |
	 *       |--<--|
	 * </pre>
	 * 
	 * @param textBuffer
	 * 
	 * @return
	 * @throws IOException
	 */
	private String unsignedIntegerDigits() throws IOException {
		char currentChar = source.currentChar();
		if (!Character.isDigit(currentChar)) {
			type = PascalTokenType.ERROR;
			value = PascalErrorCode.INVALID_NUMBER;
			text.append(currentChar);
			source.nextChar();// consume the invalid character
			return null;
		}
		StringBuilder digits = new StringBuilder();
		do {
			digits.append(currentChar);
			currentChar = source.nextChar();
		} while (Character.isDigit(currentChar));
		text.append(digits);
		return digits.toString();
	}
}
