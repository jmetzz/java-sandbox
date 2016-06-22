package com.github.jmetzz.laboratory.structural.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by Jean Metz.
 * <p>
 * we can use a dynamic proxy to save ourselves
 * the work of writing an auditing proxy for
 * every service we need to audit.
 * We start by creating an "invocation handler",
 * which is a class that can accept any method
 * call and take some action based on it
 */
public class AuditingDynamicProxy implements InvocationHandler {
    private final Auditor auditor;
    private final Object target;

    public AuditingDynamicProxy(Auditor auditor, Object target) {
        this.auditor = auditor;
        this.target = target;
    }

    /* accept any method call and take some action.
     * This example does not make very good audit logs,
     * but note that we can pass this invocation handler
     * any data through its constructor,
     * so if we need to improve the behavior we can */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        auditor.audit(target.getClass().getName(), "before " + method.getName());
        Object returnObject = method.invoke(target, args);
        auditor.audit(target.getClass().getName(), "after " + method.getName());
        return returnObject;
    }
}
