package xyz.matthewtgm.tgmconfig;

import xyz.matthewtgm.json.files.JsonReader;
import xyz.matthewtgm.json.files.JsonWriter;
import xyz.matthewtgm.json.objects.JsonArray;
import xyz.matthewtgm.json.objects.JsonObject;

import java.io.File;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class TGMConfig {

    private String name;
    private File directory;

    private JsonObject configObj;

    public TGMConfig(String name, File directory) {
        this.name = name;
        this.directory = directory;

        if (!(new File(directory, name + ".json")).exists()) {
            configObj = new JsonObject();
            save();
        } else
            configObj = JsonReader.readObj(name, directory);
    }

    public void sync() {
        try {
            JsonObject updated = JsonReader.readObj(name, directory);
            if (!configObj.equals(updated))
                configObj = updated;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        JsonWriter.writeObj(name, configObj, directory);
    }

    public TGMConfig add(ConfigEntry<?> entry) {
        configObj.put(entry.getName(), entry.getValue());
        return this;
    }

    public TGMConfig addIfAbsent(ConfigEntry<?> entry) {
        configObj.putIfAbsent(entry.getName(), entry.getValue());
        return this;
    }

    public TGMConfig replace(ConfigEntry<?> entry) {
        configObj.replace(entry.getName(), entry.getValue());
        return this;
    }

    public TGMConfig replaceAll(BiFunction<? super String, ? super Object, ?> function) {
        configObj.replaceAll(function);
        return this;
    }

    public TGMConfig remove(ConfigEntry<?> entry) {
        if (entry.getValue() instanceof String && ((String) entry.getValue()).isEmpty()) configObj.remove(entry.getName());
        else configObj.remove(entry.getName(), entry.getValue());
        return this;
    }

    public TGMConfig remove(String key) {
        return remove(new ConfigEntry<>(key, ""));
    }

    public TGMConfig compute(String key, BiFunction<? super String, ? super Object, ?> function) {
        configObj.compute(key, function);
        return this;
    }

    public TGMConfig computeIfAbsent(String key, Function<? super String, ?> function) {
        configObj.computeIfAbsent(key, function);
        return this;
    }

    public TGMConfig computeIfPresent(String key, BiFunction<? super String, ? super Object, ?> function) {
        configObj.computeIfPresent(key, function);
        return this;
    }

    public TGMConfig forEachEntry(BiConsumer<? super String, ? super Object> action) {
        configObj.forEach(action);
        return this;
    }

    public boolean containsKey(String key) {
        return configObj.containsKey(key);
    }

    public boolean containsValue(Object value) {
        return configObj.containsValue(value);
    }

    public boolean contains(Object key) {
        return containsKey(key.toString()) || containsValue(key);
    }

    public TGMConfig clear() {
        configObj.clear();
        return this;
    }

    public Collection<Object> values() {
        return configObj.values();
    }

    public Set<String> keySet() {
        return configObj.keySet();
    }

    public Set<Map.Entry<String, Object>> entrySet() {
        return configObj.entrySet();
    }

    public Object get(String key) {
        return configObj.get(key);
    }
    public short getAsShort(String key) {
        return (short) configObj.get(key);
    }
    public int getAsInt(String key) {
        if (!configObj.containsKey(key))
            return 0;
        return (int) configObj.get(key);
    }
    public byte getAsByte(String key) {
        if (!configObj.containsKey(key))
            return 0;
        return (byte) configObj.get(key);
    }
    public float getAsFloat(String key) {
        if (!configObj.containsKey(key))
            return 0.0f;
        return (float) configObj.get(key);
    }
    public double getAsDouble(String key) {
        if (!configObj.containsKey(key))
            return 0.0d;
        return (double) configObj.get(key);
    }
    public char getAsChar(String key) {
        if (!configObj.containsKey(key))
            return 'a';
        return (char) configObj.get(key);
    }
    public boolean getAsBoolean(String key) {
        if (!configObj.containsKey(key))
            return false;
        return (boolean) configObj.get(key);
    }
    public String getAsString(String key) {
        if (!configObj.containsKey(key))
            return "";
        return (String) configObj.get(key);
    }
    public JsonObject getAsJsonObject(String key) {
        if (!configObj.containsKey(key))
            return new JsonObject();
        return (JsonObject) configObj.get(key);
    }
    public JsonArray getAsJsonArray(String key) {
        if (!configObj.containsKey(key))
            return new JsonArray();
        return (JsonArray) configObj.get(key);
    }
    public <T> T getAs(String key) {
        if (!configObj.containsKey(key))
            return null;
        return (T) configObj.get(key);
    }

    public String toJson() {
        return configObj.toJson();
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

    public JsonObject getConfigObj() {
        return configObj;
    }

}