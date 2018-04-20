/**
 * 
 */
package com.main;

import java.security.SecureRandom;
import java.util.Locale;

/**
 * @author lugupta
 *
 */
public class RandomStringGenerator {

	private static final String UPPER_ALPHAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String LOWER_ALPHAS = UPPER_ALPHAS.toLowerCase(Locale.ROOT);
	private static final String DIGITS = "0123456789";
	private static final String SYMOBOLS = "!@#$%^&*_=+-/";

	public static void main(String[] args) {
		int upperAlphasLen = 1;
		int digitsLen = 1;
		int symbolsLen = 1;
		int minStringLen = 6;
		int maxStringLen = 8;

		for (int i = 0; i < 15; i++) {
			char[] randomString = RandomStringGenerator.generateRandomString(minStringLen, maxStringLen, upperAlphasLen,
					digitsLen, symbolsLen);
			System.out.println("Length = " + randomString.length + ", " + new String(randomString));
		}
	}

	public static char[] generateRandomString(int minStringLen, int maxStringLen, int upperAlphasLen, int digitsLen,
			int symbolsLen) {

		if (minStringLen > maxStringLen) {
			throw new IllegalArgumentException("Minimun length can not be greater than Maximum length");
		} else if ((upperAlphasLen + digitsLen + symbolsLen) > minStringLen) {
			throw new IllegalArgumentException(
					"Minimum length must be more than sum of (Upper Alphas , Digits, Symbols) length");
		}

		SecureRandom secureRandom = new SecureRandom();
		int len = secureRandom.nextInt(maxStringLen - minStringLen + 1) + minStringLen;
		char[] randomString = new char[len];
		int index = 0;

		for (int i = 0; i < upperAlphasLen; i++) {
			index = getNextIndex(secureRandom, len, randomString);
			randomString[index] = UPPER_ALPHAS.charAt(secureRandom.nextInt(UPPER_ALPHAS.length()));
		}

		for (int i = 0; i < digitsLen; i++) {
			index = getNextIndex(secureRandom, len, randomString);
			randomString[index] = DIGITS.charAt(secureRandom.nextInt(DIGITS.length()));
		}

		for (int i = 0; i < symbolsLen; i++) {
			index = getNextIndex(secureRandom, len, randomString);
			randomString[index] = SYMOBOLS.charAt(secureRandom.nextInt(SYMOBOLS.length()));
		}

		for (int i = 0; i < len; i++) {
			if (randomString[i] == 0) {
				randomString[i] = LOWER_ALPHAS.charAt(secureRandom.nextInt(LOWER_ALPHAS.length()));
			}
		}

		return randomString;
	}

	private static int getNextIndex(SecureRandom secureRandom, int len, char[] randomString) {
		int index = secureRandom.nextInt(len);
		while (randomString[index = secureRandom.nextInt(len)] != 0)
			;

		return index;
	}
}