package com.lxjlib.algorithm.datastructure;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

/**
 * Created by douhua on 5/21/16.
 */
public class DoubleLinkedListTest {
    private DoubleLinkedList<String> dll;

    @Before
    public void init() {
        dll = new DoubleLinkedList<>(new String[]{"a", "b", "c"});
    }

    @Test
    public void testInit() {
        DoubleLinkedList<String> dll = new DoubleLinkedList<>(new String[]{"a", "b", "c"});
        DoubleLinkedList.Node head = dll.head();

        dll.traverse();
        dll.backwardTraverse();

        assertEquals("a", head.data);
        assertEquals("b", head.next.data);
        assertEquals("c", head.prev.data);
    }


    @Test
    public void testPush() {
        DoubleLinkedList.Node head = dll.head();
        DoubleLinkedList.Node oldPrev = head.prev;

        Random random = new Random(1232132);
        String randStr = random.nextInt() + "";
        dll.push(randStr);
        DoubleLinkedList.Node newPrev = head.prev;

        assertNotEquals(oldPrev, newPrev);
        assertEquals(randStr, newPrev.data);
    }

    @Test
    public void testDelete(){
        String[] testData = new String[] {
                "lxjlib", "a", "b", "lxjlib", "c", "d", "lxjlib", "e", "f", "lxjlib"
        };
        DoubleLinkedList dll = new DoubleLinkedList(testData);

        assertTrue(dll.contains("lxjlib"));
        assertEquals(dll.containsAll("lxjlib"), 4);
        dll.delete("lxjlib");
        dll.traverse();
        assertFalse(dll.contains("lxjlib"));
    }
}
