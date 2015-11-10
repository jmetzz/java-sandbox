package com.github.jmetzz.time;

import com.github.jmetzz.time.intervals.IntervalSet;
import com.github.jmetzz.time.intervals.IntervalSets;
import com.github.jmetzz.time.periodicity.IPeriodicity;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Interval;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static com.github.jmetzz.time.periodicity.PeriodicityImpl.KnownPeriodicities.*;
import static org.junit.Assert.*;

/**
 * Created by Jean.METZ on 7/13/2015.
 */
public class PeriodicityImplTest {

    private static List<IPeriodicity> periodicityList;

    private static Map<String, DateTime> baseDates;
    private static Map<IPeriodicity, DateTime> matchingDates;

    @BeforeClass
    public static void onlyOnce() {

        periodicityList = new ArrayList<IPeriodicity>();

        periodicityList.add(SECOND_BASED.get());
        periodicityList.add(MINUTE_BASED.get());
        periodicityList.add(HOURLY.get());
        periodicityList.add(DAILY.get());
        periodicityList.add(WEEKLY.get());
        periodicityList.add(FORTNIGHT.get());
        periodicityList.add(MONTHLY.get());
        periodicityList.add(YEARLY.get());
        periodicityList.add(HALF_MINUTE_BASED.get());
        periodicityList.add(HALF_HOUR_BASED.get());
        periodicityList.add(QUARTERLY.get());
        periodicityList.add(SEMESTERLY.get());


        // -------------------------------------------------------
        //                  DATES
        // -------------------------------------------------------
        baseDates = new TreeMap<String, DateTime>();
        DateTime start = new DateTime().withMillis(0).withZone(DateTimeZone.UTC);
        baseDates.put("Start", start);
        baseDates.put("Week", start.withWeekOfWeekyear(1));
        baseDates.put("Month", start.withMonthOfYear(1));

        baseDates.put("Jan2000Millis", new DateTime().withZone(DateTimeZone.UTC)
                .withDate(2000, 01, 28).withTime(5, 32, 32, 325));
        baseDates.put("Jan2000", new DateTime().withZone(DateTimeZone.UTC)
                .withDate(2000, 01, 28).withTime(0, 0, 0, 0));
        baseDates.put("Fev2000", new DateTime().withZone(DateTimeZone.UTC)
                .withDate(2000, 02, 28).withTime(0, 0, 0, 0));
        baseDates.put("Apr2000", new DateTime().withZone(DateTimeZone.UTC)
                .withDate(2000, 04, 28).withTime(0, 0, 0, 0));


        // -------------------------------------------------------
        matchingDates = new TreeMap<IPeriodicity, DateTime>();
        List<IPeriodicity> list = new ArrayList<IPeriodicity>(periodicityList);
        list.remove(WEEKLY.get());
        list.remove(FORTNIGHT.get());

        for (IPeriodicity p : list) {
            matchingDates.put(p, baseDates.get("Start"));
        }

        matchingDates.put(WEEKLY.get(), new DateTime().withZone(DateTimeZone.UTC)
                .withDate(2000, 01, 10).withTime(0, 0, 0, 0));
        matchingDates.put(FORTNIGHT.get(), new DateTime().withZone(DateTimeZone.UTC)
                .withDate(2000, 01, 03).withTime(0, 0, 0, 0));
        // -------------------------------------------------------
    }

    @Test
    public void testOccurance4NonMatchingEvents() {

        System.out.println("Non matching event ");

        // Testing occurances for a given date that lies between to
        // periodicity events, but does not exactly match the event
        DateTime d = baseDates.get("Jan2000Millis"); //.withDate(2000, 01, 28).withTime(5, 32, 32, 325))
        for (IPeriodicity p : periodicityList) {
//            System.out.println("------ Periodicity: " + p.getDescription());
            DateTime previous = p.previous(d);
            DateTime next = p.next(d);

            assertEquals(p.next(previous), next);
            assertEquals(p.previous(next), previous);
            assertTrue(previous.isBefore(next));

            assertTrue(d.isAfter(previous) && d.isBefore(next));

//            System.out.println("\t\t Given: \t\t\t" + d);
//            System.out.println("\t\t Previous: \t\t\t" + previous);
//            System.out.println("\t\t\t Next of previous: \t\t" + p.getNextOccurrance(previous));
//            System.out.println("\t\t Next: \t\t\t\t" + next);
//            System.out.println("\t\t\t Previous of next: \t\t" + p.getPreviousOccurrance(next));

        }
    }

