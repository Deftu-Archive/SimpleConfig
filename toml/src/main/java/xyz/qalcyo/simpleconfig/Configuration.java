package xyz.qalcyo.simpleconfig;

import com.moandjiezana.toml.Toml;
import xyz.qalcyo.simpleconfig.exceptions.InvalidTypeException;
import xyz.qalcyo.simpleconfig.exceptions.SubconfigurationNotFoundException;

public class Configuration implements IConfiguration<Toml> {

    public IConfiguration<Toml> copy() {
        return null;
    }

    public <T> IConfiguration<Toml> add(String key, T value) {
        return null;
    }

    public ISubconfiguration<Toml> createSubconfiguration(String name, boolean reset) {
        return null;
    }

    public ISubconfiguration<Toml> getSubconfiguration(String name) throws SubconfigurationNotFoundException, InvalidTypeException {
        return null;
    }

    public IConfiguration<Toml> save() {
        return null;
    }

    public boolean sync() {
        return false;
    }

    public IConfiguration<Toml> clear() {
        return null;
    }

    public Toml get(String key) {
        return null;
    }

    public long getAsLong(String key) {
        return 0;
    }

    public short getAsShort(String key) {
        return 0;
    }

    public int getAsInt(String key) {
        return 0;
    }

    public byte getAsByte(String key) {
        return 0;
    }

    public float getAsFloat(String key) {
        return 0;
    }

    public double getAsDouble(String key) {
        return 0;
    }

    public char getAsChar(String key) {
        return 0;
    }

    public boolean getAsBool(String key) {
        return false;
    }

    public String getAsString(String key) {
        return null;
    }

    public int size() {
        return 0;
    }

    public boolean isEmpty() {
        return false;
    }

    public boolean isApex() {
        return false;
    }

    public IConfiguration<Toml> getParent() {
        return null;
    }

    public String asString() {
        return null;
    }

    public IConfiguration<Toml> asConfiguration() {
        return null;
    }

    public ISubconfiguration<Toml> asSubconfiguration() {
        return null;
    }

}