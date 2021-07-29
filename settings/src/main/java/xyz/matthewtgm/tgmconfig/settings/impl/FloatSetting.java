package xyz.matthewtgm.tgmconfig.settings.impl;

import xyz.matthewtgm.tgmconfig.settings.BaseSetting;

public class FloatSetting extends BaseSetting<Float> {

    public FloatSetting(String name, Float val) {
        super(name, val);
    }

    public float increment(float amount) {
        val += amount;
        return val;
    }

    public float increment() {
        return increment(1);
    }

    public float decrement(float amount) {
        val += amount;
        return val;
    }

    public float decrement() {
        return decrement(1);
    }

}