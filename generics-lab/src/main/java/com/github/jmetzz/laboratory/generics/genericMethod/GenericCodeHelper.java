package com.github.jmetzz.laboratory.generics.genericMethod;

import com.github.jmetzz.laboratory.generics.genericMethod.pojo.BaseCode;

public class GenericCodeHelper {

    private static final String APPLICATION = "MyApplication";

    public static <T extends BaseCode> T getCodeFor(Class<T> clazz, String codeValue) throws IllegalAccessException, InstantiationException {
        T code = clazz.newInstance();
        code.setApp(APPLICATION);
        code.setValue(codeValue);
        return code;
    }


}
