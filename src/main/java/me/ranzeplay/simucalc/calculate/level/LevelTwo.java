package me.ranzeplay.simucalc.calculate.level;

import me.ranzeplay.simucalc.models.Term;
import me.ranzeplay.simucalc.utils.Numbers;

import java.util.ArrayList;

public class LevelTwo {
	public static Term Controller(Term a, Term b) {
		if (b.getOperator() == '*') {
			b.setOperator(b.isNegative() ? '-' : '+');

			return Times(a, b);
		} else if (b.getOperator() == '/') {
			return null;
		} else {
			return null;
		}
	}

	public static Term Times(Term a, Term b) {
		// Pre-check convert un-calculable
		if (a.getOperator() != b.getOperator()) {
			a.setOperator('+');
			b.setOperator('+');

			Term result = Times(a, b);
			result.setOperator('-');

			return result;
		}

		if (a.getOperator() == '-' && b.getOperator() == '-') {
			a.setOperator('+');
			b.setOperator('+');

			return Times(a, b);
		}

		// How many decimal numbers are there in 2 numbers?
		// e.g. 2.00 -> 2    6.024 -> 3
		int aDecimalLength = a.getNumber().substring(a.getNumber().indexOf('.')).length() - 1;
		int bDecimalLength = b.getNumber().substring(b.getNumber().indexOf('.')).length() - 1;

		String aNumberWithoutDecimalPoint = a.getNumber().replace(".", "");
		String bNumberWithoutDecimalPoint = b.getNumber().replace(".", "");

		ArrayList<String> verticalCalcStore = new ArrayList<>();

		int posLevel = 0;
		for (int i = bNumberWithoutDecimalPoint.length() - 1; i >= 0; i--, posLevel++) {
			String currentCalc = "0.0";
			for (int j = 0; j < Integer.parseInt(String.valueOf(bNumberWithoutDecimalPoint.charAt(i))); j++) {
				Term digitResult = LevelOne.Add(new Term("+" + currentCalc), new Term("+" + aNumberWithoutDecimalPoint));
				currentCalc = digitResult.getNumber();
			}

			verticalCalcStore.add(new StringBuilder(currentCalc).insert(currentCalc.indexOf('.') - 1, "0".repeat(posLevel)).toString());
		}

		Term result = new Term("+0");
		for (String s : verticalCalcStore) {
			result = LevelOne.Add(result, new Term("+" + s));
		}

		result.setNumber(Numbers.MoveDecimalPoint(result.getNumber(), -(aDecimalLength + bDecimalLength)));
		result.setNumber(Numbers.CleanUselessZeros(result.getNumber()));

		return result;
	}
}
 