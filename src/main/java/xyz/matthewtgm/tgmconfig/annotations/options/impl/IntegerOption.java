package xyz.matthewtgm.tgmconfig.annotations.options.impl;

import xyz.matthewtgm.tgmconfig.annotations.options.BaseOption;

public class IntegerOption implements BaseOption<Integer> {

    private Integer value;

    public IntegerOption(Integer value) {
        this.value = value;
    }

    public IntegerOption() {
        this(0);
    }

    public Integer get() {
        return value;
    }

    public void set(Integer value) {
        this.value = value;
    }

}