package com.lx;

import com.lx.concurrent.CocurrentQ;
import com.lx.rtti.DoD;
import com.lx.rtti.ProxyTest;
import com.lx.util.IoUtils;
import com.lx.util.StringUtils;

public class Main {
    private void old() {
//        (new Sort()).start();
//        (new Learn()).start();
//        GitParser.test();
//        MysqlTester.start();
//        System.out.println(Thread.currentThread());
//        RbTree.test();
//        FancyLog.test();
//        StringUtils.test();
//        IoUtils.test();
//        DoD dod = ProxyTest.create(DoD.class);
//        dod.print("hi");
    }

    public static void main(String[] args) {
        CocurrentQ.test();
    }
}