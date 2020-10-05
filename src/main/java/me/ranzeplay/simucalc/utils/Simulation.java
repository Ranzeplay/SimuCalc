package me.ranzeplay.simucalc.utils;

import java.util.AbstractMap;

public class Simulation {
	/**
	 * To compare numbers in String which is bigger
	 *
	 * @param a First number, be positive
	 * @param b Last number, be positive
	 * @return 0 means they are equal, -1 means b is bigger than a, 1 means a is bigger than b
	 */
	public static int CompareNumberAbs(String a, String b) {

		String[] pair = AlignDecimalPoint(a, b);
		a = pair[0].replaceAll("\\.", "");
		b = pair[1].replaceAll("\\.", "");

		if (a.length() > b.length()) return 1;
		if (a.length() < b.length()) return -1;

		for (int i = 0; i < a.length(); i++) {
			if ((int) a.toCharArray()[i] > (int) b.toCharArray()[i]) return 1;
			if ((int) a.toCharArray()[i] < (int) b.toCharArray()[i]) return -1;
		}

		return 0;
	}

	public static String[] AlignDecimalPoint(String a, String b) {
		// Integer part
		var aInteger = new StringBuilder("0" + a.substring(0, a.indexOf('.')));
		var bInteger = new StringBuilder("0" + b.substring(0, b.indexOf('.')));
		if (aInteger.length() > bInteger.length()) {
			bInteger.insert(0, "0".repeat(aInteger.length() - bInteger.length() + 2), 0, aInteger.length() - bInteger.length());
		} else if (aInteger.length() < bInteger.length()) {
			aInteger.insert(0, "0".repeat(bInteger.length() - aInteger.length() + 2), 0, bInteger.length() - aInteger.length());
		}

		// Decimal part
		var aDecimal = new StringBuilder(a.substring(a.indexOf('.') + 1) + "0");
		var bDecimal = new StringBuilder(b.substring(b.indexOf('.') + 1) + "0");
		if (aDecimal.length() > bDecimal.length()) {
			bDecimal.append("0".repeat(aDecimal.length() - bDecimal.length()));
		} else if (bDecimal.length() > aDecimal.length()) {
			aDecimal.append("0".repeat(bDecimal.length() - aDecimal.length()));
		}

		return new String[]{aInteger + "." + aDecimal, bInteger + "." + bDecimal};
	}
}
