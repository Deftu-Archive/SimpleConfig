package xyz.deftu.simpleconfig.settings.impl;

import xyz.deftu.simpleconfig.settings.BaseSetting;

public class BooleanSetting extends BaseSetting<Boolean> {

    public BooleanSetting(String name, Boolean value) {
        super(name, value);
    }

    public BooleanSetting(String name) {
        this(name, false);
    }

    public boolean toggle() {
        set(!get());
        return get();
    }

}