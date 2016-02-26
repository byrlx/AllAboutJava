package com.lx;

import com.lx.rxjava.Learn;

public class Main {
    public static void main(String[] args) {
//        (new Sort()).start();
        (new Learn()).start();
//        GitParser.test();
//        MysqlTester.start();
        System.out.println(Thread.currentThread());
    }
}