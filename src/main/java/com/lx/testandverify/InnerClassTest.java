package com.lx.testandverify;


/**
 * Created by douhua on 5/27/16.
 */
//测试static inner class 和 inner class 的区别
public class InnerClassTest {
    public int a;
    public static int sA;
    private static int sB;

    private static void sSetB() {
    }

    InnerClassTest() {
    }

    public void setA(int a) {
        this.a = a;
    }

    public static void sSetA() {

    }

    static class InnerA {
        void test() {
//            a = 1; //error
            sA = 2;
            sB = 3;
//            setA(); //error
            sSetA();
            sSetB();
        }

    }

    class InnerB{
        public void test(){
            a = 1;
            sA = 2;
            sB = 3;

            sSetB();
            setA(1);
            sSetA();
            InnerClassTest.sSetA();
        }
    }
}
