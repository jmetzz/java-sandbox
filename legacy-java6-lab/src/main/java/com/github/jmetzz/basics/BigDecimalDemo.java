package com.github.jmetzz.basics;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Created by exi853 on 08/03/2016.
 */
public class BigDecimalDemo {

    public static void main(String[] args) {
        // create 3 BigDecimal objects
        BigDecimal bg1, bg2, bg3;

        MathContext mc = new MathContext(5); // 2 precision

        bg1 = new BigDecimal("100.123");
        bg2 = new BigDecimal("50.123");

        // subtract bg1 with bg2 using mc and assign result to bg3
        bg3 = bg1.subtract(bg2, mc);

        String str = "The Result of Subtraction is " + bg3;

        // print bg3 value
        System.out.println( str );

    }
}
