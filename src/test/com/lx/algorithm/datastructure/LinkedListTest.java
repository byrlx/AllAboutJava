package com.lx.algorithm.datastructure;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

/**
 * Created by douhua on 5/20/16.
 */
public class LinkedListTest {
    LXLinkedList<String> LXLinkedList;

    @Before
    public void init() {
        String[] dataSet = new String[]{"hi", "lo", "wo", "ld"};
        LXLinkedList = new LXLinkedList<>(dataSet);
    }

    @Test
    public void testContains() {
        Assert.assertTrue(LXLinkedList.contains("hi"));
        Assert.assertTrue(LXLinkedList.contains("lo"));
        Assert.assertTrue(LXLinkedList.contains("wo"));
        Assert.assertTrue(LXLinkedList.contains("ld"));
        Assert.assertFalse(LXLinkedList.contains("nome"));
    }

    @Test
    public void testAppend() {
        LXLinkedList.append("se");
        Assert.assertEquals(LXLinkedList.tail(), "se");
        LXLinkedList.delete("se");
    }

    @Test
    public void testDelete() {
        String tail = LXLinkedList.tail();
        LXLinkedList.delete(tail);
        Assert.assertNotEquals(tail, LXLinkedList.tail());
    }
}
