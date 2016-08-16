package com.lx.enums;

import com.lx.Log;

/**
 * Created by lx on 7/22/16.
 */
public enum Course {
    VEGETABLE(Food.Vegetable.class),
    FRUIT(Food.Fruit.class),
    MEAT(Food.Meat.class);

    private Food[] values;

    Course(Class<? extends Food> ec) {
        values = ec.getEnumConstants();
    }

    public Food randFood() {
        return Enums.random(values);
    }

    public static void main(String[] args) {
        for(int i=0; i< 5; i++) {
            Log.e("Menu " + i + ": ");
            for(Course course:Course.values()) {
                Food food = course.randFood();
                Log.e("\t"+food.toString());
            }
        }
    }
}
