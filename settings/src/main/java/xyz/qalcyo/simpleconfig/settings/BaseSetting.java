package xyz.qalcyo.simpleconfig.settings;

import xyz.qalcyo.json.entities.JsonObject;

public abstract class BaseSetting<T> {

    private final String name;
    protected T value;


    public BaseSetting(String name, T value) {
        this.name = name;
        this.value = value;
    }

    public BaseSetting(String name) {
        this(name, null);
    }

    public final T get() {
        return value;
    }

    public final void set(T value) {
        this.value = value;
    }

    public void set(JsonObject data) {}

    public final String jsonKey() {
        return name.toLowerCase().replace(' ', '_');
    }

}