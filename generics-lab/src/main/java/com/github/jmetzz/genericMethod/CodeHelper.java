package com.github.jmetzz.genericMethod;

import com.github.jmetzz.genericMethod.pojo.*;

import java.util.function.Predicate;

public class CodeHelper {

    public static final String APPLICATION = "MyApplication";



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


    public static final Predicate<Code> APPLICATION_PREDICATE = new Predicate<Code>() {
        @Override
        public boolean test(Code code) {
            return code.getApp().compareToIgnoreCase(CodeHelper.APPLICATION) == 0;
        }
    };

    public static final Predicate<Code> CONTRACT_CODE_PREDICATE = new Predicate<Code>() {
        @Override
        public boolean test(Code code) {
            return (code instanceof ContractCode);
        }
    };

    public static final Predicate<Code> COUNTRY_CODE_PREDICATE = new Predicate<Code>() {
        @Override
        public boolean test(Code code) {
            return (code instanceof CountryCode);
        }
    };

    public static final Predicate<Code> GENDER_CODE_PREDICATE = new Predicate<Code>() {
        @Override
        public boolean test(Code code) {
            return (code instanceof GenderCode);
        }
    };

}
