package com.github.jmetzz.laboratory.mockito.business;

/**
 * Created by jean on 6/02/2017.
 */
public class PrinterDiagnostics {

    private Printer printer;

    public PrinterDiagnostics(Printer printer) {
        this.printer = printer;
    }

    public void diagnosticPrint(String text, Integer copies, Boolean collate){
        StringBuilder diagnostic = new StringBuilder();
        diagnostic.append("**\t\t Diagnostic Print \t***\n");
        diagnostic.append("****\t Copies: ").append(copies).append("\t\t\t***\n");
        diagnostic.append("****\t Collate: ").append(collate).append("\t\t***\n");
        diagnostic.append("*******************************\n\n");
        printer.print(new StringBuilder().append(diagnostic).append(text).toString(), copies, collate);
    }

    public void diagnosticAndOriginalPrint(String text, Integer copies, Boolean collate) {
        diagnosticPrint(text, copies, collate);
        printer.print(text, copies, collate);
    }

}
