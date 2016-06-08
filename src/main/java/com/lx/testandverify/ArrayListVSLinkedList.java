package com.lx.testandverify;

import com.lx.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.UnaryOperator;

/**
 * Created by douhua on 6/5/16.
 */
public class ArrayListVSLinkedList {
    public long curTime;
    ArrayList<String> aList = new ArrayList<>( );
    LinkedList<String> lList = new LinkedList<>();
    Class clz = getClass();

    public void aAdd() {
        for (int i = 0; i < 1000000; i++) {
            aList.add(String.valueOf(i));
        }
    }

    public void lAdd() {
        for (int i = 0; i < 1000000; i++) {
            lList.add(String.valueOf(i));
        }
    }

    public void aRemove() {
        Log.e(aList.remove("99994")+"");
    }

    public void lRemove() {
        Log.e(lList.remove("99994")+"");
    }

    public void aInsert() {
        aList.add(99999, "10210");
    }

    public void lInsert() {
        lList.add(99999, "10210");
    }

    public void tick() {
        curTime = System.currentTimeMillis();
    }

    /**
     * test the performance of arraylist
     */
    public void testArrayList() {
        try {
            time(clz.getMethod("aAdd", null));
            time(clz.getMethod("aRemove", null));
            time(clz.getMethod("aInsert", null));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    /**
     * test the performance of linkedlist
     */
    public void testLinkedList() {
        try {
            time(clz.getMethod("lAdd", null));
            time(clz.getMethod("lRemove", null));
            time(clz.getMethod("lInsert", null));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    /**
     * caculate the time spent by executing a method
     * @param method
     */
    public void time(Method method) {
        tick();
        try {
            method.invoke(this, null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        long cur = System.currentTimeMillis();
        Log.e(method.getName() + " used " + (cur - curTime) + "ms");
    }

    public void start() {
        testArrayList();
        testLinkedList();
    }

    private void listFeature(){
//        List<String> list = new ArrayList<>();
        List<String> list = new LinkedList<>();

        String raw = "acedfiaosjdfas0i";

        for(int i = 0; i<10; i++){
            list.add(raw.substring(i));
        }

        ListIterator<String> iterator = list.listIterator(5);
        Log.e("\n");
        Iterator<String> dIterator = ((LinkedList)list).descendingIterator();
        while (dIterator.hasNext()){
            Log.e(dIterator.next());
        }
//        Log.e(iterator.previous());
//        iterator.add("go");
//        iterator.previous();
//        Log.e(iterator.next());
        Log.e("\n");
        while(iterator.hasNext()) {
//            Log.e(iterator.next());
        }

        Log.e("find result is "+list.indexOf(raw.substring(4)));


        list.add(6, "zzz");
        for (String aList1 : list) {
//            Log.e(aList1);
        }

        List<String> sublist = list.subList(2, 6);
//        sublist.clear();
        Log.e("SUBLIST:");
        for (String aList1 : sublist) {
//            Log.e(aList1);
        }
        list.set(4, "go");
        Log.e("SUBLIST CHANGE:");
        for (String aList1 : sublist) {
//            Log.e(aList1);
        }

        Log.e("SPECIFY A OPERATOR:");
        list.replaceAll(new MyUnaryOprator());
        for (String aList1 : list) {
//            Log.e(aList1);
        }

        Log.e("CLONE:\n====================");
        ArrayList<String> cln =(ArrayList<String>) ((ArrayList<String>) list).clone();
        cln.subList(2,8).clear();
        for (String aList1 : list) {
            Log.e(aList1);
        }
        Log.e("=======================");
        for (String aList1 : cln) {
            Log.e(aList1);
        }
    }
    public static void test() {
        ArrayListVSLinkedList al = new ArrayListVSLinkedList();
//        al.start();
        al.listFeature();
    }

    private class MyUnaryOprator implements UnaryOperator<String> {

        @Override
        public String apply(String s) {
            return "LX -- " + s;
        }
    }
}
