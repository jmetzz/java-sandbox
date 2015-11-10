package com.github.jmetzz.time.periodicity;


import com.github.jmetzz.time.intervals.IntervalSet;
import org.joda.time.DateTime;
import org.joda.time.Interval;

/**
 * Interface for generic periodicity.
 */

public interface IPeriodicity {
    /**
     * Test if interval contains a boundary for the given frequency.
     * <p>
     * Note that the behavior is consistent with the [start, stop) convention for intervals.
     *
     * @param interval
     * @return True iff interval contains a frequency boundary
     */
    public boolean overlapsBoundary(Interval interval);

    /**
     * Intersects an interval into an {@link IntervalSet}.
     *
     * @param interval
     * @return Resulting chopped {@link IntervalSet}
     */
    public IntervalSet intersection(Interval interval);

    /**
     * Retrieve the supremum of an interval into an {@link IntervalSet}.
     *
     * @param interval
     * @return Resulting chopped {@link IntervalSet}
     */
    public IntervalSet supremum(Interval interval);

    /**
     * Retrieves the next occurrence of this periodicity
     * that happens after the given timestamp
     *
     * @param timestamp
     * @return
     */
    public DateTime next(DateTime timestamp);

    /**
     * Retrieves the previous occurrence of this periodicity
     * that happens after the given timestamp
     *
     * @param timestamp
     * @return
     */
    public DateTime previous(DateTime timestamp);

    /**
     * States whether or not the given timestamp
     * coincides with a event of this periodicity
     *
     * @param timestamp
     * @return
     */
    public boolean matchs(DateTime timestamp);

    String getDescription();

}