package com.lx.enums;

import com.lx.Log;

/**
 * Created by lx on 7/22/16.
 */
public enum BasicE {
    BABA, MAMA, TAOZI;

    BasicE next(){
        switch (this){
            case  BABA:
                return MAMA;
            case MAMA:
                return TAOZI;
            case TAOZI:
                return BABA;
        }

        return null;
    }

    public static void main(String[] args) {
        BasicE element = BABA;
        Log.e(element.name());
        Log.e(element.getDeclaringClass()+"");
        Log.e(element.getClass()+"");
        Log.e(element.ordinal()+"");
        for(BasicE e: BasicE.values()) {
            Log.e("value " + e + " compare to MAMA:" + e.compareTo(MAMA));
        }

        for (int i=0; i<7; i++) {
            Log.e(element+"");
            element = element.next();
        }
    }
}
