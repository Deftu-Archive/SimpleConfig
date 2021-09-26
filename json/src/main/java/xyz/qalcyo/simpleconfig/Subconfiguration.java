package xyz.qalcyo.simpleconfig;

import xyz.qalcyo.json.entities.JsonElement;
import xyz.qalcyo.json.entities.JsonObject;
import xyz.qalcyo.simpleconfig.exceptions.InvalidTypeException;
import xyz.qalcyo.simpleconfig.exceptions.SubconfigurationNotFoundException;

public class Subconfiguration implements ISubconfiguration<JsonElement> {

    private final IConfiguration<JsonElement> parent;
    private final JsonObject object;

    public Subconfiguration(IConfiguration<JsonElement> parent, JsonObject object) {
        this.parent = parent;
        this.object = object;
    }

    public Subconfiguration(IConfiguration<JsonElement> parent) {
        this(parent, new JsonObject());
    }

    public Subconfiguration copy() {
        return new Subconfiguration(parent, object);
    }

    public <T> Subconfiguration add(String key, T value) {
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

    public Subconfiguration save() {
        parent.save();
        return this;
    }

    public boolean sync() {
        return parent.sync();
    }

    public Subconfiguration clear() {
        object.clear();
        return this;
    }

    public JsonElement get(String key) {
        return object.get(key);
    }

    public long getAsLong(String key) {
        return object.getAsLong(key);
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
        return false;
    }

    public IConfiguration<JsonElement> getParent() {
        return parent;
    }

    public String asString() {
        return object.getAsString();
    }

    public Configuration asConfiguration() {
        throw new InvalidTypeException("A subconfiguration cannot be converted to a configuration, please refer to Subconfiguration#getApex.");
    }

    public Subconfiguration asSubconfiguration() {
        return this;
    }

    public JsonObject asObject() {
        return object;
    }

    public IConfiguration<JsonElement> getApex() {
        IConfiguration<JsonElement> parent;
        while ((parent = this.parent.getParent()) != null && !parent.isApex())
            return parent.asConfiguration();
        return null;
    }

    public String toString() {
        return asString();
    }

}