package com.lx;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by douhua on 2/25/16.
 */
public class Log {
    public static void time(String msg) {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss.SSS");
        String cur = sdf.format(new Date(System.currentTimeMillis()));
        System.out.println(cur + " - " + msg);
    }

    public static void e(String msg) {
        System.out.println(msg);
    }

    public static <T> void e(T[] arr) {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (T e : arr) {
            sb.append(e.toString() + ",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(")");

        System.out.println(sb.toString());
    }
}
