package xyz.matthewtgm.tgmconfig;

import xyz.matthewtgm.json.entities.JsonElement;
import xyz.matthewtgm.json.files.JsonReader;
import xyz.matthewtgm.json.files.JsonWriter;
import xyz.matthewtgm.json.entities.JsonArray;
import xyz.matthewtgm.json.entities.JsonObject;

import java.io.File;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

@SuppressWarnings({"unused", "unchecked"})
public class TGMConfig {

    private final String name;
    private File directory;

    private final JsonObject configObj;

    public TGMConfig(String name, File directory) {
        this.name = name;
        this.directory = directory;

        if (!(new File(directory, name + ".json")).exists() || ((JsonObject) JsonReader.read(name, directory)).size() <= 0) {
            configObj = new JsonObject();
            save();
        } else configObj = JsonReader.read(name, directory);
    }

    public TGMConfig(String name) {
        this.name = name;
        this.directory = null;
        this.configObj = new JsonObject();
    }

    public TGMConfig(String name, JsonObject configObj) {
        this.name = name;
        this.directory = null;
        this.configObj = configObj;
    }

    private TGMConfig(String name, File directory, JsonObject configObj) {
        this.name = name;
        this.directory = directory;
        this.configObj = new JsonObject();
        this.configObj.addAll(configObj);
        if (!(new File(directory, name + ".json")).exists() || ((JsonObject) JsonReader.read(name, directory)).size() <= 0) save();
    }

    /**
     * @return a deep copy of the config.
     */
    @SuppressWarnings("all")
    public TGMConfig clone() {
        return new TGMConfig(name, directory, configObj);
    }

