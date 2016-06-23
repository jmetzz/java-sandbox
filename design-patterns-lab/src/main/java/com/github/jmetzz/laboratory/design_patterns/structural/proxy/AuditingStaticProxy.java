package com.github.jmetzz.laboratory.design_patterns.structural.proxy;

import com.github.jmetzz.laboratory.design_patterns.structural.proxy.dummyService.ICalculator;

/**
 * Created by Jean Metz.
 * <p>
 * With this example (static) we have to write a separate class
 * for every service we are auditing, so we really are
 * not saving any lines of code.
 * Additionally, our proxy is tightly coupled to the interface
 * it is proxying, so every time a service has to change
 * we have multiple places to make code changes.
 */
public class AuditingStaticProxy implements ICalculator {

    private final ICalculator inner;
    private final Auditor auditor;

    public AuditingStaticProxy(ICalculator inner, Auditor auditor) {
        this.inner = inner;
        this.auditor = auditor;
    }

    @Override
    public int add(int left, int right) {
        auditor.audit("calculator", "before add");
        int result = inner.add(left, right);
        auditor.audit("calculator", "after add");
        return result;
    }
}
