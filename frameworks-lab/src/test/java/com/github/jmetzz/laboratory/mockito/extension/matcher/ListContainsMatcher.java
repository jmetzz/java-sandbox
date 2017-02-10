package com.github.jmetzz.laboratory.mockito.extension.matcher;

import org.mockito.ArgumentMatcher;

import java.util.List;

import static org.mockito.Matchers.argThat;

/**
 * Created by jean on 10/02/2017.
 */
public class ListContainsMatcher<T> extends ArgumentMatcher<List<T>> {

    private T element;

    public ListContainsMatcher(T element) {
        this.element = element;
    }

    @Override
    public boolean matches(Object argument) {
        @SuppressWarnings("unchecked")
        List<T> list = (List<T>) argument;
        return list.contains(element);
    }

    /**
     * Static convenience method for creating the Matcher which uses argThat to convert
     * the Matcher into a List for use within the stubbing call
     * @param element
     * @param <T>
     * @return
     */
    public static <T> List<T> contains(T element) {
        return argThat(new ListContainsMatcher<>(element));
    }
}