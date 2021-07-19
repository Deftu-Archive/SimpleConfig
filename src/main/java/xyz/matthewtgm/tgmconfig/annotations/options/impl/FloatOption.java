package xyz.matthewtgm.tgmconfig.annotations.options.impl;

import xyz.matthewtgm.tgmconfig.annotations.options.BaseOption;

public class FloatOption implements BaseOption<Float> {

    private Float value;

    public FloatOption(Float value) {
        this.value = value;
    }

    public FloatOption() {
        this(0f);
    }

    public Float get() {
        return null;
    }

    public void set(Float value) {

    }

}