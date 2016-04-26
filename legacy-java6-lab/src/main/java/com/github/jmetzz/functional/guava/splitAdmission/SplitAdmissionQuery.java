package com.github.jmetzz.functional.guava.splitAdmission;

public class SplitAdmissionQuery {

    protected SplitAdmissionSpec splitAdmissionSpec;

    public SplitAdmissionQuery(SplitAdmissionSpec splitAdmissionSpec) {
        this.splitAdmissionSpec = splitAdmissionSpec;
    }

    public SplitAdmissionSpec getSplitAdmissionSpec() {
        return splitAdmissionSpec;
    }
}
