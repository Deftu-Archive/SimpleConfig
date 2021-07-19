package xyz.matthewtgm.tgmconfig.annotations.options.impl;

import xyz.matthewtgm.tgmconfig.annotations.options.BaseOption;

public class LongOption implements BaseOption<Long> {

    private Long value;

    public LongOption(Long value) {
        this.value = value;
    }

    public LongOption() {
        this(0L);
    }

    public Long get() {
        return value;
    }

    public void set(Long value) {
        this.value = value;
    }

}