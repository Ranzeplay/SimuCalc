package me.ranzeplay.simucalc.models;

public class Term {
    private String raw;
    private char operator;
    private String number;

    public Term(String raw) {
        this.raw = raw;

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

    public String getRaw() {
        return raw;
    }

    @Override
    public String toString(){
        raw = operator + number;
        return raw;
    }
}
