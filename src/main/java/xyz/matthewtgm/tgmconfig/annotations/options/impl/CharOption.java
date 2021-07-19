package xyz.matthewtgm.tgmconfig.annotations.options.impl;

import xyz.matthewtgm.tgmconfig.annotations.options.BaseOption;

public class CharOption implements BaseOption<Character> {

    private Character value;

    public CharOption(Character value) {
        this.value = value;
    }

    public CharOption() {
        this('a');
    }

    public Character get() {
        return value;
    }

    public void set(Character value) {
        this.value = value;
    }

}