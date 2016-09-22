package com.lx.nodagger;

/**
 * Created by lx on 8/31/16.
 */
public class Thermosiphon implements Pump {
    private final Heater heater;

    Thermosiphon(Heater heater) {
        this.heater = heater;
    }

    @Override
    public void pump() {
        if (heater.isHot()) {
            System.out.println("=> => pumping => =>");
        }
    }
}
