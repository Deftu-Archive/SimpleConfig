package ga.matthewtgm.tgmconfig;

import ga.matthewtgm.json.files.JsonReader;
import ga.matthewtgm.json.files.JsonWriter;
import ga.matthewtgm.json.objects.JsonArray;
import ga.matthewtgm.json.objects.JsonObject;

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
            configObj = new JsonObject();
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

    public short getValueAsShort(String key) {
        return (short) configObj.get(key);
    }
    public int getValueAsInt(String key) {
        return (int) configObj.get(key);
    }
    public byte getValueAsByte(String key) {
        return (byte) configObj.get(key);
    }
    public float getValueAsFloat(String key) {
        return (float) configObj.get(key);
    }
    public double getValueAsDouble(String key) {
        return (double) configObj.get(key);
    }
    public char getValueAsChar(String key) {
        return (char) configObj.get(key);
    }
    public boolean getValueAsBoolean(String key) {
        return (boolean) configObj.get(key);
    }
    public String getValueAsString(String key) {
        return (String) configObj.get(key);
    }
    public JsonObject getValueAsJsonObject(String key) {
        return (JsonObject) configObj.get(key);
    }
    public JsonArray getValueAsJsonArray(String key) {
        return (JsonArray) configObj.get(key);
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