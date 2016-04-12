package com.lx.util;

/**
 * 和String有关的各种方法, 部分源自<<Java编程思想4>>
 */
public class StringUtils {
    private static final String LONGSTR = "0123456789abcdefghijklmnopqrstuvwxyzxyznusicansaymyabc";

    private static void print(String msg) {
        Common.print(msg);
    }

    public static void test() {
//        testStringBuildReallocate();
        testStringBuilder();
//        testString();
    }

    /**
     * 测试String对象的immutable
     */
    public static void testImmutable() {
        String x = "abc";
        String y = "abc".toUpperCase();

        Common.print(x);
        Common.print(y);
    }

    /**
     * 测试StringBuilder的重新分配
     * 注: 在反编译的代码中看不到重新分配的操作, 应该是底层实现
     */
    public static void testStringBuildReallocate() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            sb.append(LONGSTR);
        }

        Common.print(sb);
    }

    /**
     * 测试String的方法
     */
    public static void testString() {
        print("char at 10:" + LONGSTR.charAt(10));

        char[] dst = new char[15];
        LONGSTR.getChars(0, 15, dst, 0);
        for (int i = 0; i < 15; i++) {
            System.out.printf("%c ", dst[i]);
        }
        print("\n");

        printHex(LONGSTR);

        print("compare to mx:" + LONGSTR.compareTo("mx"));
        print("region matcher:" + LONGSTR.regionMatches(LONGSTR.indexOf('j'), "jklmnopqz", 0, "jklmnopq".length()));

        print("ends with xyz :" + LONGSTR.endsWith("xzy"));
        print("intern :" + LONGSTR.intern());

    }

    /**
     * 打印传入的字符串的每个byte的16进制表示
     *
     * @param src
     */
    public static void printHex(String src) {
        byte[] bytes = src.getBytes();
        int splitNum = 16;

        System.out.println("Hex of String \"" + src + "\" is: ");
        for (int i = 0; i < bytes.length; i++) {
            if (i % splitNum == 0)
                System.out.println();
            System.out.printf("Ox%2X ", bytes[i]);
        }
        System.out.println();
    }

    /**
     * 测试基本的StringBuilder方法
     */
    public static void testStringBuilder() {
        StringBuilder sb = new StringBuilder(LONGSTR.length() * 2);

        sb.append(LONGSTR);
        Common.print("add:\n" + sb.toString());

        sb.delete(0, 5);
        Common.print("delete first 5:\n" + sb.toString());

        sb.replace(0, 10, "A");
        Common.print("replace first 10 to A:\n" + sb.toString());

        sb.insert(11, 2.0);
        Common.print("insert 2.0 to offset 11:\n" + sb.toString());

        sb.insert(0, ' ');
        sb.insert(sb.length(), "  \t  ");
        print("zz" + sb.toString().trim() + "zz");
    }
}
