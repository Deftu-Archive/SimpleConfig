package xyz.deftu.simpleconfig.settings.impl;

import xyz.deftu.simpleconfig.settings.BaseSetting;

public class EnumSetting<T extends Enum<?>> extends BaseSetting<T> {

    private final Class<T> options;
    private int index;

    public EnumSetting(String name, T value) {
        super(name, value);
        this.options = (Class<T>) value.getClass();
    }

    public void set(int index) {
        set(options.getEnumConstants()[index]);
        this.index = index;
    }

    public T next() {
        int index = this.index + 1;
        if (index > options.getEnumConstants().length - 1) {
            index = 0;
        }

        return options.getEnumConstants()[index];
    }

    public T previous() {
        int index = this.index - 1;
        if (index > options.getEnumConstants().length - 1) {
            index = options.getEnumConstants().length - 1;
        }

        return options.getEnumConstants()[index];
    }

    public Class<T> type() {
        return options;
    }

}