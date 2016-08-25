package com.github.jmetzz.laboratory.general_java;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CmdInputReaderExample {


    public static void main(String[] args) {


        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            System.out.println("Enter some text (q to leave) :");
            while(true) {

                String input = br.readLine();
                if ("q".equals(input)) {
                    System.out.println("Bye");
                    System.exit(0);
                }

                System.out.println("given input: " + input);
                System.out.println("----------------------\n");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
