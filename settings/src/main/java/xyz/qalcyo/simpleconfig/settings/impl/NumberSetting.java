package xyz.qalcyo.simpleconfig.settings.impl;

import xyz.qalcyo.simpleconfig.settings.BaseSetting;

public abstract class NumberSetting<T extends Number> extends BaseSetting<T> {

    public NumberSetting(String name, T value) {
        super(name, value);
    }

    public NumberSetting(String name) {
        super(name);
    }

    public abstract void increment(T amount);
    public abstract void increment();

    public abstract void decrement(T amount);
    public abstract void decrement();

}