package com.lx.Util;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicates;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.sun.istack.internal.NotNull;
import com.sun.tools.javac.util.Pair;

import java.io.*;
import java.util.HashMap;
import java.util.List;

/**
 * Created by douhua on 2/24/16.
 */
public class Common {
    public static void noTerminal() {
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static <T> void printList(List<T> para) {
        System.out.print("List: ");
        for (T obj : para) {
            System.out.print(obj + ",");
        }
        System.out.println();
    }

    /**
     * 阅读配置文件, 并返回一个hashMap
     *
     * @param fileName
     * @return
     */
    public static HashMap<String, String> readConfig(String fileName) {
        Preconditions.checkNotNull(fileName);

        HashMap<String, String> configs = new HashMap<>();

        File file = new File(fileName);
        if (!file.exists()) {
            Log.e("file " + fileName + " does not exsits");
            return null;
        }

        try {
            FileInputStream fis = new FileInputStream(fileName);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);

            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("#")) continue;

                int index = line.indexOf('=');
                if (index == -1) continue;

                String key = line.substring(0, index);
                String value = line.substring(index + 1, line.length());

                configs.put(key, value);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return configs;
    }

    public static <T> void print(T obj) {
        System.out.println("" + obj);
    }

    public static <T> void printThread(T obj) {
        print(obj + " (" + Thread.currentThread() + ")");
    }
}
