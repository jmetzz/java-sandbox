package com.github.jmetzz.genericMethod.pojo;

import java.io.Serializable;

public class BaseCode implements Serializable {

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
}
