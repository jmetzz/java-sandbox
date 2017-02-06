package com.github.jmetzz.laboratory.mockito.model;

/**
 * Created by jean on 6/02/2017.
 */
public interface Printer {

    void printTestPage();
    void turnOff();
    void print(String s, Integer copies, Boolean collate);


}