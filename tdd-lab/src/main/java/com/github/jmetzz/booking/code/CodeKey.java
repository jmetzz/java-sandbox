package com.github.jmetzz.booking.code;


import com.github.jmetzz.genericMethod.pojo.BaseCode;

public class CodeKey extends BaseCode {

    private CodeSchema schema;

    private CodeType type;

    public CodeKey(String type) {
        super(type);
    }

    public CodeSchema getSchema() {
        return schema;
    }

}
