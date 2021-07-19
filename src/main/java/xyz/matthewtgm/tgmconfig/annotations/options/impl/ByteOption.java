package xyz.matthewtgm.tgmconfig.annotations.options.impl;

import xyz.matthewtgm.tgmconfig.annotations.options.BaseOption;

public class ByteOption implements BaseOption<Byte> {

    private Byte value;

    public ByteOption(Byte value) {
        this.value = value;
    }

    public ByteOption() {
        this((byte) 0);
    }

    public Byte get() {
        return value;
    }

    public void set(Byte value) {
        this.value = value;
    }

}