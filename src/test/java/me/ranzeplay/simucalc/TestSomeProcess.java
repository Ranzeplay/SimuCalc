package me.ranzeplay.simucalc;

import me.ranzeplay.simucalc.calculate.SomeProcess;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSomeProcess {
	@Test
	public void TestCleanUselessZeros() {
		assertEquals("0.0", SomeProcess.CleanUselessZeros("00000.000000000000"));
		assertEquals("432.321", SomeProcess.CleanUselessZeros("00432.321000"));
		assertEquals("4320.00321", SomeProcess.CleanUselessZeros("004320.00321000"));
		assertEquals("600.0", SomeProcess.CleanUselessZeros("0600.0"));
	}
}
