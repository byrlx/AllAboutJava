package com.lx.testandverify;

import com.lx.util.Log;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * Created by douhua on 6/8/16.
 */
public class SubIterable<T> implements Iterable {
    int i = 0;
    SubInterator subInterator = new SubInterator();

    @Override
    public Iterator iterator() {
        return subInterator;
//        return null;
    }

    @Override
    public void forEach(Consumer action) {

    }

    @Override
    public Spliterator spliterator() {
        return null;
    }

    class SubInterator implements Iterator {

        @Override
        public boolean hasNext() {
            return i < 100;
        }

        @Override
        public String next() {
            return String.valueOf(i++);
        }

        @Override
        public void remove() {
            i--;
        }

        @Override
        public void forEachRemaining(Consumer action) {

        }
    }

    public static void test() {
        SubIterable subIterable = new SubIterable();

        for (Object i : subIterable) {
            Log.e(i + "");
        }
    }
}
