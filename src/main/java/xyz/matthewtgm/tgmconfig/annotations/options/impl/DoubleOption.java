package xyz.matthewtgm.tgmconfig.annotations.options.impl;

import xyz.matthewtgm.tgmconfig.annotations.options.BaseOption;

public class DoubleOption implements BaseOption<Double> {

    private Double value;

    public DoubleOption(Double value) {
        this.value = value;
    }

    public DoubleOption() {
        this(0D);
    }

    public Double get() {
        return value;
    }

    public void set(Double value) {
        this.value = value;
    }

}