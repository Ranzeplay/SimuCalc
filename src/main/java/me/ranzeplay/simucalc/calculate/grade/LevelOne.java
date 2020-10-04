package me.ranzeplay.simucalc.calculate.grade;

import me.ranzeplay.simucalc.models.Term;

public class LevelOne {
	public static Term Add(Term a, Term b) {
		// Pre-check convert un-calculable
		if (a.getOperator() == '-' && b.getOperator() == '-') {
			a.setOperator('+');
			b.setOperator('+');
			Term stageResult = Add(a, b);

			stageResult.setOperator('-');
			return stageResult;
		}

		if (a.getOperator() == '-' && b.getOperator() == '+') {
			return Add(b, a);
		}

		// Process

		// Split integer part and decimal part && Align decimal point
		StringBuilder aInteger = new StringBuilder("0" + a.getNumber().substring(0, a.getNumber().indexOf('.')));
		StringBuilder bInteger = new StringBuilder("0" + b.getNumber().substring(0, b.getNumber().indexOf('.')));
		if (aInteger.length() > bInteger.length()) {
			bInteger.insert(0, "0".repeat(aInteger.length() - bInteger.length() + 2), 0, aInteger.length() - bInteger.length());
		} else if (aInteger.length() < bInteger.length()) {
			aInteger.insert(0, "0".repeat(bInteger.length() - aInteger.length() + 2), 0, bInteger.length() - aInteger.length());
		}

		StringBuilder aDecimal = new StringBuilder(a.getNumber().substring(a.getNumber().indexOf('.') + 1));
		StringBuilder bDecimal = new StringBuilder(b.getNumber().substring(b.getNumber().indexOf('.') + 1));
		if (aDecimal.length() > bDecimal.length()) {
			bDecimal.insert(0, "0".repeat(aDecimal.length() - bDecimal.length() + 2), bDecimal.length(), aDecimal.length() - bDecimal.length() + 1);
		} else if (bDecimal.length() > aDecimal.length()) {
			aDecimal.insert(0, "0".repeat(bDecimal.length() - aDecimal.length() + 2), aDecimal.length(), bDecimal.length() - aDecimal.length() + 1);
		}

		// xFlag is needed in both Add operation and Sub operation
		boolean xFlag = false;
		if (a.getOperator() == '+' && b.getOperator() == '+') {
			// Do an Add operation

			// Add decimal part
			StringBuilder decimalResult = new StringBuilder();
			for (int i = aDecimal.length() - 1; i >= 0; i--) {
				int ta = aDecimal.charAt(i) - '0';
				int tb = bDecimal.charAt(i) - '0';
				int tr = ta + tb + (xFlag ? 1 : 0);

				xFlag = tr >= 10;
				if (xFlag) tr += 10;

				decimalResult.insert(0, tr);
			}

			// Add integer part
			StringBuilder integerResult = new StringBuilder();
			for (int i = aInteger.length() - 1; i >= 0; i--) {
				int ta = aInteger.charAt(i) - '0';
				int tb = bInteger.charAt(i) - '0';
				int tr = ta + tb + (xFlag ? 1 : 0);

				xFlag = tr >= 10;
				if (xFlag) tr += 10;

				integerResult.insert(0, tr);
			}

			return new Term("+" + integerResult.toString() + '.' + decimalResult.toString());
		} else if (a.getOperator() == '+' && b.getOperator() == '-') {
			// Do a Sub operation

			// Sub decimal part
			StringBuilder decimalResult = new StringBuilder();
			for (int i = aDecimal.length() - 1; i >= 0; i--) {
				int ta = aDecimal.charAt(i) - '0';
				int tb = bDecimal.charAt(i) - '0';
				int tr = ta - tb - (xFlag ? 1 : 0);

				xFlag = tr <= -10;
				if (xFlag) tr += 10;

				decimalResult.insert(0, tr);
			}

			// Sub integer part
			StringBuilder integerResult = new StringBuilder();
			for (int i = aInteger.length() - 1; i >= 0; i--) {
				int ta = aInteger.charAt(i) - '0';
				int tb = bInteger.charAt(i) - '0';
				int tr = ta - tb - (xFlag ? 1 : 0);

				xFlag = tr <= 10;
				if (xFlag) tr += 10;

				integerResult.insert(0, tr);
			}

			return new Term("+" + integerResult.toString() + '.' + decimalResult.toString());
		}

		throw new IllegalArgumentException();
	}
}
