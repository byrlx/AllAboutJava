package com.lx.algorithm.datastructure;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

/**
 * Created by douhua on 5/20/16.
 */
public class LinkedListTest {
    LinkedList<String> linkedList;

    @Before
    public void init() {
        String[] dataSet = new String[]{"hi", "lo", "wo", "ld"};
        linkedList = new LinkedList<>(dataSet);
    }

    @Test
    public void testContains() {
        Assert.assertTrue(linkedList.contains("hi"));
        Assert.assertTrue(linkedList.contains("lo"));
        Assert.assertTrue(linkedList.contains("wo"));
        Assert.assertTrue(linkedList.contains("ld"));
        Assert.assertFalse(linkedList.contains("nome"));
    }

    @Test
    public void testAppend() {
        linkedList.append("se");
        Assert.assertEquals(linkedList.tail(), "se");
        linkedList.delete("se");
    }

    @Test
    public void testDelete() {
        String tail = linkedList.tail();
        linkedList.delete(tail);
        Assert.assertNotEquals(tail, linkedList.tail());
    }
}
