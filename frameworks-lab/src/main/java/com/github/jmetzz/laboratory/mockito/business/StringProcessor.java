package com.github.jmetzz.laboratory.mockito.business;

import com.github.jmetzz.laboratory.mockito.exception.PrinterNotConnectedException;

import java.util.Optional;

/**
 * Created by jean on 6/02/2017.
 */
public class StringProcessor {

    private Printer printer;
    private String currentBuffer;

    public StringProcessor(Printer printer) {
        this.printer = printer;
    }

    public Optional<String> statusAndTest() throws PrinterNotConnectedException {
        printer.printTestPage();
        return Optional.ofNullable(currentBuffer);
    }
}
