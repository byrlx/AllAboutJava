package com.lx.annotation;

import com.lx.Log;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by lx on 8/23/16.
 */
public class AnnoTestClass {
    @LxAnnotationTest(id = 1, desp = "function 1")
    public void fun1() {

    }

    @LxAnnotationTest(id = 2, desp = "function 2")
    public void fun2() {

    }

    public void fun3() {
    }

    @LxAnnotationTest(id = 4)
    public void end() {
    }

    private static void trackAnnoUses(List<Integer> ids, Class<?> cls) {
        for (Method method : cls.getDeclaredMethods()) {
            LxAnnotationTest annotations = method.getAnnotation(LxAnnotationTest.class);
            if (annotations != null) {
                int id = annotations.id();
                if (ids.contains(id)) {
                    Log.e("id " + id + " found; it's description is " + annotations.desp());
                }
                ids.remove(new Integer(id));
            }
        }

        for (int id : ids) {
            Log.e("id " + id + " is not found");
        }
    }

    public static void main(String[] args) {
        List<Integer> ids = new ArrayList<>();
        Collections.addAll(ids, 1, 2, 3, 4, 5);
        trackAnnoUses(ids, AnnoTestClass.class);
    }
}

