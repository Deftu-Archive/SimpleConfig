package xyz.qalcyo.simpleconfig.settings;

import xyz.qalcyo.simpleconfig.interfaces.IConfiguration;

public class ConfigurationSettings {

    public void parse(IConfiguration configuration, BaseSetting<?>... settings) {
        for (BaseSetting<?> setting : settings) {
            configuration.add(setting.jsonKey(), setting.get());
        }
    }

}