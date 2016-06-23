package com.github.jmetzz.laboratory.generics.genericMethod.pojo;

import com.google.common.collect.ComparisonChain;

import java.io.Serializable;

public abstract class BaseCode implements Code, Serializable {

    private final static long serialVersionUID = 99846115699L;

    private final String type;

    private String app;

    private String value;

    public BaseCode(String type) {
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

    public String getType(){
        return this.type;
    }

    @Override
    public int compareTo(Code other) {
        return ComparisonChain.start()
                .compare(this.getApp(),  other.getApp())
                .compare(this.getType(),  other.getType())
                .compare(this.getValue(),  other.getValue())
                .result();
    }


}
