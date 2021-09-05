package xyz.deftu.simpleconfig.interfaces;

import xyz.deftu.json.entities.JsonObject;
import xyz.deftu.simpleconfig.Configuration;
import xyz.deftu.simpleconfig.Subconfiguration;

public interface IConfiguration {
    Configuration getAsConfiguration();
    default boolean isConfiguration() {
        return this instanceof Configuration;
    }
    Subconfiguration getAsSubconfiguration();
    default boolean isSubconfiguration() {
        return this instanceof Subconfiguration;
    }

    <T> IConfiguration add(String name, T value);
    IConfiguration createSubconfiguration(String key, JsonObject object);
    default IConfiguration createSubconfiguration(String key) {
        return createSubconfiguration(key, new JsonObject());
    }
    Subconfiguration getSubconfiguration(String key);

    JsonObject jsonify();
}