    @Test
    public void testOccurance4MatchingEvents() {
        // Testing occurances for a given date that
        // exactly matches the periodicity event
        System.out.println("Matching event ");

        for (IPeriodicity p : periodicityList) {
//            System.out.println("------ Periodicity: " + p.getDescription());
            DateTime d = matchingDates.get(p);
            DateTime previous = p.previous(d);
            DateTime next = p.next(d);

            assertTrue(previous.isBefore(d));
            assertTrue(d.isBefore(next));
            assertTrue(!d.isEqual(previous) && !d.isEqual(next));

            DateTime mid = p.next(previous);
            assertEquals(p.next(previous), mid);
            assertEquals(mid, p.previous(next));

//            System.out.println("\t\t Given: \t\t\t" + d);
//            System.out.println("\t\t Previous: \t\t\t" + previous);
//            System.out.println("\t\t\t Next of previous: \t\t" + p.getNextOccurrance(previous));
//            System.out.println("\t\t Next: \t\t\t\t" + next);
//            System.out.println("\t\t\t Previous of next: \t\t" + p.getPreviousOccurrance(next));
//            System.out.println("\t\t Mid: \t\t\t" + mid);
//            System.out.println("\t\t\t Next of mid: \t\t" + p.getNextOccurrance(mid));
//            System.out.println("\t\t\t Previous of mid: \t\t" + p.getPreviousOccurrance(mid));
        }
    }

    @Test
    @Ignore
    public void testCategoryOverlaps() {

        for (IPeriodicity p : periodicityList) {

            DateTime start = matchingDates.get(p);
            System.out.println(p.getDescription());

            // FIXME - get the correct duration according to the property field and step size
            //long d = p.getDuration(start);
            long d = 0L;

            DateTime stop = start.plus(d);

            /* should pass these tests since the periodicity begins at start time */
            assertTrue(p.overlapsBoundary(new Interval(start, stop)));
            assertTrue(p.overlapsBoundary(new Interval(start, start.plus(1))));
            assertTrue(p.overlapsBoundary(new Interval(start, stop.minus(1))));
            assertTrue(p.overlapsBoundary(new Interval(start, stop.plus(1))));
            assertTrue(p.overlapsBoundary(new Interval(start, start)));

            /* should pass these tests since the periodicity begins at start time
             * and the end timestamp of the given period greater than start
             */
            assertTrue(p.overlapsBoundary(new Interval(start.minus(1), stop.plus(1))));
            assertTrue(p.overlapsBoundary(new Interval(start.minus(1), stop)));

            /* should fail because the periodicity starts before the given
             * start timestamp and happens again only after the given stop timestamp
             */
            assertFalse(p.overlapsBoundary(new Interval(start.plus(1), stop)));
            assertFalse(p.overlapsBoundary(new Interval(start.plus(1), stop.minus(1))));
        }
    }

    @Test
    public void testIntersection() {
        IntervalSet r = IntervalSets.of(
                IntervalTestHelper.getInterval("20140101 000000", "20140102 000000"),
                IntervalTestHelper.getInterval("20140102 000000", "20140103 000000"),
                IntervalTestHelper.getInterval("20140103 000000", "20140104 000000"),
                IntervalTestHelper.getInterval("20140104 000000", "20140105 000000"),
                IntervalTestHelper.getInterval("20140105 000000", "20140106 000000"),
                IntervalTestHelper.getInterval("20140106 000000", "20140107 000000"),
                IntervalTestHelper.getInterval("20140107 000000", "20140108 000000"),
                IntervalTestHelper.getInterval("20140108 000000", "20140109 000000"),
                IntervalTestHelper.getInterval("20140109 000000", "20140110 000000"),
                IntervalTestHelper.getInterval("20140110 000000", "20140111 000000"),
                IntervalTestHelper.getInterval("20140111 000000", "20140112 000000"),
                IntervalTestHelper.getInterval("20140112 000000", "20140113 000000")

        );

        IPeriodicity p = DAILY.get();
        assertEquals(
                r,
                p.intersection(IntervalTestHelper.getInterval("20140101 000000", "20140113 000000"))
        );

        r = IntervalSets.of(
                IntervalTestHelper.getInterval("20000101 000000", "20000103 000000"),
                IntervalTestHelper.getInterval("20000103 000000", "20000110 000000"),
                IntervalTestHelper.getInterval("20000110 000000", "20000117 000000"),
                IntervalTestHelper.getInterval("20000117 000000", "20000124 000000"),
                IntervalTestHelper.getInterval("20000124 000000", "20000131 000000")
        );

        p = WEEKLY.get();
        assertEquals(
                r,
                p.intersection(IntervalTestHelper.getInterval("20000101 000000", "20000131 000000"))
        );

        r.add(IntervalTestHelper.getInterval("20000131 000000", "20000205 000000"));
        p = WEEKLY.get();
        assertEquals(
                r,
                p.intersection(IntervalTestHelper.getInterval("20000101 000000", "20000205 000000"))
        );


    }

