package com.lx.reflect;

import java.lang.reflect.Field;

/**
 * Created by douhua on 4/6/16.
 */
public class FancyLog {
    /**
     * 使用该函数, 每次都要获取该类的fields, 会比较慢
     *
     * @param msg
     * @param o
     */
    static void log(String msg, Object o) {
        Field[] fields = o.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            try {
                String value = String.valueOf(field.get(o));
                msg = msg.replaceAll("\\$" + field.getName(), value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        System.out.println(msg);
    }

    /**
     * 比上一个方法更快的实现, 缓存该类的fields
     */
    private Field[] fields;

    public FancyLog(Class type) {
        fields = type.getDeclaredFields();
    }

    public void log2(String msg, Object o) {
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                String value = String.valueOf(field.get(o));
                msg = msg.replaceAll("\\$" + field.getName(), value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        System.out.println(msg);
    }

    public static void test() {
        FancyLog fancyLog = new FancyLog(Movie.class);
        for (Movie movie : Movie.BEST_MOVIES) {
//            FancyLog.log("I think $name worth $star stars", movie);
            fancyLog.log2("I think $name worth $star stars", movie);
        }
    }
}
