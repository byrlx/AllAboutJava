package com.lx.algorithm.sort;

import java.util.Arrays;

/**
 * Created by xzc on 2/23/16.
 */
public class Sort {
    public static int[] DATA = {8, 9, 16, 2, 5, 22, 40, 100, 86, 3};
    private static int LEN = DATA.length;

    public static void printBase() {
        printBase(0, LEN - 1);
    }

    public static void printBase(int[] arr) {
        printBase(arr, 0, arr.length - 1);
    }

    public static void printBase(int start, int end) {
        printBase(DATA, start, end);
    }

    public static void printBase(int[] arr, int start, int end) {
        String spliter = "";
        for (int i = start, len = end; i <= len; i++) {
            System.out.print(spliter + arr[i]);
            spliter = ",";
        }

        System.out.println();
    }

    public void start() {
//        insertSort();
//        quickSort(0, LEN - 1);
        linearSort();
    }

    private void swip(int i, int j) {
        if (i == j) return;

        int temp = DATA[i];
        DATA[i] = DATA[j];
        DATA[j] = temp;
    }

    /**
     * 插入排序
     */
    public void insertSort() {
        for (int i = 0; i < LEN - 1; i++) {
            int index = i;
            for (int j = i + 1; j < LEN; j++) {
                if (DATA[j] < DATA[index]) {
                    index = j;
                }
            }

            if (index != i) {
                swip(i, index);
            }

            printBase();
        }
    }

    /**
     * 快速排序
     */
    public int _quickSort(int start, int end) {
        if (start >= end) return -1;

        int radomIndex = start;//(new Random()).nextInt(LEN);
        int data = DATA[radomIndex];

        int i = start;
        for (int j = start + 1; j <= end; j++) {
            if (DATA[j] < data) {
                swip(i + 1, j);
                i++;
            }
        }

        swip(start, i);
        return i;
    }

    public void quickSort(int start, int end) {
        if (start >= end) return;

        int index = _quickSort(start, end);
        System.out.println(index + "");
        quickSort(start, index - 1);
        quickSort(index + 1, end);
    }


    /**
     * 线性排序
     */
    public void linearSort() {
        int[] data = {8, 3, 4, 6, 5, 8, 7, 10, 6, 2};
        int[] output = new int[10];

        int[] counter = new int[11];
        Arrays.fill(counter, 0);

        for (int i = 0; i < data.length; i++) {
            counter[data[i]]++;
        }
        for (int i = 2; i < counter.length; i++) {
            counter[i] += counter[i - 1];
        }

        for (int i = data.length - 1; i >= 0; i--) {
            int _data = data[i];
            output[counter[_data]-1] = _data;
            counter[_data]--;
        }

        printBase(output, 0, output.length - 1);
    }
}
