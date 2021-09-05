package xyz.deftu.simpleconfig.settings;

import xyz.deftu.simpleconfig.interfaces.IConfiguration;

public class ConfigurationSettings {

    public void parse(IConfiguration configuration, BaseSetting<?>... settings) {
        for (BaseSetting<?> setting : settings) {
            configuration.add(setting.jsonKey(), setting.get());
        }
    }

}