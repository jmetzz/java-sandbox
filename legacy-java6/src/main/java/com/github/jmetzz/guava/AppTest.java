package com.github.jmetzz.guava;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by exi853 on 03/11/2015.
 */
public class AppTest {


    public static void main(String[] args) {
        List<Character> delimiters = new ArrayList<Character>();
        delimiters.add('<');
        delimiters.add('>');


        CrazySplitter splitter = new CrazySplitter(false, delimiters, 10);

        System.out.println(splitter.split("<name>Test Test<name><price>9.65<price><destination>Brussels<destination> "));


    }
}
