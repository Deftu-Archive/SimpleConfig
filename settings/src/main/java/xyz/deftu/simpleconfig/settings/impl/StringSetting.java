package xyz.deftu.simpleconfig.settings.impl;

import xyz.deftu.simpleconfig.settings.BaseSetting;

public class StringSetting extends BaseSetting<String> {
    public StringSetting(String name, String value) {
        super(name, value);
    }

    public StringSetting(String name) {
        this(name, "");
    }
}