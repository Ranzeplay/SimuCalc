package me.ranzeplay.simucalc;

import me.ranzeplay.simucalc.utils.Simulation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUtils {
	@Test
	public void TestAlignDecimalPoint() {
		var alignResult = Simulation.AlignDecimalPoint("0.02", "10.247");

		assertEquals("000.0200", alignResult[0]);
		assertEquals("010.2470", alignResult[1]);
	}

	@Test
	public void TestCompareNumberAbs() {
		assertEquals(0, Simulation.CompareNumberAbs("2.5", "2.50"));
		assertEquals(1, Simulation.CompareNumberAbs("30.5", "2.50"));
		assertEquals(-1, Simulation.CompareNumberAbs("0.002", "70.0"));
	}

	@Test
	public void TestMoveDecimalPoint() {
		assertEquals("34.5678", Simulation.MoveDecimalPoint("345.678", -1));
		assertEquals("34567800.00000000000", Simulation.MoveDecimalPoint("345.678", 5));
		assertEquals("0.00345678", Simulation.MoveDecimalPoint("345.678", -5));
	}
}
