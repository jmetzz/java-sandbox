package com.github.jmetzz.functional.guava.splitAdmission;

import com.github.jmetzz.functional.guava.splitAdmission.builder.SplitAdmissionBuilder;
import com.github.jmetzz.functional.guava.splitAdmission.pojo.SplitAdmission;
import com.google.common.base.Optional;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class SplitAdmissionCommandTest {

    private static List<SplitAdmission> splitAdmissionList;

    @BeforeClass
    public static void init(){
        splitAdmissionList = new ArrayList<SplitAdmission>();

        splitAdmissionList.add(SplitAdmissionBuilder.getInstance()
                .withId(1L)
                .withOriginLike("Bru%")
                .withDestinationLike("Cope%")
                .withConfigProfile("")
                .withTariffGroup("New tariff group")
                .withDistributor("")
                .createSplitAdmission());

        splitAdmissionList.add(SplitAdmissionBuilder.getInstance()
                .withId(2L)
                .withOriginLike("Brussels")
                .withDestinationLike("Copenhagen")
                .withConfigProfile("")
                .withTariffGroup("Reservation")
                .withDistributor("")
                .createSplitAdmission());

        BasicConfigurator.configure();

    }


    @Test
    public void shouldSplit(){
        SplitAdmissionSpec spec = new SplitAdmissionSpec("Brussels", "Copenhagen", "", "Reservation");

        SplitAdmissionQuery query = new SplitAdmissionQuery(spec);
        SplitAdmissionProvider provider = new SplitAdmissionProvider(splitAdmissionList);
        SplitAdmissionCommand command = new SplitAdmissionCommand(query, provider);

        Optional<SplitAdmission> reply = command.execute();

        assertThat(reply).isNotNull().isEqualTo(Optional.of(splitAdmissionList.get(1)));

    }

}