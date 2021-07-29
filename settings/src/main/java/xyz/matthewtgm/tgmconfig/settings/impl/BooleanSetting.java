package xyz.matthewtgm.tgmconfig.settings.impl;

import xyz.matthewtgm.tgmconfig.settings.BaseSetting;

public class BooleanSetting extends BaseSetting<Boolean> {

    public BooleanSetting(String name, Boolean val) {
        super(name, val);
    }

    public boolean toggle() {
        val = !val;
        return val;
    }

}