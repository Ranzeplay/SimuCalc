package me.ranzeplay.simucalc;

import me.ranzeplay.simucalc.calculate.level.LevelOne;
import me.ranzeplay.simucalc.calculate.level.LevelThree;
import me.ranzeplay.simucalc.calculate.level.LevelTwo;
import me.ranzeplay.simucalc.models.Term;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCalculation {
	@Test
	void TestLevelOneCalculation() {
		assertEquals(new Term("+2020.0").toString(), LevelOne.Add(new Term("+2000"), new Term("+20")).toString());
		assertEquals(new Term("+2020.0").toString(), LevelOne.Add(new Term("+2040"), new Term("-20")).toString());
		assertEquals(new Term("-2020.0").toString(), LevelOne.Add(new Term("-2000"), new Term("-20")).toString());
		assertEquals(new Term("-2020.0").toString(), LevelOne.Add(new Term("-2040"), new Term("+20")).toString());

		assertEquals(new Term("+540.0").toString(), LevelOne.Add(new Term("+270"), new Term("+270")).toString());
		assertEquals(new Term("+10000.0").toString(), LevelOne.Add(new Term("+9999.999"), new Term("+0.001")).toString());
	}

	@Test
	void TestLevelTwoCalculation() {
		assertEquals("+6.0", LevelTwo.Times(new Term("+3"), new Term("+2")).toString());
		assertEquals("+81.0", LevelTwo.Times(new Term("+27"), new Term("+3")).toString());

		assertEquals("+1024.0", LevelTwo.Times(new Term("-32"), new Term("-32")).toString());
		assertEquals("-1024.0", LevelTwo.Times(new Term("+32"), new Term("-32")).toString());
	}

	@Test
	void TestLevelThreeCalculation() {
		assertEquals("+4.0", LevelThree.Power(new Term("+2"), new Term("+2")).toString());
		assertEquals("+1024.0", LevelThree.Power(new Term("+2"), new Term("+10")).toString());
		assertEquals("-8.0", LevelThree.Power(new Term("-2"), new Term("+3")).toString());
		assertEquals("+1.0", LevelThree.Power(new Term("-1"), new Term("+0")).toString());
	}
}
