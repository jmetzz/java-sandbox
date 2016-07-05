package com.github.jmetzz.time.jodaExamples;


import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Minutes;
import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import java.util.Calendar;

public class PeriodFormatterDemo {


    public static void main(String[] args) {

        PeriodFormatter yearsAndMonths = new PeriodFormatterBuilder()
                .printZeroAlways()
                .appendYears()
                .appendSuffix(" year", " years")
                .appendSeparator(" and ")
                .printZeroRarelyLast()
                .appendMonths()
                .appendSuffix(" month", " months")
                .toFormatter();

        PeriodFormatter hoursAndMinutes = new PeriodFormatterBuilder()
                .printZeroAlways()
                .rejectSignedValues(true)
                .minimumPrintedDigits(2)
                .appendHours()
                .appendSeparator(":")
                .appendMinutes()
                .toFormatter();


        DateTime d1 = new DateTime(Calendar.getInstance());
        DateTime d2 = d1.plusSeconds(59);
        DateTime d3 = d1.plusYears(2).plusHours(1).plusMinutes(20);
        System.out.println("d1: " + d1);
        System.out.println("d2: " + d2);
        System.out.println("d3: " + d3);
        System.out.println("---------------------------------------");

        Period period = new Period(d1, d2);
        System.out.println("(d1, d2): " + hoursAndMinutes.print(period));
        System.out.println("(d1, d2): " + yearsAndMonths.print(period));

        period = new Period(d1, d3);
        System.out.println("(d1, d3): " + hoursAndMinutes.print(period));

        System.out.println("---------------------------------------");
        System.out.println("---- PAST ----");
        DateTime d4 = d1.minusHours(1).minusMinutes(20);

        System.out.println("d1: " + d1);
        System.out.println("d4: " + d4);

        period = new Period(d1, d4);
        boolean negative = period.toStandardDuration().getMillis() < 0;


        period = negative ? period.negated() : period;

        System.out.println("(d1, d4): " + (negative ? "-" : "+") + hoursAndMinutes.print(period));


        System.out.println("---------------------------------------");
//        DateTime d5 = d1.plusDays(2).plusHours(1).plusMinutes(20);
//        DateTime d5 = d1.plusHours(1).minusMinutes(20);
        DateTime d5 = d1.minusMinutes(1).minusSeconds(1);

        period = new Period(d1, d5);
//        negative = period.toStandardDuration().getMillis() < 0;
//        period = negative ? period.negated() : period;

        System.out.println("d5: " + d5);
        System.out.println("(d1, d5) [hh:mm] : " + (negative ? "-" : "+") + hoursAndMinutes.print(period));
        System.out.println("Standard duration: " + period.toStandardDuration());
        System.out.println("isShorterThan 1 minute? " + period.toStandardDuration().isShorterThan(Duration.standardMinutes(-1)));
        System.out.println("isLongerThan 1 minute? " + period.toStandardDuration().isLongerThan(Duration.standardMinutes(-1)));

    }
}
