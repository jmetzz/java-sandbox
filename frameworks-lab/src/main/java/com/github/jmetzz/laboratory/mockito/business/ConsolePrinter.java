package com.github.jmetzz.laboratory.mockito.business;

/**
 * Created by jean on 6/02/2017.
 */
public class ConsolePrinter implements Printer {
    @Override
    public void printTestPage() {
        System.out.println("This is a test page");
    }

    @Override
    public void turnOff() {
        System.out.println("Printer if Off");
    }

    @Override
    public void print(String s, Integer copies, Boolean collate) {
        StringBuffer sb = new StringBuffer();
        for(int i =0 ; i < copies; i++){
            sb.append(s);
            if (!collate)
                sb.append( "\n");
        }
        System.out.println(sb.toString());
    }
}
