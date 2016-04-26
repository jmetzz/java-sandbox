package com.github.jmetzz.genericMethod;

import com.github.jmetzz.genericMethod.pojo.CodeBaseClass;
import com.github.jmetzz.genericMethod.pojo.ContractCode;
import com.github.jmetzz.genericMethod.pojo.CountryCode;
import com.github.jmetzz.genericMethod.pojo.GenderCode;

import java.util.function.Predicate;

public class CodeHelper {

    public static final String APPLICATION = "MyApplication";

    public static final Predicate<CodeBaseClass> APPLICATION_PREDICATE = new Predicate<CodeBaseClass>() {
        @Override
        public boolean test(CodeBaseClass code) {
            return code.getApp().compareToIgnoreCase(CodeHelper.APPLICATION) == 0;
        }
    };

    public static CountryCode getCountryCode(String codeValue) {
        CountryCode code = new CountryCode();
        code.setApp(APPLICATION);
        code.setValue(codeValue);
        return code;
    }

    public static ContractCode getContractCode(String codeValue) {
        ContractCode code = new ContractCode();
        code.setApp(APPLICATION);
        code.setValue(codeValue);
        return code;
    }


    public static GenderCode getGenderCode(String codeValue) {
        GenderCode code = new GenderCode();
        code.setApp(APPLICATION);
        code.setValue(codeValue);
        return code;
    }


}
