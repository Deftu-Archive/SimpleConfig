package xyz.qalcyo.simpleconfig.settings.impl;

import xyz.qalcyo.simpleconfig.settings.BaseSetting;

public class StringSetting extends BaseSetting<String> {
    public StringSetting(String name, String value) {
        super(name, value);
    }

    public StringSetting(String name) {
        this(name, "");
    }
}