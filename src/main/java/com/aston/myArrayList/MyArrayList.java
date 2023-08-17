package com.aston.myArrayList;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;

public class MyArrayList<E> implements MyList{

    private static final int DEFAULT_CAPACITY = 8;

    private static final Object[] EMPTY_ELEMENTS = {};

    private transient Object[] elements;

    private int capacity;

    private int size;

    public MyArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elements = new Object[initialCapacity];
            this.capacity = initialCapacity;
        } else if (initialCapacity == 0) {
            this.elements = EMPTY_ELEMENTS;
            this.capacity = initialCapacity;
        } else {
            throw new IllegalArgumentException("Illegal capacity " + initialCapacity);
        }
    }

    public MyArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.capacity = DEFAULT_CAPACITY;
        this.size = 0;
    }

    public MyArrayList(Collection<? extends E> collection) {
        Object[] objects = collection.toArray();
        this.size = objects.length;
        if (this.size > 0) {
            this.elements = Arrays.copyOf(objects, size, Object[].class);
            this.capacity = this.size;
        } else {
            this.elements = EMPTY_ELEMENTS;
            this.capacity = 0;
        }
    }

    @Override
    public boolean add(Object o) {
        int i = this.size;
        if (this.capacity == i) {
            increaseMyList();
        }
        this.elements[i] = o;
        this.size++;
        return true;
    }

    @Override
    public boolean add(int index, Object o) {
        if (this.capacity == this.size) {
            increaseMyList();
        }
        System.arraycopy(this.elements, index, this.elements, index + 1, this.size);
        this.elements[index] = o;
        this.size++;
        return true;
    }

    @Override
    public E get(int index) {
        return (E) this.elements[index];
    }

    @Override
    public boolean remove(Object o) {
        int i = 0;
        boolean hasElement = false;
        if (o == null) {
            for (; i < size; i++) {
                if (Objects.isNull(this.elements[i])) {
                    hasElement = true;
                    break;
                }
            }
        } else {
            for (; i < size; i++) {
                if (this.elements[i].equals(o)) {
                    hasElement = true;
                    break;
                }
            }
        }
        if (hasElement) {
            removeElement(i);
            return true;
        } else {
            return false;
        }
    }

    private void removeElement(int index) {
        if (index == this.size - 1) {
            this.elements[index] = null;
        } else {
            System.arraycopy(this.elements, index + 1, this.elements, index, this.size - 1);
        }
        this.size--;
    }

    @Override
    public void clear() {
        Arrays.fill(this.elements, null);
        this.size = 0;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public void sort(Comparator c) {
        quickSort(0, this.size - 1, c);
    }

    private void increaseMyList() {
        if (this.elements.length > 0) {
            int newCapacity = (int) ((capacity * 1.5) + 0.5);
            this.elements = Arrays.copyOf(this.elements, newCapacity);
            this.capacity = newCapacity;
        } else {
            this.elements = new Object[DEFAULT_CAPACITY];
            this.capacity = DEFAULT_CAPACITY;
        }
    }

    private <E> void quickSort(int leftIndex, int rightIndex, Comparator<E> comparator) {
        if (leftIndex < rightIndex) {
            int divideIndex = partition(leftIndex, rightIndex, comparator);

            quickSort(leftIndex, divideIndex - 1, comparator);
            quickSort(divideIndex, rightIndex, comparator);
        }
    }

    private <E> int partition(int from, int to, Comparator<E> comparator) {
        int leftIndex = from;
        int rightIndex = to;

        E pivot = (E) this.elements[from + (to - from) / 2];
        while (leftIndex <= rightIndex) {
            if (Objects.nonNull(comparator)) {
                while (comparator.compare((E) this.elements[leftIndex], pivot) < 0) {
                    leftIndex++;
                }

                while (comparator.compare((E) this.elements[rightIndex], pivot) > 0) {
                    rightIndex--;
                }
            } else {
                while (((Comparable<E>) this.elements[leftIndex]).compareTo(pivot) < 0) {
                    leftIndex++;
                }

                while (((Comparable<E>) this.elements[rightIndex]).compareTo(pivot) > 0) {
                    rightIndex--;
                }
            }

            if (leftIndex <= rightIndex) {
                E tmp  = (E) this.elements[leftIndex];
                this.elements[leftIndex] = this.elements[rightIndex];
                this.elements[rightIndex] = tmp;
                leftIndex++;
                rightIndex--;
            }
        }
        return leftIndex;
    }
}
