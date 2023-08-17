package com.aston.myArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Comparator;

public class MyArrayListTest {
    @Test
    public void testAdd() {
        MyArrayList<Integer> list = new MyArrayList<>(1);
        list.add(22);
        list.add(null);
        list.add(null);
        list.add(null);
        Assert.assertEquals(list.get(0), 22);
        Assert.assertEquals(list.get(3), null);
    }

    @Test
    public void testAddByIndex() {
        MyArrayList<Integer> list = new MyArrayList<>(Arrays.asList(null, null));
        list.add(0, 12);
        Assert.assertEquals(list.get(0), 12);
        Assert.assertEquals(list.getSize(), 3);
    }

    @Test
    public void testGet() {
        MyArrayList<Integer> list = new MyArrayList<>(Arrays.asList(null, 12));
        list.add(0, Integer.valueOf(12));
        Assert.assertEquals(list.getSize(), 3);
    }

    @Test
    public void testRemove() {
        MyArrayList<Integer> list = new MyArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        list.remove(1);
        Assert.assertEquals(list.get(0), 2);
        Assert.assertEquals(list.getSize(), 4);
    }

    @Test
    public void testClear() {
        MyArrayList<Integer> list = new MyArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        list.clear();
        Assert.assertEquals(list.getSize(), 0);
    }

    @Test
    public void testGetSize() {
        MyArrayList<Integer> list = new MyArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        Assert.assertEquals(list.getSize(), 5);
    }

    @Test
    public void testSortWithComparator() {
        MyArrayList<Integer> list = new MyArrayList<>(Arrays.asList(22, 5, 12, 3, 7, 4, 34, 5));
        Comparator<Integer> c = new Comparator<>() {
            @Override
            public int compare(Integer o1, Integer o2) {

                return (o1.intValue() > o2.intValue()) ? 1 : (o1.intValue() < o2.intValue()) ? -1 : 0;
            }
        };
        list.sort(c);

        Assert.assertEquals(list.get(0), 3);
        Assert.assertEquals(list.get(3), 5);
        Assert.assertEquals(list.get(7), 34);
    }

    @Test
    public void testSortWithComparaable() {
        MyArrayList<Integer> list = new MyArrayList<>(Arrays.asList(22, 5, 12, 3, 7, 4, 34, 5));

        list.sort(null);

        Assert.assertEquals(list.get(0), 3);
        Assert.assertEquals(list.get(3), 5);
        Assert.assertEquals(list.get(7), 34);
    }
}