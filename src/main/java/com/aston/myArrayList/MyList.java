package com.aston.myArrayList;

import java.util.Comparator;

public interface MyList<E> {
    boolean add(E e);

    boolean add(int index, E e);

    E get(int index);

    boolean remove(E e);

    void clear();

    int getSize();

    void sort(Comparator<E> c);
}
