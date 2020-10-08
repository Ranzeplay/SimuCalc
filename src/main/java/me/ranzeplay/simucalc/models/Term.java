package me.ranzeplay.simucalc.models;

public class Term {
	private char operator;
	private String number;

	public Term(String raw) {
		if (!raw.contains(".")) {
			// Means the number is an integer (no decimal point)
			raw = raw + ".0";
		}

		this.operator = raw.charAt(0);
		this.number = raw.substring(1);
	}

	public char getOperator() {
		return operator;
	}

	public void setOperator(char operator) {
		this.operator = operator;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return operator + number;
	}
}
