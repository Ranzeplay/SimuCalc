package me.ranzeplay.simucalc.calculate.level;

import me.ranzeplay.simucalc.models.Term;

public class LevelThree {
	public static Term Power(Term base, Term exponent) {
		Term result = new Term("+1");

		for (; !exponent.getNumber().equals("0.0"); exponent = LevelOne.Add(exponent, new Term("-1"))) {
			result = LevelTwo.Times(result, base);
		}

		return result;
	}
}
