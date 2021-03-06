package xyz.qalcyo.simpleconfig;

import xyz.qalcyo.json.entities.JsonElement;
import xyz.qalcyo.json.entities.JsonObject;
import xyz.qalcyo.json.files.JsonReader;
import xyz.qalcyo.json.files.JsonWriter;
import xyz.qalcyo.simpleconfig.exceptions.InvalidTypeException;
import xyz.qalcyo.simpleconfig.exceptions.SubconfigurationNotFoundException;

import java.io.File;

public class Configuration implements IConfiguration<JsonElement> {

    private final File file;
    private final JsonObject object;

    public Configuration(File file, JsonObject object) {
        this.file = file;
        this.object = object;
    }

    public Configuration(File file) {
        this(file, new JsonObject());
    }

    public Configuration(String name, File directory, JsonObject object) {
        this(new File(directory, name), object);
    }

    public Configuration(String name, File directory) {
        this(name, directory, new JsonObject());
    }

    public Configuration(String name, JsonObject object) {
        this(new File(name), object);
    }

    public Configuration(String name) {
        this(name, new JsonObject());
    }

    public Configuration copy() {
        return new Configuration(file, object);
    }

    public <T> Configuration add(String key, T value) {
        object.add(key, value);
        return this;
    }

    public Subconfiguration createSubconfiguration(String name, boolean reset) {
        if (object.hasKey(name) && !reset)
            return getSubconfiguration(name);
        Subconfiguration subconfiguration = new Subconfiguration(this);
        add(name, subconfiguration.asObject());
        return subconfiguration;
    }

    public Subconfiguration getSubconfiguration(String name) throws SubconfigurationNotFoundException, InvalidTypeException {
        if (!object.hasKey(name))
            throw new SubconfigurationNotFoundException();
        JsonElement found = object.get(name);
        if (!found.isJsonObject())
            throw new InvalidTypeException("Element found wasn't an object, therefore it can't be a subconfiguration.");
        return new Subconfiguration(this, found.getAsJsonObject());
    }

    public IConfiguration<JsonElement> save() {
        JsonWriter.write(file.getName(), object, file.getParentFile(), true);
        return this;
    }

    public boolean sync() {
        JsonObject saved = JsonReader.read(file.getName(), file.getParentFile());
        if (!saved.equals(object)) {
            object.clear();
            object.addAll(saved);
            return true;
        }
        return false;
    }

    public IConfiguration<JsonElement> clear() {
        object.clear();
        return this;
    }

    public JsonElement get(String key) {
        return object.get(key);
    }

    public long getAsLong(String key) {
        return object.getAsShort(key);
    }

    public short getAsShort(String key) {
        return object.getAsShort(key);
    }

    public int getAsInt(String key) {
        return object.getAsInt(key);
    }

    public byte getAsByte(String key) {
        return object.getAsByte(key);
    }

    public float getAsFloat(String key) {
        return object.getAsFloat(key);
    }

    public double getAsDouble(String key) {
        return object.getAsDouble(key);
    }

    public char getAsChar(String key) {
        return object.getAsChar(key);
    }

    public boolean getAsBool(String key) {
        return object.getAsBoolean(key);
    }

    public String getAsString(String key) {
        return object.getAsString(key);
    }

    public int size() {
        return object.size();
    }

    public boolean isEmpty() {
        return object.isEmpty();
    }

    public boolean isApex() {
        return true;
    }

    public Configuration getParent() {
        return this;
    }

    public String asString() {
        return object.getAsString();
    }

    public Configuration asConfiguration() {
        return this;
    }

    public Subconfiguration asSubconfiguration() {
        throw new InvalidTypeException("A configuration cannot be converted to a subconfiguration.");
    }

    public JsonObject asObject() {
        return object;
    }

    public String toString() {
        return asString();
    }

    public static Configuration empty(File file) {
        return new Configuration(file);
    }

    public static Configuration empty(String name) {
        return new Configuration(new File("./", name));
    }

    public static Configuration of(File file, JsonObject object) {
        return new Configuration(file, object);
    }

}