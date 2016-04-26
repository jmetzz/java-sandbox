package com.github.jmetzz.genericMethod.pojo;

import java.util.function.Predicate;

public class ContractCode extends CodeBaseClass {


    public static final Predicate<ContractCode> CONTRACT_CODE_PREDICATE = new Predicate<ContractCode>() {
        @Override
        public boolean test(ContractCode code) {
            return code.getType().compareTo(ContractCode.class.getSimpleName()) == 0;
        }
    };

    public ContractCode(){
        super(ContractCode.class.getSimpleName());
    }
}
