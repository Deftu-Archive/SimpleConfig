import xyz.deftu.simpleconfig.Configuration;
import xyz.deftu.simpleconfig.settings.ConfigurationSettings;
import xyz.deftu.simpleconfig.settings.impl.BooleanSetting;

import java.io.File;

public class SettingsApplication {

    private static final SettingsApplication instance = new SettingsApplication();

    public void start() {
        Configuration configuration = new Configuration(new File("./settings/settings"));
        ConfigurationSettings settings = new ConfigurationSettings();

        settings.parse(configuration, new BooleanSetting("toggle"));
        configuration.save();
    }

    public static void main(String[] args) {
        instance.start();
    }

}