package com.github.jmetzz.time.intervals;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Interval;

import java.util.Collection;
import java.util.TreeSet;

/**
 * Commodity class to represent a set of intervals. Since the IntervalSet is used
 * extensively in the Expressions API, this class keeps out the generic boilerplate.
 */
public class IntervalSet extends TreeSet<Interval> {
    /**
     * Infinity date time
     */
    public static final long DATETIME_MAX_MILLIS = 253370764800000L;
    public static final DateTime DATETIME_MAX = new DateTime(DATETIME_MAX_MILLIS, DateTimeZone.UTC);
    private static final long serialVersionUID = 7589153540394814779L;

    public IntervalSet() {
        super(IntervalSets.INTERVAL_COMPARATOR);
    }

    public IntervalSet(Collection<Interval> intervals) {
        super(IntervalSets.INTERVAL_COMPARATOR);

        addAll(intervals);
    }

    /**
     * Test if this {@link IntervalSet} is continuous, that is it contains only chained, non overlapping intervals.
     * <p>
     * An empty {@link IntervalSet} is not considered continuous.
     *
     * @return True iff the {@link IntervalSet} contains only chained, non overlapping intervals
     */
    public boolean isContinuous() {
        if (isEmpty())
            return false;

        DateTime lastEnd = null;

        for (Interval interval : this) {
            if (lastEnd != null && !lastEnd.equals(interval.getStart()))
                return false;

            lastEnd = interval.getEnd();
        }

        return true;
    }

    /**
     * Returns the intersection of the current interval set with a given interval, that is
     * an {@link IntervalSet} whose intervals are contained in the given interval parameter.
     *
     * @param interval Interval to intersect with
     * @return An sub {@link IntervalSet} containing only the intersecting interval.
     */
    public IntervalSet intersect(Interval interval) {
        IntervalSet result = new IntervalSet();

        for (Interval i : this)
            if (interval.overlaps(i))
                result.add(interval.overlap(i));

        return result;
    }

    /**
     * Returns an {@link Interval} spanning through all the interval set (that is from first().getPhase() to last().getEnd()).
     *
     * @return An {@link Interval} spanning through all the interval set
     * @throws IllegalStateException If the set is empty
     */
    public Interval span() {
        if (isEmpty())
            throw new IllegalStateException("Span invoked on empty IntervalSet");

        return new Interval(
                first().getStart(),
                last().getEnd()
        );
    }


}
