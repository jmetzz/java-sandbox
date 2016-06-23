package com.github.jmetzz.laboratory.design_patterns.demo.generic_calculator.parser;

import com.github.jmetzz.laboratory.design_patterns.demo.generic_calculator.operators.Operator;
import com.google.common.base.MoreObjects;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public abstract class Parser implements IParser {

    private Set<Operator> operators;
    private Set<String> symbols;
    protected char sep;

    public Parser() {
        operators = new HashSet<Operator>();
        symbols = new HashSet<String>();

        DecimalFormat format = (DecimalFormat) DecimalFormat.getInstance();
        DecimalFormatSymbols symbols = format.getDecimalFormatSymbols();
        sep = symbols.getDecimalSeparator();
    }

    public void registerOperator(Operator op) {
        operators.add(op);
        symbols.add(op.getSimbol());
    }

    public void registerOperatorTable(Collection<Operator> opTable) {
        for (Operator op : opTable) {
            registerOperator(op);
        }
    }

    public boolean isOperator(Operator op) {
        return operators.contains(op);
    }

    public boolean isOperator(String symbol) {
        return getOperator(symbol) != null;
    }

    public Operator getOperator(String token) {
        for (Operator op : operators) {
            if (op.getSimbol().compareTo(token) == 0) {
                return op;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("operators", operators)
                .add("symbols", symbols)
                .add("separator", sep)
                .toString();
    }

    public Set<Operator> getOperators() {
        return operators;
    }

    public Set<String> getSymbols() {
        return symbols;
    }
}
