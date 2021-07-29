package xyz.matthewtgm.tgmconfig.settings;

import xyz.matthewtgm.json.entities.JsonElement;
import xyz.matthewtgm.json.entities.JsonPrimitive;
import xyz.matthewtgm.tgmconfig.Configuration;

public class ConfigurationSettingsAPI {

    public static void add(Configuration configuration, BaseSetting<?> setting) {
        configuration.add(setting.name, setting.get());
    }

    public static <T> BaseSetting<T> retrieve(Configuration configuration, String name, Class<? extends BaseSetting<T>> type) {
        JsonElement gotten = configuration.get(name);
        if (!gotten.isJsonPrimitive())
            return null;
        JsonPrimitive primitive = gotten.getAsJsonPrimitive();
        Object primVal = primitive.getValue();
        try {
            return type.cast(type.getDeclaredConstructors()[0].newInstance(name, primVal));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}