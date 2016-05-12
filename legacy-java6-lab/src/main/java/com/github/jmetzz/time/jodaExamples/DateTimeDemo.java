package com.github.jmetzz.time.jodaExamples;

import org.joda.time.DateTime;

public class DateTimeDemo {

    public static void main(String[] args) {

        DateTime now = DateTime.now();

        System.out.println(now);

        System.out.println(now.plusMonths(1));

        System.out.println(now.plusMonths(1).withDayOfWeek(1));

    }

}
