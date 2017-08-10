package com.github.jmetzz.booking.code;

import com.google.common.collect.ComparisonChain;

import java.io.Serializable;

public abstract class BaseCode implements Code, Serializable {

    private final static long serialVersionUID = 99846115699L;

    private final CodeType type;

    private String app;

    private String value;

    public BaseCode(CodeType type) {
        this.type = type;
    }


    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public CodeType getType(){
        return this.type;
    }


    public int compareTo(Code other) {
        return ComparisonChain.start()
                .compare(this.getApp(),  other.getApp())
                .compare(this.getType(),  other.getType())
                .compare(this.getValue(),  other.getValue())
                .result();
    }


}
