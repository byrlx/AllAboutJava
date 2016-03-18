package com.lx.algorithm;

import com.lx.Util.Common;

import java.util.Arrays;
import java.util.LinkedHashSet;

/**
 * Created by douhua on 3/3/16.
 */
public class CollectionLearn {
    public void hashsetAdd(){
        String para1 = "www.baidu.com||www.sina.com||www.google.com";
        String para2 = "www.sina.com";

        LinkedHashSet<String> test = new LinkedHashSet<>();
        test.add(para2);

        String[] splits = para1.split("\\|\\|"); //Caution !!!
        test.addAll(Arrays.asList(splits));

        for(String para : test) {
            Common.print(para);
        }
    }

    public static void test(){
        CollectionLearn learn = new CollectionLearn();

        learn.hashsetAdd();
    }
}
