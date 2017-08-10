package com.github.jmetzz.laboratory.design_patterns.creational.factory;


import com.github.jmetzz.laboratory.design_patterns.demo.generic_calculator.operators.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


/**
 * Some benefits:
 * - code for interface rather than implementation
 * - removes the instantiation of actual implementation classes from client code
 * - more robust, less coupled and easy to extend
 *
 * JDK examples:  java.util.Calendar, ResourceBundle and NumberFormat getInstance() methods ...
 *
 * */
public class OperatorFactory {

    public static Operator getOperator(String type, String symbol){
        switch (type){
            case Sum.OPERATOR_SUM:
                return new Sum(symbol);
            case Sub.OPERATOR_SUBTRACTION:
                return new Sub(symbol);
            case Multiplication.OPERATOR_MULTIPLICATION:
                return new Multiplication(symbol);
            case Division.OPERATOR_DIVISION:
                return new Division(symbol);
            default:
                throw new IllegalArgumentException("Unknown operator type ");
        }
    }

    public static <T extends Operator> T getOperatorGeneric(Class<T> clazz, String symbol) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<T> constructor = clazz.getConstructor(String.class);
        return constructor.newInstance(symbol);
    }



}
