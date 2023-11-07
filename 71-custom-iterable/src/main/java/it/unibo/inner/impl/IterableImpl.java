package it.unibo.inner.impl;

import java.util.Iterator;
import java.util.function.Predicate;

import it.unibo.inner.api.IterableWithPolicy;

public class IterableImpl<T> implements IterableWithPolicy {
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
    public void setIterationPolicy(Predicate filter) {
        this.pred = filter;
    }

    class InnerIterator implements Iterator<T> {
        @Override
        public boolean hasNext() {
            if(IterableImpl.this.currentIndex < IterableImpl.this.myList.length){
                int temp = IterableImpl.this.currentIndex;
                while(temp < IterableImpl.this.myList.length){
                    temp++;
                    if(IterableImpl.this.pred.test(IterableImpl.this.myList[temp])){
                        return true;
                    }
                }
                return false;
            }else{
                return false;
            }
            
        }

        @Override
        public T next() {
            int temp = IterableImpl.this.currentIndex;
            while(temp < IterableImpl.this.myList.length){
                temp++;
                if(IterableImpl.this.pred.test(IterableImpl.this.myList[temp])){
                    return IterableImpl.this.myList[temp];
                }
            };
            return null;
        }

    }

    
}
