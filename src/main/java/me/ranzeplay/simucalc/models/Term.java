package me.ranzeplay.simucalc.models;

public class Term {
	private char operator;
	private String number;
	private boolean isNegative;

	public Term(String raw) {
		if (!raw.contains(".")) {
			// Means the number is an integer (no decimal point)
			raw = raw + ".0";
		}

		this.operator = raw.charAt(0);
		this.number = raw.substring(1);
	}

	public Term(String raw, boolean isNegative) {
		if (!raw.contains(".")) {
			// Means the number is an integer (no decimal point)
			raw = raw + ".0";
		}

		this.operator = raw.charAt(0);
		this.number = raw.substring(1);

		this.isNegative = isNegative;
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

	public boolean isNegative() {
		return isNegative;
	}

	public void setNegative(boolean negative) {
		isNegative = negative;
	}

	@Override
	public String toString() {
		return operator + number;
	}
}
