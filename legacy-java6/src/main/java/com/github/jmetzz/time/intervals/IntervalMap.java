package com.github.jmetzz.time.intervals;

import org.joda.time.Interval;

import java.util.TreeMap;

/**
 * Commodity class to represent a interval -> value map.
 * <p>
 * Since the IntervalMap is used extensively in the Expressions API,
 * this class keeps out the generic boilerplate on the key side.
 */
public class IntervalMap<V> extends TreeMap<Interval, V> {
    private static final long serialVersionUID = 7734285803391069623L;

    public IntervalMap() {
        super(IntervalSets.INTERVAL_COMPARATOR);
    }

    public IntervalMap(IntervalMap<V> intervals) {
        super(IntervalSets.INTERVAL_COMPARATOR);

        putAll(intervals);
    }

    /**
     * Retrieves the key set as an IntervalSet.
     */
    @Override
    public IntervalSet keySet() {
        return new IntervalSet(super.keySet());
    }

    /**
     * Returns the intersection of the current {@link IntervalMap} with a given interval, that is
     * a interval map whose all key intervals intersect (overlap) the given interval parameter.
     *
     * @param interval Interval to intersect with
     * @return An subset IntervalMap containing only the key intersecting interval.
     */
    public IntervalMap<V> intersect(Interval interval) {
        IntervalMap<V> result = new IntervalMap<V>();

        for (Interval i : keySet())
            if (i.overlaps(interval))
                result.put(i, get(i));

        return result;
    }
}