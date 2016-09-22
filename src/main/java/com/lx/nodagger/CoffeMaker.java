package com.lx.nodagger;

/**
 * Created by lx on 8/31/16.
 */
public class CoffeMaker {
    private Heater heater;
    private Pump pump;

    public CoffeMaker() {
    }

    public void setHeater(Heater heater) {
        this.heater = heater;
    }

    public void setPump(Pump pump) {
        this.pump = pump;
    }

    public CoffeMaker(Heater heater, Pump pump) {
        this.heater = heater;
        this.pump = pump;
    }

    public void brew(){
        heater.on();
        pump.pump();
        System.out.println(" [_]P coffee! [_]P ");
        heater.off();
    }
}
