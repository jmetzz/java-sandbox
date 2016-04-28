package com.github.jmetzz.genericMethod.pojo;


import java.util.function.Predicate;

public class GenderCode extends BaseCode {

    public static final Predicate<CountryCode> COUNTRY_CODE_PREDICATE = new Predicate<CountryCode>() {
        @Override
        public boolean test(CountryCode code) {
            return code.getType().compareTo(CountryCode.class.getSimpleName()) == 0;
        }
    };


    public static final Predicate<GenderCode> GENDER_CODE_PREDICATE = new Predicate<GenderCode>() {
        @Override
        public boolean test(GenderCode code) {
            return code.getType().compareTo(GenderCode.class.getSimpleName()) == 0;
        }
    };
    public GenderCode() {
        super(GenderCode.class.getSimpleName());
    }
}
