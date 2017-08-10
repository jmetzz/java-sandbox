package com.github.jmetzz.booking.code;


public interface Code extends Comparable<Code> {

    String getApp();

    String getValue();

    CodeType getType();

    void setApp(String app);

    void setValue(String value);

}
