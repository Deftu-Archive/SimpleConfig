package xyz.matthewtgm.tgmconfig.settings;

import lombok.Getter;

public abstract class BaseSetting<T> {

    @Getter protected final String name;
    protected T val;

    protected BaseSetting(String name, T val) {
        this.name = name;
        this.val = val;
    }

    public T get() {
        return val;
    }
    public T set(T val) {
        this.val = val;
        return val;
    }

    public final String jsonKey() {
        return name.replace(' ', '_').toLowerCase();
    }

}