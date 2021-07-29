package xyz.matthewtgm.tgmconfig;

import lombok.Getter;
import xyz.matthewtgm.json.entities.JsonObject;
import xyz.matthewtgm.tgmconfig.interfaces.IConfigurable;

public final class Subconfiguration implements IConfigurable {

    @Getter private final IConfigurable parent;
    @Getter private final JsonObject config;

    public Subconfiguration(IConfigurable parent, JsonObject config) {
        this.parent = parent;
        this.config = config;
    }

    public Subconfiguration(IConfigurable parent) {
        this(parent, new JsonObject());
    }

    /**
     * Clears all entries in this configuration.
     * @return This configuration.
     */
    public Subconfiguration clear() {
        config.clear();
        return this;
    }

    /**
     * Adds a new entry to the configuration with the specified name and value.
     * @param name The name of the entry.
     * @param value The value being added.
     * @param <T> The type of the value.
     * @return This configuration.
     */
    public <T> Subconfiguration add(String name, T value) {
        config.add(name, value);
        return this;
    }

    /**
     * Removes an existing entry from the configuration with the specified name.
     * @param name The name of the entry to remove.
     * @return This configuration.
     */
    public Subconfiguration remove(String name) {
        config.remove(name);
        return this;
    }

    public Configuration getAsConfiguration() {
        return parent.getAsConfiguration();
    }

    public Subconfiguration getAsSubconfiguration() {
        return this;
    }

    public JsonObject jsonify() {
        return config.copy();
    }

}