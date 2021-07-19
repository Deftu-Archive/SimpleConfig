package xyz.matthewtgm.tgmconfig.annotations.options.impl;

import xyz.matthewtgm.tgmconfig.annotations.options.BaseOption;

public class StringOption implements BaseOption<String> {

    private String value;

    public StringOption(String value) {
        this.value = value;
    }

    public StringOption() {
        this("");
    }

    public String get() {
        return value;
    }

    public void set(String value) {
        this.value = value;
    }

}