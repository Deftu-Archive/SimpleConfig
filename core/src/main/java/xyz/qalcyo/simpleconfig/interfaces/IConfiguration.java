package xyz.qalcyo.simpleconfig.interfaces;

import xyz.qalcyo.simpleconfig.Configuration;
import xyz.qalcyo.simpleconfig.Subconfiguration;
import xyz.qalcyo.json.entities.JsonObject;

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

    IConfiguration getParent();
    JsonObject jsonify();
}