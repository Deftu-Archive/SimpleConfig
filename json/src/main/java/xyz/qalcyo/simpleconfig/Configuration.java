package xyz.qalcyo.simpleconfig;

import xyz.qalcyo.simpleconfig.exceptions.SubconfigurationNotFoundException;

public class Configuration implements IConfiguration {

    public Configuration copy() {
        return null;
    }

    public ISubconfiguration createSubconfiguration(String name) {
        return null;
    }

    public ISubconfiguration getSubconfiguration(String name) throws SubconfigurationNotFoundException {
        return null;
    }

    public IConfiguration save() {
        return null;
    }

    public boolean sync() {
        return false;
    }

    public IConfiguration clear() {
        return null;
    }

    public Object get(String key) {
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

    public IConfiguration getParent() {
        return null;
    }

    public String asString() {
        return null;
    }

    public IConfiguration asConfiguration() {
        return null;
    }

    public ISubconfiguration asSubconfiguration() {
        return null;
    }
    
}