package com.github.jmetzz.laboratory.generics.genericMethod.pojo;


public interface Code extends Comparable<Code> {

    String getApp();

    String getValue();

    String getType();

    void setApp(String app);

    void setValue(String value);

}
