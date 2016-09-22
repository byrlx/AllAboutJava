package com.lx.nodagger;

/**
 * Created by lx on 8/31/16.
 */
public class CoffeeApp {
    public static void main(String[] args){
        Heater heater = new ElectricHeater();
//        Pump pump = new Thermosiphon(heater);
        Pump pump = new LxPump();

        CoffeMaker coffeMaker = new CoffeMaker();
        coffeMaker.setHeater(heater);
        coffeMaker.setPump(pump);

        coffeMaker.brew();
    }
}
