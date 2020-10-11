package me.ranzeplay.simucalc;

import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Constants {
	// Map<Operator, calculationLevel>
	public static final Map<Character, Integer> Operators = Stream.of(
			new AbstractMap.SimpleEntry<>('+', 1),
			new AbstractMap.SimpleEntry<>('-', 1),
			new AbstractMap.SimpleEntry<>('*', 2),
			new AbstractMap.SimpleEntry<>('/', 2),
			new AbstractMap.SimpleEntry<>('^', 3))
			.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
}
