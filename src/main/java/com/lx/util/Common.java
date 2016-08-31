package com.lx.util;

import com.google.common.base.Preconditions;
import com.lx.Log;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

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

    /**
     * 获取一组随机整数
     */
    public static int[] getRandomIntArr(int max) {
        return getRandomIntArr(max, 10);
    }

    public static int[] getRandomIntArr(int max, int num) {
        Random random = new Random();

        int[] result = new int[num];

        for (int i = 0; i < num; i++) {
            result[i] = Math.abs(random.nextInt()) % max;
        }

        return result;
    }

    public static int getRandomInt(int max) {
        return (new Random(max)).nextInt();
    }

    public static <T> void print(T obj) {
        System.out.println("" + obj);
    }

    public static <T> void printThread(T obj) {
        print(obj + " (" + Thread.currentThread() + ")");
    }
}
