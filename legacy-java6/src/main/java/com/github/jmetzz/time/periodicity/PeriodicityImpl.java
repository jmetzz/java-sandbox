package com.github.jmetzz.time.periodicity;

import com.github.jmetzz.time.intervals.IntervalSet;
import org.joda.time.DateTime;
import org.joda.time.DateTimeFieldType;
import org.joda.time.Interval;

import java.util.HashMap;
import java.util.Map;

/**
 * Periodicity implementation with some standard periodicity
 * already instantiated
 */
public class PeriodicityImpl implements IPeriodicity, Comparable<PeriodicityImpl> {

    private static Map<DateTimeFieldType, Integer> stepOffset = new HashMap<DateTimeFieldType, Integer>();

    static {
        stepOffset.put(DateTimeFieldType.monthOfYear(), 1);
        stepOffset.put(DateTimeFieldType.weekOfWeekyear(), 1);
        stepOffset.put(DateTimeFieldType.dayOfMonth(), 1);
    }

    /**
     * A string that represents the periodicity
     */
    private final String description;
    ;
    /**
     * Represents the elapsed time duration in milliseconds
     * between two events in this periodicity
     */
    private final long stdDuration;
    /*
     * used for comparison purposes
     */
    private final DateTime phase;
    private final DateTimeFieldType stepUnit;
    private final int stepSize;

    public PeriodicityImpl(String description, DateTimeFieldType stepUnit, int stepSize, DateTime phase) {
        this.description = description;

        this.phase = phase;

        this.stepUnit = stepUnit;
        this.stepSize = stepSize;

        this.stdDuration = phase.property(stepUnit).toInterval().toDurationMillis();
    }

    /**
     * Helper method to extract the minimum between two comparables.
     * <p>
     * FIXME: feels like I'm reinventing the wheel, can you find an existing API method to do that?
     *
     * @param t
     * @param u
     * @param <T>
     * @return
     */
    private static final <T extends Comparable<? super T>> T min(T t, T u) {
        if (t.compareTo(u) < 0)
            return t;

        return u;
    }

    /**
     * Given a date retrieves the floor (using periodicity).
     *
     * @param t The date to floor
     * @return Floor date of t
     */
    public DateTime floor(DateTime t) {
        int offset = stepOffset.get(stepUnit) != null ? stepOffset.get(stepUnit) : 0;

        int round = (t.property(stepUnit).get() - offset) % stepSize;

        return t.property(stepUnit).roundFloorCopy().property(stepUnit).addToCopy(-round);
    }

    /**
     * See {@link IPeriodicity}.
     */
    @Override
    public boolean overlapsBoundary(Interval interval) {
        return intersection(interval).size() > 1 || floor(interval.getStart()).equals(interval.getStart());
    }

    /**
     * See {@link IPeriodicity}.
     */
    @Override
    public IntervalSet intersection(Interval interval) {
        return supremum(interval).intersect(interval);
    }

    /**
     * See {@link IPeriodicity}.
     */
    @Override
    public IntervalSet supremum(Interval interval) {
        IntervalSet result = new IntervalSet();

        DateTime t = floor(interval.getStart()), u = next(t);

        while (u.compareTo(interval.getEnd()) < 0) {
            result.add(new Interval(t, u));

            t = u;

            u = next(u);
        }

        result.add(new Interval(t, u));

        return result;
    }

    /**
     * See {@link IPeriodicity}.
     */
    @Override
    public DateTime previous(DateTime timestamp) {
        DateTime f = floor(timestamp);

        if (!f.equals(timestamp))
            return f;

        return floor(timestamp).property(stepUnit).addToCopy(-stepSize);
    }

    /**
     * See {@link IPeriodicity}.
     */
    @Override
    public DateTime next(DateTime timestamp) {
        return floor(timestamp).property(stepUnit).addToCopy(stepSize);
    }

    /**
     * See {@link IPeriodicity}.
     */
    public boolean matchs(DateTime timestamp) {
        DateTime previous = previous(timestamp);
        DateTime next = next(timestamp);

        return !next.isEqual(next(previous));
    }

    @Override
    public int compareTo(PeriodicityImpl p) {
        DateTime epoch = new DateTime(0);

        Interval t = new Interval(previous(epoch), next(epoch));

        Interval u = new Interval(p.previous(epoch), p.next(epoch));

        return t.toDuration().compareTo(u.toDuration());
    }

    public String getDescription() {
        return this.description;
    }

    public DateTime getPhase() {
        return this.phase;
    }

    @Override
    public String toString() {
        return "PeriodicityImpl{" + getDescription() + "}";
    }

    public enum KnownPeriodicities {
        SECOND_BASED(new PeriodicityImpl("Second based periodicity", new DateTime().secondOfMinute().getFieldType(), 1, new DateTime().withTime(0, 0, 0, 0))),
        HALF_MINUTE_BASED(new PeriodicityImpl("Half Minute based periodicity", new DateTime().secondOfMinute().getFieldType(), 30, new DateTime().withTime(0, 0, 0, 0))),
        MINUTE_BASED(new PeriodicityImpl("Minute based periodicity", new DateTime().minuteOfHour().getFieldType(), 1, new DateTime().withTime(0, 0, 0, 0))),
        HALF_HOUR_BASED(new PeriodicityImpl("Half Hour based periodicity", new DateTime().minuteOfHour().getFieldType(), 30, new DateTime().withTime(0, 0, 0, 0))),
        HOURLY(new PeriodicityImpl("Hourly periodicity", new DateTime().hourOfDay().getFieldType(), 1, new DateTime().withTime(0, 0, 0, 0))),
        BIHOURLY(new PeriodicityImpl("Bihourly periodicity", new DateTime().hourOfDay().getFieldType(), 2, new DateTime().withTime(0, 0, 0, 0))),
        DAILY(new PeriodicityImpl("Daily periodicity", new DateTime().dayOfMonth().getFieldType(), 1, new DateTime().withTime(0, 0, 0, 0))),
        WEEKLY(new PeriodicityImpl("Weekly periodicity", new DateTime().weekOfWeekyear().getFieldType(), 1, new DateTime().withTime(0, 0, 0, 0))),
        FORTNIGHT(new PeriodicityImpl("Fortnight periodicity", new DateTime().weekOfWeekyear().getFieldType(), 2, new DateTime().withTime(0, 0, 0, 0))),
        MONTHLY(new PeriodicityImpl("Monthly periodicity", new DateTime().monthOfYear().getFieldType(), 1, new DateTime().withTime(0, 0, 0, 0))),
        QUARTERLY(new PeriodicityImpl("Quarterly periodicity", new DateTime().monthOfYear().getFieldType(), 3, new DateTime().withTime(0, 0, 0, 0))),
        SEMESTERLY(new PeriodicityImpl("Semesterly periodicity", new DateTime().monthOfYear().getFieldType(), 6, new DateTime().withTime(0, 0, 0, 0))),
        YEARLY(new PeriodicityImpl("Yearly periodicity", new DateTime().year().getFieldType(), 1, new DateTime().withTime(0, 0, 0, 0)));

        private PeriodicityImpl implementation;

        KnownPeriodicities(PeriodicityImpl implementation) {
            this.implementation = implementation;
        }

        public IPeriodicity get() {
            return implementation;
        }
    }
}
