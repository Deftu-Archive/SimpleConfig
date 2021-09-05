package xyz.deftu.simpleconfig;

import xyz.deftu.simpleconfig.interfaces.IConfiguration;
import xyz.deftu.json.entities.JsonArray;
import xyz.deftu.json.entities.JsonElement;
import xyz.deftu.json.entities.JsonObject;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public final class Subconfiguration implements IConfiguration {

    private final IConfiguration parent;
    private final JsonObject config;

    public Subconfiguration(IConfiguration parent, JsonObject config) {
        this.parent = parent;
        this.config = config;
    }

    public Subconfiguration(IConfiguration parent) {
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
     * Creates and adds a subconfiguration to this configuration.
     * @param key The key that the subconfiguration will be listed under.
     * @param object The object that the subconfiguration will use.
     * @return This configuration.
     */
    public Subconfiguration createSubconfiguration(String key, JsonObject object) {
        config.add(key, new Subconfiguration(this, object).jsonify());
        return this;
    }

    /**
     * Creates and adds a subconfiguration to this configuration.
     * @param key The key that the subconfiguration will be listed under.
     * @return This configuration.
     */
    public Subconfiguration createSubconfiguration(String key) {
        return createSubconfiguration(key, new JsonObject());
    }

    /**
     * Retrieves a subconfiguration and returns it.
     * @param key The key that the subsconfiguration is listed under.
     * @return The gotten subconfiguration.
     */
    public Subconfiguration getSubconfiguration(String key) {
        JsonElement gotten = get(key);
        if (!gotten.isJsonObject())
            return null;
        return new Subconfiguration(this, gotten.getAsJsonObject());
    }

    public Subconfiguration replaceSubconfiguration(String key, Subconfiguration replacement) {
        if (!config.hasKey(key))
            createSubconfiguration(key);
        config.add(key, replacement);
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

    public JsonElement get(String key) {
        return config.get(key);
    }
    public long getAsLong(String key) {
        return config.getAsLong(key);
    }
    public short getAsShort(String key) {
        return config.getAsShort(key);
    }
    public int getAsInt(String key) {
        return config.getAsInt(key);
    }
    public byte getAsByte(String key) {
        return config.getAsByte(key);
    }
    public float getAsFloat(String key) {
        return config.getAsFloat(key);
    }
    public double getAsDouble(String key) {
        return config.getAsDouble(key);
    }
    public char getAsChar(String key) {
        return config.getAsChar(key);
    }
    public boolean getAsBoolean(String key) {
        return config.getAsBoolean(key);
    }
    public String getAsString(String key) {
        return config.getAsString(key);
    }
    public JsonObject getAsJsonObject(String key) {
        return config.getAsObject(key);
    }
    public JsonArray getAsJsonArray(String key) {
        return config.getAsArray(key);
    }

    public Collection<JsonElement> values() {
        return config.values();
    }

    public Set<String> keySet() {
        return config.keySet();
    }

    public Set<Map.Entry<String, JsonElement>> entrySet() {
        return config.entrySet();
    }

    public boolean hasKey(String key) {
        return config.hasKey(key);
    }

    public boolean hasValue(JsonElement element) {
        return config.hasValue(element);
    }

    public int size() {
        return config.size();
    }

    public boolean isEmpty() {
        return config.isEmpty();
    }

    public JsonObject jsonify() {
        return config.copy();
    }

    public String toString() {
        return jsonify().getAsString();
    }

    public Configuration getAsConfiguration() {
        return parent.getAsConfiguration();
    }

    public Subconfiguration getAsSubconfiguration() {
        return this;
    }

    public IConfiguration getParent() {
        return parent;
    }

    public JsonObject asJson() {
        return config;
    }

}