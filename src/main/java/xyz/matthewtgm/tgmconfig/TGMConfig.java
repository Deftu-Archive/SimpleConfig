package xyz.matthewtgm.tgmconfig;

import xyz.matthewtgm.json.files.JsonReader;
import xyz.matthewtgm.json.files.JsonWriter;
import xyz.matthewtgm.json.objects.JsonArray;
import xyz.matthewtgm.json.objects.JsonObject;

import java.io.File;
import java.util.Collection;

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

    public TGMConfig add(String key, Object value) {
        configObj.add(key, value);
        return this;
    }

    public TGMConfig add(ConfigEntry<?> entry) {
        configObj.add(entry.getName(), entry.getValue());
        return this;
    }

    public TGMConfig replace(String key, Object value) {
        configObj.replace(key, value);
        return this;
    }

    public TGMConfig replace(ConfigEntry<?> entry) {
        configObj.replace(entry.getName(), entry.getValue());
        return this;
    }

    public Collection<Object> values() {
        return configObj.values();
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