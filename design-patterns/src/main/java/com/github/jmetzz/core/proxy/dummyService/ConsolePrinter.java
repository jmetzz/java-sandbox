package com.github.jmetzz.core.proxy.dummyService;

/**
 * Created by Jean Metz.
 */
public class ConsolePrinter implements IPrinter {

    public void printMsg(String msg) {
        System.out.println(msg);
    }

}
