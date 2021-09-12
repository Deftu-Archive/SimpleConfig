package xyz.qalcyo.simpleconfig.settings.impl;

public class FloatSetting extends NumberSetting<Float> {

    public FloatSetting(String name, Float value) {
        super(name, value);
    }

    public FloatSetting(String name) {
        this(name, 0f);
    }

    public void increment(Float amount) {
        set(get() + amount);
    }

    public void increment() {
        increment(1f);
    }

    public void decrement(Float amount) {
        set(get() + amount);
    }

    public void decrement() {
        decrement(1f);
    }

}