package com.lx.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by douhua on 2/25/16.
 */
public class Log {
    public static void e(String msg) {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss.SSS");
        String cur = sdf.format(new Date(System.currentTimeMillis()));
        System.out.println(cur + " - " + msg);
    }
}
