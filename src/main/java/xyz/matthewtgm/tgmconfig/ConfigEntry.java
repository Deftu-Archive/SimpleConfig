package xyz.matthewtgm.tgmconfig;

import xyz.matthewtgm.tgmconfig.annotations.options.BaseOption;

public class ConfigEntry<T> {

    private String name;
    private T value;

    public ConfigEntry(String name, T value) {
        this.name = name;
        this.value = value;
        if (value instanceof BaseOption<?>)
            this.value = (T) ((BaseOption<?>) value).get();
    }

    public String getName() {
        return name;
    }

    public ConfigEntry<T> setName(String name) {
        this.name = name;
        return this;
    }

    public T getValue() {
        return value;
    }

    public ConfigEntry<T> setValue(T value) {
        this.value = value;
        return this;
    }

    public Class<T> getType() {
        if (value != null)
            return (Class<T>) value.getClass();
        return null;
    }

}