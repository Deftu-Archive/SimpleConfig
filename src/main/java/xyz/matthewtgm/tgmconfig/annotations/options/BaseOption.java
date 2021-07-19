package xyz.matthewtgm.tgmconfig.annotations.options;

public interface BaseOption<T> {

    T get();
    void set(T value);

}