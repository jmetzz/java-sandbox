package com.github.jmetzz.laboratory.mockito.extension.matcher;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static com.github.jmetzz.laboratory.mockito.extension.matcher.ListContainsMatcher.contains;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Created by jean on 10/02/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class ListContainsMatcherTest {

    private List<String> stringList = Arrays.asList("one", "two", "three", "four");
    private List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5);

    @Mock
    TestClass test;

    @Test
    public void test() throws Exception {
        when(test.usesStrings(contains("one"))).thenReturn(true);
        when(test.usesIntegers(contains(5))).thenReturn(true);
        assertTrue(test.usesIntegers(integerList));
        assertTrue(test.usesStrings(stringList));

        when(test.usesStrings(contains("nine"))).thenReturn(true);
        when(test.usesIntegers(contains(42))).thenReturn(true);
        assertFalse(test.usesStrings(stringList));
        assertFalse(test.usesIntegers(integerList));
    }

    public interface TestClass {
        public boolean usesStrings(List<String> list);

        public boolean usesIntegers(List<Integer> list);
    }
}