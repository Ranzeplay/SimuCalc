package me.ranzeplay.simucalc.calculate.level;

import me.ranzeplay.simucalc.models.Term;
import me.ranzeplay.simucalc.utils.Numbers;

public class LevelOne {
	public static Term Controller(Term a, Term b) {
		return Add(a, b);
	}

	public static Term Add(Term a, Term b) {
		// Pre-check convert un-calculable
		if (a.getOperator() == '-' && b.getOperator() == '-') {
			a.setOperator('+');
			b.setOperator('+');
			Term stageResult = Add(a, b);

			stageResult.setOperator('-');
			return stageResult;
		}

		if (a.getOperator() == '+' && b.getOperator() == '-') {
			if (Numbers.CompareNumberAbs(a.getNumber(), b.getNumber()) < 0) {
				a.setOperator('-');
				b.setOperator('+');
				Term result = Add(b, a);
				result.setOperator('-');
				return result;
			}
		}

		if (a.getOperator() == '-' && b.getOperator() == '+') {
			return Add(b, a);
		}

		// Process (limited conditions)

		// Split integer part and decimal part && Align decimal point

		var pair = Numbers.AlignDecimalPoint(a.getNumber(), b.getNumber());
		String aInteger = pair[0].split("\\.")[0];
		String aDecimal = pair[0].split("\\.")[1];
		String bInteger = pair[1].split("\\.")[0];
		String bDecimal = pair[1].split("\\.")[1];

		var decimalResult = new StringBuilder();
		var integerResult = new StringBuilder();

		// xFlag is needed in both Add operation and Sub operation
		// xFlag describes if 2 single bit calculation needs to be "carry" or "borrow"
		boolean xFlag = false;
		if (a.getOperator() == '+' && b.getOperator() == '+') {
			// Do an Add operation

			// Add decimal part
			for (int i = aDecimal.length() - 1; i >= 0; i--) {
				int ta = aDecimal.charAt(i) - '0';
				int tb = bDecimal.charAt(i) - '0';
				int tr = ta + tb + (xFlag ? 1 : 0);

				xFlag = tr >= 10;
				if (xFlag) tr -= 10;

				decimalResult.insert(0, tr);
			}

			// Add integer part
			for (int i = aInteger.length() - 1; i >= 0; i--) {
				int ta = aInteger.charAt(i) - '0';
				int tb = bInteger.charAt(i) - '0';
				int tr = ta + tb + (xFlag ? 1 : 0);

				xFlag = tr >= 10;
				if (xFlag) tr -= 10;

				integerResult.insert(0, tr);
			}
		} else if (a.getOperator() == '+' && b.getOperator() == '-') {
			// Do a Sub operation

			// Sub decimal part
			for (int i = aDecimal.length() - 1; i >= 0; i--) {
				int ta = aDecimal.charAt(i) - '0';
				int tb = bDecimal.charAt(i) - '0';
				int tr = ta - tb - (xFlag ? 1 : 0);

				xFlag = tr < 0;
				if (xFlag) tr += 10;

				decimalResult.insert(0, tr);
			}

			// Sub integer part
			for (int i = aInteger.length() - 1; i >= 0; i--) {
				int ta = aInteger.charAt(i) - '0';
				int tb = bInteger.charAt(i) - '0';
				int tr = ta - tb - (xFlag ? 1 : 0);

				xFlag = tr < 0;
				if (xFlag) tr += 10;

				integerResult.insert(0, tr);
			}


		}

		return new Term("+" + Numbers.CleanUselessZeros(integerResult.toString() + '.' + decimalResult.toString()));
	}
}
