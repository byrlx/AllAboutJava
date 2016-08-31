package com.lx.algorithm.sort;

import com.lx.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by lx on 8/24/16.
 */
public class ComparatorTest implements Comparator<CData> {
    @Override
    public int compare(CData o1, CData o2) {
        if (o1.id == o2.id) {
            return 0;
        } else if (o1.id < o2.id) {
            return -1;
        } else {
            return 1;
        }
    }

    public static void main(String[] args) {
        ArrayList<CData> list = new ArrayList<>();
        for(int i=0;i<10;i++) {
            list.add(new CData(i));
        }

        Collections.sort(list, new ComparatorTest());
        Log.e("pase");
    }
}

class CData {
    int id;

    CData(int id) {
        this.id = id;
    }
}
