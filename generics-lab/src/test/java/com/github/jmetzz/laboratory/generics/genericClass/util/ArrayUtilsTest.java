package com.github.jmetzz.laboratory.generics.genericClass.util;

import com.github.jmetzz.laboratory.generics.genericClass.shape.TwoDPoint;
import com.github.jmetzz.laboratory.generics.genericClass.tuple.HomogeneousPair;
import com.github.jmetzz.laboratory.generics.genericClass.tuple.SingleTypePair;
import com.github.jmetzz.laboratory.generics.genericClass.tuple.SingleTypedBoundedPair;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
 * Created by Jean Metz.
 */
public class ArrayUtilsTest {

    private static final GregorianCalendar[] birthdays = {
            new GregorianCalendar(1906, Calendar.DECEMBER, 9), // G. Hopper
            new GregorianCalendar(1815, Calendar.DECEMBER, 10), // A. Lovelace
            new GregorianCalendar(1903, Calendar.DECEMBER, 3), // J. von Neumann
            new GregorianCalendar(1910, Calendar.JUNE, 22), // K. Zuse
    };

    private static final Number[] nArray = new Number[]{3, 4.5, 5.345, 69, 6};

    private static final Integer[] iArray = new Integer[]{3, 4, 5, 69, 6};

    @BeforeClass
    public static void printData() {
        System.out.println("---------------------------------");
        System.out.println("Date input:");

        for (GregorianCalendar c : birthdays)
            System.out.println(c.getTime());

        System.out.println("Numeric input:");
        System.out.println(Arrays.asList(nArray));

        System.out.println("Integer input:");
        System.out.println(Arrays.asList(iArray));

    }

    @Test
    public void testMinmax1() throws Exception {

        HomogeneousPair<GregorianCalendar> mm = ArrayUtils.minmax1(birthdays);
        assertThat(mm.getLeft()).isEqualTo(birthdays[1]);
        assertThat(mm.getRight()).isEqualTo(birthdays[3]);
        System.out.println("minmax1 method:");
        System.out.println("min = " + mm.getLeft().getTime());
        System.out.println("max = " + mm.getRight().getTime());

//        HomogeneousPair<Number> pNumber = ArrayUtils.minmax1(nArray);  // -- Compiler Error: Number is not Comparable
        HomogeneousPair<Integer> pInt = ArrayUtils.minmax1(iArray);
        assertThat(pInt.getLeft()).isEqualTo(Collections.min(Arrays.asList(iArray)));
        assertThat(pInt.getRight()).isEqualTo(Collections.max(Arrays.asList(iArray)));
        System.out.println("{min, max} = " + pInt);

        // Next instruction generates a Compiler Error:
        // minmax1 method return a HomogeneousPair<T> in which <T extends Comparable>.
        // Both Integer and Double implement Comparable interface
        // but the is-a relationship between HomogeneousPair<Integer> and HomogeneousPair<Double>
        // doesn't hold.
        // Therefore, the minmax1 method returns a HomogeneousPair<Integer>
        // and not a HomogeneousPair<Double>
        // HomogeneousPair<Double> pDouble = ArrayUtils.minmax1(iArray);

    }

    @Test
    public void testMinmax2() throws Exception {
        TwoDPoint[] points = {
                new TwoDPoint(new SingleTypePair(1, 1)),
                new TwoDPoint(new SingleTypePair(2, 3)),
                new TwoDPoint(new SingleTypePair(4, 5)),
                new TwoDPoint(new SingleTypePair(6, 2)),
                new TwoDPoint(new SingleTypePair(6, 8)),
                new TwoDPoint(new SingleTypePair(2, 3))
        };
        HomogeneousPair<TwoDPoint> pair = ArrayUtils.minmax2(points);

        assertThat(pair.getLeft()).isEqualTo(points[0]);
        assertThat(pair.getRight()).isEqualTo(points[4]);

    }

    @Test
    public void testMinmax3() throws Exception {
        /*
        * Since Comparable interface is also generic we could take
        * advantage of this and implement minmax1 method as in minmax2.
        * However, calling this method with birthdays as arguments
        * yields a Compilation Error:
        * GregorianCalendar extends Calendar which implements Comparable<Calendar>
        * Therefore, through inheritance GregorianCalendar implements Comparable<Calendar>
        * instead of Comparable<GregorianCalendar>  :(
        * To solve this situation, we can use supertypes bounds such as in
        * ArrayAlg.minmax3(birthdays);
        * HomogeneousPair<GregorianCalendar> mm = ArrayAlg.minmax2(birthdays);   -- Compilation Error
        * */

        HomogeneousPair<GregorianCalendar> mm = ArrayUtils.minmax3(birthdays);
        assertThat(mm.getLeft()).isEqualTo(birthdays[1]);
        assertThat(mm.getRight()).isEqualTo(birthdays[3]);

        System.out.println("---------------------------------");
        System.out.println("minmax3 method:");
        System.out.println("min = " + mm.getLeft().getTime());
        System.out.println("max = " + mm.getRight().getTime());

    }


    public void testBoundedPair() {
        /*
        * SingleTypedBoundedPair accepts argument types that are
        * both Comparable and Serializable.
        * Therefore, we can not instantiate a SingleTypedBoundedPair
        * with Point, since Point is Comparable<Point>
        * but not Serializable.
        *
        * SingleTypedBoundedPair<Point> ibPair = new SingleTypedBoundedPair<Point>(new Point(), new Point()); // -- Compile-time error
        *
        * Integer, on the other hand, is both Comparable and Serializable
        * and thus can be used to instantiate a SingleTypedBoundedPair
        * */
        SingleTypedBoundedPair<Integer> ibPair = new SingleTypedBoundedPair<Integer>(3, 5);
    }

    // TODO - test upper and lower bound with generic classes and methods



}