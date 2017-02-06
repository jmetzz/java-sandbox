package com.github.jmetzz.laboratory.mockito.business;

import com.github.jmetzz.laboratory.mockito.exception.PrinterNotConnectedException;

/**
 * Created by jean on 6/02/2017.
 */
public interface Printer {

    void printTestPage() throws PrinterNotConnectedException;
    void turnOff();
    void print(String s, Integer copies, Boolean collate) throws PrinterNotConnectedException;


}