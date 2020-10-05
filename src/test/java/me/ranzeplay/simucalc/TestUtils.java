package me.ranzeplay.simucalc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUtils {
	@Test
	public void TestAlignDecimalPoint() {
		var alignResult = me.ranzeplay.simucalc.utils.Simulation.AlignDecimalPoint("0.02", "10.247");

		assertEquals("000.0200", alignResult[0]);
		assertEquals("010.2470", alignResult[1]);
	}
}
