package xyz.matthewtgm.tgmconfig.settings.impl;

import xyz.matthewtgm.tgmconfig.settings.BaseSetting;

public class EnumSetting<T extends Enum<?>> extends BaseSetting<T> {

    private final Class<T> options;
    private int index;

    protected EnumSetting(String name, T val) {
        super(name, val);
        this.options = (Class<T>) val.getClass();
    }

    public T set(int index) {
        set(options.getEnumConstants()[index]);
        this.index = index;
        return val;
    }

    public T next(boolean update) {
        int i = index + 1;
        if (i > options.getEnumConstants().length - 1)
            i = 0;
        if (update)
            set(i);
        return val;
    }

    public T next() {
        return next(false);
    }

    public T previous(boolean update) {
        int i = index - 1;
        if (i < 0)
            i = options.getEnumConstants().length - 1;
        if (update)
            set(i);
        return val;
    }

    public T previous() {
        return previous(false);
    }

    public Class<T> type() {
        return options;
    }

}