package me.ranzeplay.simucalc.utils;

public class Numbers {
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

	/**
	 * Move decimal point left or right
	 *
	 * @param number a decimal number
	 * @param digit  how many "position" to move, a negative number to move the point left, a positive number to move the point right
	 * @return the number with moved decimal point
	 */
	public static String MoveDecimalPoint(String number, int digit) {
		int decimalPointPos = number.indexOf('.');
		int targetPosition = decimalPointPos + digit;

		if (targetPosition <= 0) {
			return MoveDecimalPoint("0".repeat(Math.abs(targetPosition + 5)) + number, digit);
		}
		if (targetPosition >= number.length()) {
			return MoveDecimalPoint(number + "0".repeat(Math.abs(targetPosition + 5)), digit);
		}

		// Remove decimal point
		number = number.replaceAll("\\.", "");
		var result = new StringBuilder(number);
		result.insert(targetPosition, ".");

		return result.toString();
	}

	// To clean 0s on the left and right
	// e.g. 02.350 -> 2.35    002.0690 -> 2.069
	public static String CleanUselessZeros(String numberAbs) {
		String regex = "^0*|0*$";
		numberAbs = numberAbs.replaceAll(regex, "");

		if (numberAbs.startsWith(".")) {
			numberAbs  = "0" + numberAbs;
		}
		if (numberAbs.endsWith(".")) {
			numberAbs = numberAbs + "0";
		}

		return numberAbs;
	}
}
