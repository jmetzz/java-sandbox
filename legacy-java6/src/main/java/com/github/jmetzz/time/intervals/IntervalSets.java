package com.github.jmetzz.time.intervals;

import org.joda.time.DateTime;
import org.joda.time.Interval;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * Static class with convenience methods to manage IntervalSets.
 */
public class IntervalSets {
    /**
     * An interval comparator ordering by start, end.
     *
     * @author Andrea Leofreddi
     */
    public static final Comparator<Interval> INTERVAL_COMPARATOR = new Comparator<Interval>() {
        @Override
        public int compare(Interval o1, Interval o2) {
            int result = o1.getStart().compareTo(o2.getStart());

            if (result != 0)
                return result;

            return o1.getEnd().compareTo(o2.getEnd());
        }
    };

    private IntervalSets() {
        throw new AssertionError();
    }

    /**
     * Factory method for a variadic list of intervals.
     *
     * @param intervals Input intervals
     * @return Corresponding interval set
     */
    public static IntervalSet of(Interval... intervals) {
        return new IntervalSet(Arrays.asList(intervals));
    }

    /**
     * Merge various interval sets into one.
     *
     * @param intervalSets List of interval sets to merge
     * @return Merged interval set
     */
    public static IntervalSet merge(Iterable<IntervalSet> intervalSets) {
        IntervalSet resultSet = new IntervalSet();

        TreeMap<DateTime, Integer> boundaries = new TreeMap<DateTime, Integer>();

        // Populate boundaries map
        for (IntervalSet intervalSet : intervalSets) {
            for (Interval interval : intervalSet) {
                for (DateTime t : Arrays.asList(interval.getStart(), interval.getEnd())) {
                    Entry<DateTime, Integer> previous = boundaries.floorEntry(t);

                    boundaries.put(t, previous != null ? previous.getValue() : 0);
                }

                for (DateTime t : boundaries.subMap(interval.getStart(), interval.getEnd()).keySet())
                    boundaries.put(t, boundaries.get(t) + 1);
            }
        }

        if (boundaries.isEmpty())
            return resultSet;

        // Iterate (orderly) boundaries producing intervals when needed
        Iterator<DateTime> itor = boundaries.keySet().iterator();

        DateTime start = itor.next(), stop;

        boolean add = boundaries.get(start) > 0;

        while (itor.hasNext()) {
            stop = itor.next();

            if (add)
                resultSet.add(new Interval(start, stop));

            start = stop;

            add = boundaries.get(start) > 0;
        }

        return resultSet;
    }

    /**
     * Intersect various interval sets into one.
     *
     * @param intervalSets List of interval sets to merge
     * @return Merged interval set
     */
    public static IntervalSet intersect(Iterable<IntervalSet> intervalSets) {
        /* Sanity test: guarantees that empty sets have no intersection */
        int notEmptySets = 0;
        for (IntervalSet intervalSet : intervalSets) {
            if (!intervalSet.isEmpty())
                notEmptySets++;
        }
        if (notEmptySets <= 1)
            return new IntervalSet();


        /* In case there are at leas two non empty sets we can continue */
        IntervalSet union = merge(intervalSets);

        Interval outputSpan = span(intervalSets);

        return union.intersect(outputSpan);

    }

    /**
     * Calculate the output span as the intersection of all the spans
     *
     * @param intervalSets
     * @return
     */
    public static Interval span(Iterable<IntervalSet> intervalSets) {
        Interval outputSpan = null;

        for (IntervalSet intervalSet : intervalSets) {
            if (outputSpan == null)
                outputSpan = intervalSet.span();
            else
                outputSpan = outputSpan.overlap(intervalSet.span());
        }
        return outputSpan;
    }


    /**
     * Variadic version of merge.
     *
     * @param intervalSets List of {@link IntervalSets} to merge
     * @return Merged interval set
     */
    public static IntervalSet merge(IntervalSet... intervalSets) {
        return merge(Arrays.asList(intervalSets));
    }


}
