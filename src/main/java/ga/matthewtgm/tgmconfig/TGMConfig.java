package ga.matthewtgm.tgmconfig;

import ga.matthewtgm.json.files.JsonReader;
import ga.matthewtgm.json.files.JsonWriter;
import ga.matthewtgm.json.objects.JsonArray;
import ga.matthewtgm.json.objects.JsonObject;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.Collection;

public class TGMConfig {

    @Getter @Setter private String name;
    @Getter @Setter private File directory;

    @Getter @Setter private File fullPath;

    @Getter @Setter private JsonObject configObj;

    public TGMConfig(String name, File directory) {
        this.name = name;
        this.directory = directory;

        this.fullPath = new File(new File(name + ".json"), String.valueOf(directory));

        this.configObj = new JsonObject();

        try {
            if (!this.fullPath.exists() || !this.fullPath.isFile()) {
                if (!this.fullPath.exists()) this.fullPath.mkdirs();
                this.fullPath.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public TGMConfig add(ConfigEntry entry) {
        configObj.add(entry.getName(), entry.getValue());
        return this;
    }

    public Collection<Object> values() {
        return configObj.values();
    }

    public <T> T get(String key, Class<?> type) {
        if (!configObj.containsKey(key)) {
            Object newVal = new Object();
            if (type.isAssignableFrom(Boolean.class)) newVal = Boolean.FALSE;
            if (type.isAssignableFrom(Double.class)) newVal = 0D;
            if (type.isAssignableFrom(Float.class)) newVal = 0F;
            if (type.isAssignableFrom(Long.class)) newVal = 0L;
            if (type.isAssignableFrom(Integer.class)) newVal = 0;
            if (type.isAssignableFrom(Short.class)) newVal = 0;
            if (type.isAssignableFrom(Character.class)) newVal = 'A';
            if (type.isAssignableFrom(Byte.class)) newVal = 0;
            if (type.isAssignableFrom(JsonObject.class)) newVal = new JsonObject();
            if (type.isAssignableFrom(JsonArray.class)) newVal = new JsonArray();
            configObj.put(key, newVal);
        }
        return (T) configObj.get(key);
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

}