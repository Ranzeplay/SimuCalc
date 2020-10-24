package me.ranzeplay.simucalc;

import me.ranzeplay.simucalc.utils.Numbers;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestNumbers {
	@Test
	void TestAlignDecimalPoint() {
		var alignResult = Numbers.AlignDecimalPoint("0.02", "10.247");

		assertEquals("000.0200", alignResult[0]);
		assertEquals("010.2470", alignResult[1]);
	}

	@Test
	void TestCompareNumberAbs() {
		assertEquals(0, Numbers.CompareNumberAbs("2.5", "2.50"));
		assertEquals(1, Numbers.CompareNumberAbs("30.5", "2.50"));
		assertEquals(-1, Numbers.CompareNumberAbs("0.002", "70.0"));
	}

	@Test
	void TestMoveDecimalPoint() {
		assertEquals("34.5678", Numbers.MoveDecimalPoint("345.678", -1));
		assertEquals("34567800.00000000000", Numbers.MoveDecimalPoint("345.678", 5));
		assertEquals("0.00345678", Numbers.MoveDecimalPoint("345.678", -5));
	}

	@Test
	void TestCleanUselessZeros() {
		assertEquals("0.0", Numbers.CleanUselessZeros("00000.000000000000"));
		assertEquals("432.321", Numbers.CleanUselessZeros("00432.321000"));
		assertEquals("4320.00321", Numbers.CleanUselessZeros("004320.00321000"));
		assertEquals("600.0", Numbers.CleanUselessZeros("0600.0"));
	}
}
