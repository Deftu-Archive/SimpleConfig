package xyz.matthewtgm.tgmconfig.annotations.options.impl;

import xyz.matthewtgm.tgmconfig.annotations.options.BaseOption;

public class ShortOption implements BaseOption<Short> {

    private Short value;

    public ShortOption(Short value) {
        this.value = value;
    }

    public ShortOption() {
        this((short) 0);
    }

    public Short get() {
        return null;
    }

    public void set(Short value) {

    }

}