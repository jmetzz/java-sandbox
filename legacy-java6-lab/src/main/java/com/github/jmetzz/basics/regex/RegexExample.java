package com.github.jmetzz.basics.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by exi853 on 12/04/2016.
 */
public class RegexExample {


    private static final String PATTERN_PHONENUMBER_STRING = "^\\+\\d{11}$";
    private static Pattern pattern = Pattern.compile(PATTERN_PHONENUMBER_STRING);


    public static void main(String[] args) {

        String phoneNumber = "+32489555555";
        String name = "John Doe";

        validatePhoneNumber(phoneNumber);
        validatePhoneNumber(name);


    }

    private static void validatePhoneNumber(String phoneNumber) {
        Matcher matcher = pattern.matcher(phoneNumber);
        if(matcher.matches()){
            System.out.println("Correct phone number: " + phoneNumber);
        } else {
            System.out.println("Incorrect phone number: " + phoneNumber);
        }
    }


}
