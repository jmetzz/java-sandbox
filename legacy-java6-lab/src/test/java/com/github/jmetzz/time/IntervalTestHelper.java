package com.github.jmetzz.time;

import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by exi853 on 10/11/2015.
 */
public class IntervalTestHelper {

    private static DateTimeFormatter fmt = ISODateTimeFormat.dateTime();


    public static Interval getInterval(String begin, String end) {

        checkNotNull(begin);
        checkNotNull(end);

        return new Interval(
                fmt.parseDateTime(begin),
                fmt.parseDateTime(end)
        );
    }
}
