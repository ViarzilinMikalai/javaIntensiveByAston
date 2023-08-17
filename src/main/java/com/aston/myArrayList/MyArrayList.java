package com.aston.myArrayList;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;

/**
 * Resizable unsynchronized parametrized array.
 *
 * <p>Each {@code MyArrayList} instance has a <i>capacity</i>.  The capacity is
 * the size of the array used to store the elements in the list.  It is always
 * at least as large as the list size.  As elements are added to an ArrayList,
 * its capacity grows automatically.  The details of the growth policy are not
 * specified beyond the fact that adding an element has constant amortized
 * time cost.
 *
 * <p>An application can increase the capacity of an {@code MyArrayList} instance
 * before adding a large number of elements using the {@code ensureCapacity}
 * operation.  This may reduce the amount of incremental reallocation.
 *
 * @param <E> the type of elements in this list
 */
public class MyArrayList<E> implements MyList<E>{
    /**
     * Default initial capacity
     */
    private static final int DEFAULT_CAPACITY = 8;

    /**
     * Shared empty array instance used for empty instances.
     */
    private static final Object[] EMPTY_ELEMENTS = {};

    /**
     * The array buffer into which the elements of the ArrayList are stored.
     * The capacity of the ArrayList is the length of this array buffer.
     */
    private Object[] elements;

    /**
     * Capacity of MyArrayList
     */
    private int capacity;

    /**
     * Size of MyArrayList
     */
    private int size;

    /**
     * Constructs an empty list with the specified initial capacity.
     * Params: initialCapacity – the initial capacity of the list
     * Throws: IllegalArgumentException – if the specified initial capacity is negative
     * @param initialCapacity
     */
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
        this.size = 0;
    }

    /**
     * Constructs an empty list with an initial capacity of 8.
     */
    public MyArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.capacity = DEFAULT_CAPACITY;
        this.size = 0;
    }

    /**
     * Constructs a list containing the elements of the specified collection,
     * in the order they are returned by the collection's iterator.
     * @param collection - the collection whose elements are to be placed into this list
     */
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

    /**
     * Appends the specified element to the end of this list.
     *
     * @param o element to be appended to this list
     * @return {@code true} (as specified by {@link Collection#add})
     */
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

    /**
     * Inserts the specified element at the specified position in this
     * list. Shifts the element currently at that position (if any) and
     * any subsequent elements to the right (adds one to their indices).
     *
     * @return {@code true} (as specified by {@link Collection#add})
     * @param index index at which the specified element is to be inserted
     * @param o element to be inserted
     * @throws IndexOutOfBoundsException
     */
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

    /**
     * Returns the element at the specified position in this list.
     *
     * @param  index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public E get(int index) {
        return (E) this.elements[index];
    }

    /**
     * Removes the first occurrence of the specified element from this list,
     * if it is present.  If the list does not contain the element, it is
     * unchanged.  More formally, removes the element with the lowest index
     * {@code i} such that
     * {@code Objects.equals(o, get(i))}
     * (if such an element exists).  Returns {@code true} if this list
     * contained the specified element (or equivalently, if this list
     * changed as a result of the call).
     *
     * @param o element to be removed from this list, if present
     * @return {@code true} if this list contained the specified element
     */
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

    /**
     * Private remove method that skips bounds checking and does not
     * return the value removed.
     */
    private void removeElement(int index) {
        if (index == this.size - 1) {
            this.elements[index] = null;
        } else {
            System.arraycopy(this.elements, index + 1, this.elements, index, this.size - 1);
        }
        this.size--;
    }

    /**
     * Removes all of the elements from this list.  The list will
     * be empty after this call returns.
     */
    @Override
    public void clear() {
        Arrays.fill(this.elements, null);
        this.size = 0;
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    @Override
    public int getSize() {
        return this.size;
    }

    /**
     * QuickSort for MyArrayList
     *
     * @param c - comparator
     */
    @Override
    public void sort(Comparator c) {
        quickSort(0, this.size - 1, c);
    }

    /**
     * Increase capacity of this list.
     */
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

    private void quickSort(int leftIndex, int rightIndex, Comparator<E> comparator) {
        if (leftIndex < rightIndex) {
            int divideIndex = partition(leftIndex, rightIndex, comparator);

            quickSort(leftIndex, divideIndex - 1, comparator);
            quickSort(divideIndex, rightIndex, comparator);
        }
    }

    private int partition(int from, int to, Comparator<E> comparator) {
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
