package com.github.jmetzz.laboratory.structural.proxy;

import com.github.jmetzz.laboratory.structural.proxy.dummyService.CalculatorImpl;
import com.github.jmetzz.laboratory.structural.proxy.dummyService.ConsolePrinter;
import com.github.jmetzz.laboratory.structural.proxy.dummyService.ICalculator;
import com.github.jmetzz.laboratory.structural.proxy.dummyService.IPrinter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by Jean Metz.
 */
public class App {

    public static void main(String[] args) {

        // can be used for many different dynamic proxies, each implementing
        // a different interface.
        Auditor auditor = new Auditor();

        // service to proxy
        ICalculator calculator = new CalculatorImpl();

        // --- Testing static auditor ---

        AuditingStaticProxy staticProxy = new AuditingStaticProxy(calculator, auditor);

        calculator.add(1, 1); // Will not be audited
        staticProxy.add(3, 3); // Will be audited

        // --- Testing dynamic auditor ---
        InvocationHandler calculatorAuditor = new AuditingDynamicProxy(auditor, calculator);

        // dynamically generates a class that claims to implement Calculator.
        // called methods are routed to the invocation handler.
        // The handler is associated to a generic object, in this case the
        // calculator object.
        ICalculator calculatorProxy = (ICalculator) Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader(), new Class[]{ICalculator.class}, calculatorAuditor);

        calculator.add(2, 2); // Will not be audited
        calculatorProxy.add(2, 2); // Will be audited

        // reuse the same auditor object to instantiate another dynamic proxy
        ConsolePrinter printer = new ConsolePrinter();
        InvocationHandler printerAuditor = new AuditingDynamicProxy(auditor, printer);
        IPrinter printerProxy = (IPrinter) Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader(), new Class[]{IPrinter.class}, printerAuditor);

        printer.printMsg("Hello World"); // Will not be audited
        printerProxy.printMsg("2nd Hello World");// Will be audited
    }
}
