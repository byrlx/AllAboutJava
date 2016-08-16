package com.lx.enums;

import com.lx.Log;

import java.util.EnumSet;

/**
 * Created by lx on 7/22/16.
 */
public interface Food {
    enum Vegetable implements Food {
        CACTUS, BROCCOLI, CARROT
    }

    enum Fruit implements Food {
        APPLE, PEAR, BANANA
    }

    enum Meat implements Food {
        BEAF, POCK, CHICKEN
    }

    public static void main(String[] args) {
        Food food = Vegetable.BROCCOLI;
        food = Fruit.APPLE;
        food = Meat.BEAF;

        EnumSet<Fruit> fruits = EnumSet.noneOf(Fruit.class);
        fruits.add(Fruit.APPLE);
        fruits.add(Fruit.APPLE); //not add
        fruits.remove(Fruit.PEAR);

        fruits = EnumSet.of(Fruit.APPLE, Fruit.BANANA);

        fruits = EnumSet.complementOf(fruits);

        for(Fruit f: fruits) {
            Log.e(f+"");
        }
    }
}
