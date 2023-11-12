package it.unibo.inner.impl;

import java.util.Iterator;
import java.util.function.Predicate;

import it.unibo.inner.api.IterableWithPolicy;

public class IterableImpl<T> implements IterableWithPolicy<T> {
    private T[] myList;
    private int currentIndex;
    private Predicate<T> pred;

    public IterableImpl(T[] input) {
        this(input, new Predicate<T>() {
            @Override
            public boolean test(T t) {
                return true;
            }
        });
    }

    public IterableImpl(T[] input, Predicate<T> pred){
        this.myList = input;
        this.currentIndex = 0;
        this.pred = pred;
    }

    @Override
    public Iterator<T> iterator() {
        return new InnerIterator();    
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        this.pred = filter;
    }

    public class InnerIterator implements Iterator<T> {
        @Override
        public boolean hasNext() {
            if(currentIndex < myList.length){
                while(currentIndex < myList.length){
                    if(pred.test(myList[currentIndex])){
                        return true;
                    }
                    currentIndex++;
                }
            }
            return false;
        }

        @Override
        public T next() {
            if(hasNext()) {
                return myList[currentIndex++];
            }
            throw new UnsupportedOperationException();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    
}
