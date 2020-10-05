package me.ranzeplay.simucalc;

import me.ranzeplay.simucalc.calculate.grade.LevelOne;
import me.ranzeplay.simucalc.models.Term;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCalculation {
	@Test
	public void TestLevelOneCalculation() {
		assertEquals(new Term("+02020.00").toString(), LevelOne.Add(new Term("+2000"), new Term("+20")).toString());
		assertEquals(new Term("+02020.00").toString(), LevelOne.Add(new Term("+2040"), new Term("-20")).toString());
		assertEquals(new Term("-02020.00").toString(), LevelOne.Add(new Term("-2000"), new Term("-20")).toString());
		assertEquals(new Term("-02020.00").toString(), LevelOne.Add(new Term("-2040"), new Term("+20")).toString());
	}
}
