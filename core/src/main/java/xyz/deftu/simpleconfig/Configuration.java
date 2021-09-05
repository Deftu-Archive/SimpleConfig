package xyz.deftu.simpleconfig;

import xyz.deftu.simpleconfig.interfaces.IConfiguration;
import xyz.deftu.json.entities.JsonArray;
import xyz.deftu.json.entities.JsonElement;
import xyz.deftu.json.entities.JsonObject;
import xyz.deftu.json.files.JsonReader;
import xyz.deftu.json.files.JsonWriter;

import java.io.File;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unused"})
public final class Configuration implements IConfiguration {

    private final File file;
    private final JsonObject config;

    public Configuration(File file) {
        if (file == null)
            throw new NullPointerException("Configuration file cannot be null!");
        this.file = file;

        String fileName = file.getName();
        File real = new File(file.getParentFile(), fileName.endsWith(".json") ? fileName : fileName + ".json");
        if (!real.exists()) {
            this.config = new JsonObject();
            save();
        } else
            this.config = JsonReader.read(file.getName(), file.getParentFile());
    }

    public Configuration(String name, File directory) {
        this(new File(directory, name));
    }

    public Configuration(String name, String directory) {
        this(new File(directory, name));
    }

    private Configuration(File file, JsonObject config) {
        this.file = file;
        this.config = config;
    }

    /**
     * Will create an exact replica of this configuration object.
     * @return An exact copy of this configuration.
     */
    public Configuration copy() {
        return new Configuration(file, config);
    }

    /**
     * Saves this configuration to a file. Indents are optional.
     * @param indents Whether to indent the file or not.
     * @return This configuration.
     */
    public Configuration save(boolean indents) {
        JsonWriter.write(file.getName(), config, file.getParentFile(), indents);
        return this;
    }

    /**
     * Saves this configuration to a file, indents included.
     * @return This configuration.
     */
    public Configuration save() {
        return save(true);
    }

    /**
     * Syncs this configuration with the one saved.
     * @return This configuration.
     */
    public Configuration sync() {
        config.clear();
        config.addAll(JsonReader.<JsonElement>read(file.getName(), file.getParentFile()).getAsJsonObject());
        return this;
    }

    /**
     * @return Whether this file is synced or not.
     */
    public boolean synced() {
        if (file == null || file.getParentFile() == null)
            return true;
        JsonObject saved = JsonReader.<JsonElement>read(file.getName(), file.getParentFile()).getAsJsonObject();
        return config.equals(saved);
    }

    /**
     * Clears all entries in this configuration.
     * @return This configuration.
     */
    public Configuration clear() {
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
    public <T> Configuration add(String name, T value) {
        config.add(name, value);
        return this;
    }

    /**
     * Creates and adds a subconfiguration to this configuration.
     * @param key The key that the subconfiguration will be listed under.
     * @param object The object that the subconfiguration will use.
     * @return This configuration.
     */
    public Configuration createSubconfiguration(String key, JsonObject object) {
        config.add(key, new Subconfiguration(this, object).jsonify());
        return this;
    }

    /**
     * Creates and adds a subconfiguration to this configuration.
     * @param key The key that the subconfiguration will be listed under.
     * @return This configuration.
     */
    public Configuration createSubconfiguration(String key) {
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

    public Configuration replaceSubconfiguration(String key, Subconfiguration replacement) {
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
    public Configuration remove(String name) {
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
        return this;
    }

    public Subconfiguration getAsSubconfiguration() {
        throw new UnsupportedOperationException("Configurations aren't the same as subconfigurations!");
    }

    public File getFile() {
        return file;
    }

    public JsonObject asJson() {
        return config;
    }

}