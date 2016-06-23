package com.github.jmetzz.laboratory.design_patterns.demo.generic_calculator.operators;

public abstract class Operator implements Comparable<Operator> {

	private String name;
	private String symbol;
	private Associativity associativity;
	private int precedence;

	public Operator(String name, String symbol, Associativity ass, int prec) {
		this.name = name;
		this.symbol = symbol;
		this.associativity = ass;
		this.precedence = prec;
	}

	public abstract Double eval(Double... operands) throws IllegalArgumentException;

	public String getName() {
		return name;
	}

	public String getSimbol() {
		return symbol;
	}

	public Associativity getAssociativity() {
		return associativity;
	}

	public int getIntAssociativity() {
		return associativity.ordinal();
	}

	public int getPrecedence() {
		return precedence;
	}

	@Override
	public String toString() {
		return "Operator [name=" + name + ", simbol=" + symbol
				+ ", associativity=" + associativity + ", precedence="
				+ precedence + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((associativity == null) ? 0 : associativity.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + precedence;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Operator other = (Operator) obj;
		if (associativity != other.associativity)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (precedence != other.precedence)
			return false;
		return true;
	}

	public int compareTo(Operator other) {
		return this.precedence - other.precedence;
	}

}
