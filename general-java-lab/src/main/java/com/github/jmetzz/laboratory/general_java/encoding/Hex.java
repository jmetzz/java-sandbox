package com.github.jmetzz.laboratory.general_java.encoding;


import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Arrays;

public class Hex {

    //table to convert a nibble to a hex char.
    static char[] hexChar = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String encode(byte[] input) {
        StringBuffer buffer = new StringBuffer(input.length * 2);
        for (int i = 0; i < input.length; i++) {
            // look up high nibble char
            buffer.append(hexChar[(input[i] & 0xf0) >>> 4]);
            // look up low nibble char
            buffer.append(hexChar[input[i] & 0x0f]);
        }
        return buffer.toString();
    }

    public static String encode(String input) {
        return String.format("%x", new BigInteger(1, input.getBytes(/*YOUR_CHARSET?*/)));
    }

    public static String encode(String input, Charset charset) {
        return String.format("%x", new BigInteger(1, input.getBytes(charset)));
    }

    public static void main(String[] args) {
        String input = "Isaac Newton";

        System.out.println("Input: " + input);
        byte[] bytes = input.getBytes();
        System.out.print("Bytes: ");
        Arrays.asList(bytes).stream().forEach(System.out::print);
        System.out.println("\n");

        System.out.println("encode(byte[] input) => " + encode(bytes));
        System.out.println("encode(String input) => " + encode(input));
    }


}
