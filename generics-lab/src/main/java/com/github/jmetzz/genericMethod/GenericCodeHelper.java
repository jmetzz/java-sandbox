package com.github.jmetzz.genericMethod;

import com.github.jmetzz.genericMethod.pojo.CodeBaseClass;

public class GenericCodeHelper {

    private static final String APPLICATION = "MyApplication";

    public static <T extends CodeBaseClass> T getCodeFor(Class<T> clazz, String codeValue) throws IllegalAccessException, InstantiationException {
        T code = clazz.newInstance();
        code.setApp(APPLICATION);
        code.setValue(codeValue);
        return code;
    }


}
