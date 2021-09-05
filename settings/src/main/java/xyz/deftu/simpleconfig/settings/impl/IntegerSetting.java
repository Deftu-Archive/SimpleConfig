package xyz.deftu.simpleconfig.settings.impl;

public class IntegerSetting extends NumberSetting<Integer> {

    public IntegerSetting(String name, Integer value) {
        super(name, value);
    }

    public IntegerSetting(String name) {
        this(name, 0);
    }

    public void increment(Integer amount) {
        set(get() + amount);
    }

    public void increment() {
        increment(1);
    }

    public void decrement(Integer amount) {
        set(get() - amount);
    }

    public void decrement() {
        decrement(1);
    }

}