    /**
     * Syncs the class with the saved config.
     */
    public void sync() {
        if (directory == null) return;
        try {
            JsonObject updated = JsonReader.read(name, directory);
            if (!configObj.equals(updated)) configObj.addAll(updated);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return Whether or not the class is synced with the saved config.
     */
    public boolean isSynced() {
        if (directory == null) return true;
        JsonObject saved = JsonReader.read(name, directory);
        return configObj.equals(saved);
    }

    /**
     * Saves the config, the saved config JSON is "pretty".
     */
    public void save() {
        if (directory == null) return;
        JsonWriter.write(name, configObj, directory, true);
    }

    /**
     * Saves the config with raw JSON, no indents.
     */
    public void rawSave() {
        if (directory == null) return;
        JsonWriter.write(name, configObj, directory);
    }

    /**
     * Adds an entry to the config.
     * @param entry The entry to add.
     */
    public TGMConfig add(ConfigEntry<?> entry) {
        configObj.add(entry.getName(), entry.getValue());
        return this;
    }

    /**
     * Creates a sub-category configuration.
     * @param config The config to turn into a sub-configuration.
     */
    public TGMConfig addSubConfig(TGMConfig config) {
        return add(new ConfigEntry<>(config.getName(), config.getConfigObj()));
    }

    /**
     * Returns a sub-category configuration with the specified name.
     * @param name The name of the sub-configuration to fetch.
     */
    public TGMConfig getSubConfig(String name) {
        return new TGMConfig(name, configObj.getObject(name));
    }

    /**
     * Syncs the config then adds the entry.
     * @param entry The entry to add.
     */
    public TGMConfig syncAndAdd(ConfigEntry<?> entry) {
        sync();
        add(entry);
        return this;
    }

    /**
     * Adds the entry then saves to a file.
     * @param entry The entry to add.
     */
    public TGMConfig addAndSave(ConfigEntry<?> entry) {
        add(entry);
        save();
        return this;
    }

    /**
     * Adds all entries from the map to the config JSON.
     * @param map The map of which's entries to add.
     */
    public TGMConfig addAll(Map<String, JsonElement> map) {
        configObj.addAll(map);
        return this;
    }

    /**
     * Adds all entries from the JsonObject to the config JSON.
     * @param jsonObject The object of which's entries to add.
     */
    public TGMConfig addAll(JsonObject jsonObject) {
        configObj.addAll(jsonObject);
        return this;
    }

    /**
     * @param config The config of which's entries to add.
     */
    public TGMConfig addAll(TGMConfig config) {
        configObj.addAll(config.getConfigObj());
        return this;
    }

    /**
     * Syncs the config then adds all entries in the map.
     * @param map The map of which's entries to add.
     */
    public TGMConfig syncAndAddAll(Map<String, JsonElement> map) {
        sync();
        addAll(map);
        return this;
    }

    /**
     * Syncs the config then adds all the entries in the JsonObject.
     * @param json The object of which's entries to add.
     */
    public TGMConfig syncAndAddAll(JsonObject json) {
        sync();
        addAll(json);
        return this;
    }

    /**
     * Syncs the config then adds all entries in the config provided.
     * @param config The config of which's entries to add.
     */
    public TGMConfig syncAndAddAll(TGMConfig config) {
        sync();
        addAll(config.getConfigObj());
        return this;
    }

    /**
     * Adds all the entries in the map then saves.
     * @param map The map of which's entries to add.
     */
    public TGMConfig addAllAndSave(Map<String, JsonElement> map) {
        addAll(map);
        save();
        return this;
    }

    /**
     * Adds all entries in the JsonObject then saves.
     * @param jsonObject The object of which's entries to add.
     */
    public TGMConfig addAllAndSave(JsonObject jsonObject) {
        addAll(jsonObject);
        save();
        return this;
    }

    /**
     * Adds all entries in the provided config then saves.
     * @param config The config of which's entries to add.
     */
    public TGMConfig addAllAndSave(TGMConfig config) {
        addAll(config);
        save();
        return this;
    }

    /**
     * Adds the entry if it is absent.
     * @param entry The entry to add.
     */
    public TGMConfig addIfAbsent(ConfigEntry<?> entry) {
        configObj.addIfAbsent(entry.getName(), entry.getValue());
        return this;
    }

    /**
     * Adds the entry if it is absent, then saves.
     * @param entry The entry to add.
     */
    public TGMConfig addIfAbsentAndSave(ConfigEntry<?> entry) {
        addIfAbsent(entry);
        save();
        return this;
    }

    /**
     * Removes the entry provided.
     * @param entry The entry to remove.
     */
    public TGMConfig remove(ConfigEntry<?> entry) {
        configObj.remove(entry.getName());
        return this;
    }

    /**
     * Removes the entry provided then saves.
     * @param entry The entry to remove.
     */
    public TGMConfig removeAndSave(ConfigEntry<?> entry) {
        remove(entry);
        save();
        return this;
    }

    /**
     * Removes the entry provided.
     * @param key The key of the entry you want to remove.
     */
    public TGMConfig remove(String key) {
        return remove(new ConfigEntry<>(key, ""));
    }

    /**
     * Removes the entry provided then saves.
     * @param key The key of the entry you want to remove.
     */
    public TGMConfig removeAndSave(String key) {
        return removeAndSave(new ConfigEntry<>(key, ""));
    }

    /**
     * Loops through each entry and runs the action provided.
     * @param action The action to run for each entry.
     */
    public TGMConfig forEach(BiConsumer<? super String, ? super Object> action) {
        configObj.forEach(action);
        return this;
    }

    /**
     * @return The amount of entries.
     */
    public int size() {
        return configObj.size();
    }

    /**
     * @return Whether or not the config JSON has any entries or not.
     */
    public boolean isEmpty() {
        return configObj.isEmpty();
    }

    /**
     * @param key The key to check for.
     * @return Whether or not the config JSON contains an entry with the key specified.
     */
    public boolean containsKey(String key) {
        return configObj.hasKey(key);
    }

    /**
     * @param value The value to check for.
     * @return Whether or not the config JSON contains an entry with the value specified.
     */
    public boolean containsValue(JsonElement value) {
        return configObj.hasValue(value);
    }

    /**
     * @param key The item to check for.
     * @return Whether or not the config JSON contains the key provided as a key or a value.
     */
    public boolean contains(Object key) {
        return containsKey(key.toString()) || containsValue((JsonElement) key);
    }

    /**
     * Clears the config JSON.
     */
    public TGMConfig clear() {
        configObj.clear();
        return this;
    }

    /**
     * Clears the config JSON if the key isn't present.
     * @param key The key to check for.
     */
    public TGMConfig clearIfAbsent(String key) {
        if (!containsKey(key)) clear();
        return this;
    }

    /**
     * Clears the config JSON if the key is present.
     * @param key The key to check for.
     */
    public TGMConfig clearIfPresent(String key) {
        if (containsKey(key)) clear();
        return this;
    }

    /**
     * Clears the config JSON and saves.
     */
    public TGMConfig clearAndSave() {
        clear();
        save();
        return this;
    }

    /**
     * Clears the config JSON if the key isn't present, then saves.
     * @param key The key to check for.
     */
    public TGMConfig clearIfAbsentAndSave(String key) {
        if (!containsKey(key)) clear();
        save();
        return this;
    }

    /**
     * Clears the config JSON if the key is present, then saves.
     * @param key The key to check for.
     */
    public TGMConfig clearIfPresentAndSave(String key) {
        if (containsKey(key)) clear();
        save();
        return this;
    }

    /**
     * Clears the config JSON then adds the entry.
     * @param entry The entry to add.
     */
    public TGMConfig clearAndAdd(ConfigEntry<?> entry) {
        clear();
        add(entry);
        return this;
    }

    public TGMConfig clearAndAddAll(Map<String, JsonElement> map) {
        clear();
        addAll(map);
        return this;
    }

    public TGMConfig clearAndAddAll(JsonObject json) {
        clear();
        addAll(json);
        return this;
    }

    public TGMConfig clearAndAddAll(TGMConfig config) {
        clear();
        addAll(config.getConfigObj());
        return this;
    }

    /**
     * @return All the entry values in the config JSON.
     */
    public Collection<JsonElement> values() {
        return configObj.values();
    }

    /**
     * @return All keys in the config JSON.
     */
    public Set<String> keySet() {
        return configObj.keySet();
    }

    /**
     * @return All the entries in the config JSON.
     */
    public Set<Map.Entry<String, JsonElement>> entrySet() {
        return configObj.entrySet();
    }

    public Object get(String key) {
        return configObj.get(key);
    }
    public long getAsLong(String key) {
        return configObj.get(key).getAsLong();
    }
    public short getAsShort(String key) {
        return configObj.get(key).getAsShort();
    }
    public int getAsInt(String key) {
        return configObj.get(key).getAsInt();
    }
    public byte getAsByte(String key) {
        return configObj.get(key).getAsByte();
    }
    public float getAsFloat(String key) {
        return configObj.get(key).getAsFloat();
    }
    public double getAsDouble(String key) {
        return configObj.get(key).getAsDouble();
    }
    public char getAsChar(String key) {
        return configObj.get(key).getAsChar();
    }
    public boolean getAsBoolean(String key) {
        return configObj.get(key).getAsBoolean();
    }
    public String getAsString(String key) {
        return configObj.get(key).getAsString();
    }
    public JsonObject getAsJsonObject(String key) {
        return configObj.get(key).getAsJsonObject();
    }
    public JsonArray getAsJsonArray(String key) {
        return configObj.get(key).getAsJsonArray();
    }
    public <T> T getAs(String key) {
        return (T) configObj.get(key);
    }

    public String toJson() {
        return configObj.getAsString();
    }

    public String getName() {
        return name;
    }

    public File getDirectory() {
        return directory;
    }

    public void setDirectory(File directory) {
        this.directory = directory;
    }

    public File getConfigFile() {
        return new File(directory, name + ".json");
    }

    public JsonObject getConfigObj() {
        return configObj;
    }

    public JsonObject cloneConfigObj() {
        return configObj.copy();
    }

    public String toString() {
        return toJson();
    }

}