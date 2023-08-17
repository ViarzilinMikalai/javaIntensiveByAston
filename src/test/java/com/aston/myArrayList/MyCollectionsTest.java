package com.aston.myArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Comparator;


public class MyCollectionsTest {

    @Test
    public void testSortWithComparator() {
        MyArrayList<Integer> list = new MyArrayList<>(Arrays.asList(22, 5, 12, 3, 7, 4, 34, 5));
        Comparator<Integer> c = new Comparator<>() {
            @Override
            public int compare(Integer o1, Integer o2) {

                return (o1.intValue() > o2.intValue()) ? 1 : (o1.intValue() < o2.intValue()) ? -1 : 0;
            }
        };
        MyCollections.sort(list, c);

        Assert.assertEquals(list.get(0), 3);
        Assert.assertEquals(list.get(3), 5);
        Assert.assertEquals(list.get(7), 34);
    }

    @Test
    public void testSortWithComparable() {
        MyArrayList<Integer> list = new MyArrayList<>(Arrays.asList(22, 5, 12, 3, 7, 4, 34, 5));

        MyCollections.sort(list);

        Assert.assertEquals(list.get(0), 3);
        Assert.assertEquals(list.get(3), 5);
        Assert.assertEquals(list.get(7), 34);
    }
}