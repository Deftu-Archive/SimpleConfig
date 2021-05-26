package xyz.matthewtgm.tgmconfig;

import xyz.matthewtgm.json.files.JsonReader;
import xyz.matthewtgm.json.files.JsonWriter;
import xyz.matthewtgm.json.objects.JsonArray;
import xyz.matthewtgm.json.objects.JsonObject;
import xyz.matthewtgm.json.parsing.JsonParser;

import java.io.File;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class TGMConfig {

    private final String name;
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

    private TGMConfig(String name, File directory, JsonObject configObj) {
        this.name = name;
        this.directory = directory;
        this.configObj = new JsonObject();
        this.configObj.putAll(configObj);

        if (!(new File(directory, name + ".json")).exists()) save();
    }

    public TGMConfig clone() {
        return new TGMConfig(name, directory, configObj);
    }

    public void sync() {
        try {
            JsonObject updated = JsonReader.readObj(name, directory);
            if (!configObj.equals(updated))
                configObj.putAll(updated);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isSynced() {
        JsonObject saved = JsonReader.readObj(name, directory);
        return configObj.equals(saved);
    }

    public void save() {
        JsonWriter.writeObj(name, configObj, directory);
    }

    public TGMConfig add(ConfigEntry<?> entry) {
        configObj.put(entry.getName(), entry.getValue());
        return this;
    }

    public TGMConfig syncAndAdd(ConfigEntry<?> entry) {
        sync();
        add(entry);
        return this;
    }

    public TGMConfig addAndSave(ConfigEntry<?> entry) {
        configObj.put(entry.getName(), entry.getValue());
        save();
        return this;
    }

    public TGMConfig addAll(Map<? extends String, ?> map) {
        configObj.putAll(map);
        return this;
    }

    public TGMConfig addAll(JsonObject jsonObject) {
        configObj.putAll(jsonObject);
        return this;
    }

    public TGMConfig addAll(TGMConfig config) {
        configObj.putAll(config.getConfigObj());
        return this;
    }

    public TGMConfig syncAndAddAll(Map<? extends String, ?> map) {
        sync();
        addAll(map);
        return this;
    }

    public TGMConfig syncAndAddAll(JsonObject json) {
        sync();
        addAll(json);
        return this;
    }

    public TGMConfig syncAndAddAll(TGMConfig config) {
        sync();
        addAll(config.getConfigObj());
        return this;
    }

    public TGMConfig addAllAndSave(Map<? extends String, ?> map) {
        addAll(map);
        save();
        return this;
    }

    public TGMConfig addAllAndSave(JsonObject jsonObject) {
        addAll(jsonObject);
        save();
        return this;
    }

    public TGMConfig addAllAndSave(TGMConfig config) {
        addAll(config);
        save();
        return this;
    }

    public TGMConfig addIfAbsent(ConfigEntry<?> entry) {
        configObj.putIfAbsent(entry.getName(), entry.getValue());
        return this;
    }

    public TGMConfig addIfAbsentAndSave(ConfigEntry<?> entry) {
        configObj.putIfAbsent(entry.getName(), entry.getValue());
        save();
        return this;
    }

    public TGMConfig replace(ConfigEntry<?> entry) {
        configObj.replace(entry.getName(), entry.getValue());
        return this;
    }

    public TGMConfig replaceAndSave(ConfigEntry<?> entry) {
        configObj.replace(entry.getName(), entry.getValue());
        save();
        return this;
    }

    public TGMConfig replaceAll(BiFunction<? super String, ? super Object, ?> function) {
        configObj.replaceAll(function);
        return this;
    }

    public TGMConfig replaceAllAndSave(BiFunction<? super String, ? super Object, ?> function) {
        configObj.replaceAll(function);
        save();
        return this;
    }

    public TGMConfig remove(ConfigEntry<?> entry) {
        if (entry.getValue() instanceof String && ((String) entry.getValue()).isEmpty()) configObj.remove(entry.getName());
        else configObj.remove(entry.getName(), entry.getValue());
        return this;
    }

    public TGMConfig removeAndSave(ConfigEntry<?> entry) {
        if (entry.getValue() instanceof String && ((String) entry.getValue()).isEmpty()) configObj.remove(entry.getName());
        else configObj.remove(entry.getName(), entry.getValue());
        save();
        return this;
    }

    public TGMConfig remove(String key) {
        return remove(new ConfigEntry<>(key, ""));
    }

    public TGMConfig removeAndSave(String key) {
        return removeAndSave(new ConfigEntry<>(key, ""));
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

    public TGMConfig merge(String key, Object value, BiFunction<? super Object, ? super Object, ?> function) {
        configObj.merge(key, value, function);
        return this;
    }

    public TGMConfig mergeAndSave(String key, Object value, BiFunction<? super Object, ? super Object, ?> function) {
        configObj.merge(key, value, function);
        save();
        return this;
    }

    public int size() {
        return configObj.size();
    }

    public boolean isEmpty() {
        return configObj.isEmpty();
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

    public TGMConfig clearIfAbsent(String key) {
        if (!containsKey(key)) clear();
        return this;
    }

    public TGMConfig clearIfPresent(String key) {
        if (containsKey(key)) clear();
        return this;
    }

    public TGMConfig clearAndSave() {
        clear();
        save();
        return this;
    }

    public TGMConfig clearIfAbsentAndSave(String key) {
        if (!containsKey(key)) clear();
        save();
        return this;
    }

    public TGMConfig clearIfPresentAndSave(String key) {
        if (containsKey(key)) clear();
        save();
        return this;
    }

    public TGMConfig clearAndAdd(ConfigEntry<?> entry) {
        clear();
        add(entry);
        return this;
    }

    public TGMConfig clearAndAddAll(Map<? extends String, ?> map) {
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
        Object gotten = configObj.get(key);
        return JsonParser.parseObj(gotten == null ? "{}" : gotten.toString());
    }
    public JsonArray getAsJsonArray(String key) {
        if (!configObj.containsKey(key))
            return new JsonArray();
        Object gotten = configObj.get(key);
        return JsonParser.parseArr(gotten == null ? "{}" : gotten.toString());
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

    public File getConfigFile() {
        return new File(directory, name + ".json");
    }

    public JsonObject getConfigObj() {
        return configObj;
    }

}