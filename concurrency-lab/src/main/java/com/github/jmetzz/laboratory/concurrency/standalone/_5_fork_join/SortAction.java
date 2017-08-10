package com.github.jmetzz.laboratory.concurrency.standalone._5_fork_join;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

public class SortAction<T extends Comparable<T>> extends RecursiveAction{

    private static final int SMALL_ENOUGH = 32;
    private final T[] elements;
    private final int start, end;
    private final T[] result;

    public SortAction(T[] elements, int start, int end){
        this.elements = elements;
        this.start = start;
        this.end = end;
        this.result = (new ArrayList<T>(elements.length)).toArray(elements);
    }

    public SortAction(T[] elements){
        this(elements, 0, elements.length);
    }


    private void merge(SortAction<T> left, SortAction<T> right){

            int i = 0;
            int lCt = 0;
            int rCt = 0;

            while (lCt < left.size() && rCt < right.size()) {
                result[i++] = (left.result[lCt].compareTo(right.result[rCt]) < 0)
                        ? left.result[lCt++]
                        : right.result[rCt++];
            }

            while (lCt < left.size())
                result[i++] = left.result[lCt++];

            while (rCt < right.size())
                result[i++] = right.result[rCt++];
    }


    public int size(){
        return end - start;
    }

    public T[] getResult() {
        return result;
    }

    @Override
    protected void compute() {
        if(size() <= SMALL_ENOUGH){
            System.arraycopy(elements, start, result, 0, size());
            Arrays.sort(result, 0, size());
        } else {
            int mid = (size() /2) + start;
            SortAction<T> left = new SortAction<T>(elements, start, mid);
            SortAction<T> right = new SortAction<T>(elements, start, mid);
            invokeAll(left, right);
            merge(left, right);
        }
    }
}
