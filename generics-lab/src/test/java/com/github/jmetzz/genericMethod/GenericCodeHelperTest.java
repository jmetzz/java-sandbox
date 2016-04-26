package com.github.jmetzz.genericMethod;


import com.github.jmetzz.genericMethod.pojo.CodeBaseClass;
import com.github.jmetzz.genericMethod.pojo.ContractCode;
import com.github.jmetzz.genericMethod.pojo.CountryCode;
import com.github.jmetzz.genericMethod.pojo.GenderCode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;

import static com.github.jmetzz.genericMethod.pojo.ContractCode.CONTRACT_CODE_PREDICATE;
import static com.github.jmetzz.genericMethod.pojo.GenderCode.COUNTRY_CODE_PREDICATE;
import static com.github.jmetzz.genericMethod.pojo.GenderCode.GENDER_CODE_PREDICATE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@RunWith(Parameterized.class)
public class GenericCodeHelperTest {

    private String constructArg;
    private Class clazz;
    private Predicate predicate;

    @Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList( new Object[][]{
            {CountryCode.class, "BR", COUNTRY_CODE_PREDICATE},
            {GenderCode.class, "Male", GENDER_CODE_PREDICATE},
            {ContractCode.class, "007", CONTRACT_CODE_PREDICATE}
        });
    }

    public GenericCodeHelperTest(Class clazz, String arg, Predicate predicate){
        this.clazz = clazz;
        this.constructArg = arg;
        this.predicate = predicate;
    }


    @Test
    public void shouldCreateContractCode() throws InstantiationException, IllegalAccessException {
        CodeBaseClass c1 = GenericCodeHelper.getCodeFor(clazz, constructArg);
        assertThat(c1)
                .isNotNull()
                .matches(CodeHelper.APPLICATION_PREDICATE)
                .matches(predicate);
        assertThat(c1.getValue()).isEqualTo(constructArg);
    }



}