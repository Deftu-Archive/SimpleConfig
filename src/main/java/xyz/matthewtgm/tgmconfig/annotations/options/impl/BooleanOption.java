package xyz.matthewtgm.tgmconfig.annotations.options.impl;

import xyz.matthewtgm.tgmconfig.annotations.options.BaseOption;

public class BooleanOption implements BaseOption<Boolean> {

    private Boolean value;

    public BooleanOption(Boolean value) {
        this.value = value;
    }

    public BooleanOption() {
        this(false);
    }

    public Boolean get() {
        return value;
    }

    public void set(Boolean value) {
        this.value = value;
    }

}