    @Test
    public void testChopMisalignedStart() {

        IntervalSet r = IntervalSets.of(
                IntervalTestHelper.getInterval("20140101 100000", "20140102 000000"),
                IntervalTestHelper.getInterval("20140102 000000", "20140103 000000"),
                IntervalTestHelper.getInterval("20140103 000000", "20140104 000000"),
                IntervalTestHelper.getInterval("20140104 000000", "20140105 000000")
        );
        IPeriodicity p = DAILY.get();
        assertEquals(
                r,
                p.intersection(IntervalTestHelper.getInterval("20140101 100000", "20140105 000000"))
        );

    }

    @Test
    public void testChopMisalignedStop() {


        IntervalSet r = IntervalSets.of(
                IntervalTestHelper.getInterval("20140101 000000", "20140102 000000"),
                IntervalTestHelper.getInterval("20140102 000000", "20140103 000000"),
                IntervalTestHelper.getInterval("20140103 000000", "20140104 000000"),
                IntervalTestHelper.getInterval("20140104 000000", "20140105 000000"),
                IntervalTestHelper.getInterval("20140105 000000", "20140105 100000")
        );
        IPeriodicity p = DAILY.get();

        assertEquals(
                r,
                p.intersection(IntervalTestHelper.getInterval("20140101 000000", "20140105 100000"))
        );

    }

    @Test
    public void testChopMisalignedStartStop() {

        IntervalSet r = IntervalSets.of(
                IntervalTestHelper.getInterval("20140101 100000", "20140102 000000"),
                IntervalTestHelper.getInterval("20140102 000000", "20140103 000000"),
                IntervalTestHelper.getInterval("20140103 000000", "20140104 000000"),
                IntervalTestHelper.getInterval("20140104 000000", "20140105 000000"),
                IntervalTestHelper.getInterval("20140105 000000", "20140105 100000")
        );
        IPeriodicity p = DAILY.get();
        assertEquals(
                r,
                p.intersection(IntervalTestHelper.getInterval("20140101 100000", "20140105 100000"))
        );

    }

    @Test
    public void testSupremun() {


        List<DateTime> d = new ArrayList<DateTime>();
        d.add(new DateTime().withZone(DateTimeZone.UTC).withDate(1999, 12, 27).withTime(0, 0, 0, 0));
        d.add(new DateTime().withZone(DateTimeZone.UTC).withDate(2000, 01, 03).withTime(0, 0, 0, 0));
        d.add(new DateTime().withZone(DateTimeZone.UTC).withDate(2000, 01, 10).withTime(0, 0, 0, 0));
        d.add(new DateTime().withZone(DateTimeZone.UTC).withDate(2000, 01, 17).withTime(0, 0, 0, 0));
        d.add(new DateTime().withZone(DateTimeZone.UTC).withDate(2000, 01, 24).withTime(0, 0, 0, 0));
        d.add(new DateTime().withZone(DateTimeZone.UTC).withDate(2000, 01, 31).withTime(0, 0, 0, 0));

        IntervalSet r = IntervalSets.of(
                new Interval(d.get(0), d.get(1)),
                new Interval(d.get(1), d.get(2)),
                new Interval(d.get(2), d.get(3)),
                new Interval(d.get(3), d.get(4)),
                new Interval(d.get(4), d.get(5))
        );

        Interval intervalOfInterest = new Interval(
                new DateTime().withZone(DateTimeZone.UTC).withDate(2000, 01, 01).withTime(0, 0, 0, 0),
                d.get(5));

        IPeriodicity p = WEEKLY.get();
        assertEquals(
                r,
                p.supremum(intervalOfInterest)
        );
    }
}