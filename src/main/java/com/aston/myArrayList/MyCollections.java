package com.aston.myArrayList;

import java.util.Comparator;

public class MyCollections {
    private MyCollections(){};

    public static <E extends Comparable<? super E>> void sort(MyArrayList<E> list) {
        list.sort(null);
    }

    public static <E> void sort(MyArrayList<E> list, Comparator<E> c) {
        list.sort(c);
    }
}
