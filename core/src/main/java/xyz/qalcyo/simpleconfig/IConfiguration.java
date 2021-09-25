package xyz.qalcyo.simpleconfig;

import xyz.qalcyo.simpleconfig.exceptions.SubconfigurationNotFoundException;

public interface IConfiguration {

    /* Duplication. */

    /**
     * Will create an exact replica of this configuration object.
     *
     * @return An exact copy of this configuration.
     */
    IConfiguration copy();
    /**
     * Will create an exact replica of this configuration object.
     *
     * @see IConfiguration#copy()
     * @return An exact copy of this configuration.
     */
    default IConfiguration clone() {
        return copy();
    }

    /* Modification. */

    /**
     * Creates a new subconfiguration and replaces the current one if it exists.
     *
     * @param name The name of this subconfiguration.
     * @return The subconfiguration created.
     */
    ISubconfiguration createSubconfiguration(String name);
    /**
     * Gets and returns the subconfiguration provided.
     *
     * @param name The name of the subconfiguration you want.
     * @return The subconfiguration requested.
     */
    ISubconfiguration getSubconfiguration(String name) throws SubconfigurationNotFoundException;
    /**
     * Saves this configuration to a file.
     *
     * @return This configuration.
     */
    IConfiguration save();
    /**
     * Syncs this configuration with the one saved.
     *
     * @return This configuration.
     */
    boolean sync();
    /**
     * Clears all entries in this configuration.
     *
     * @return This configuration.
     */
    IConfiguration clear();

    /* Interaction. */

    Object get(String key);
    long getAsLong(String key);
    short getAsShort(String key);
    int getAsInt(String key);
    default int getAsInteger(String key) {
        return getAsInt(key);
    }
    byte getAsByte(String key);
    float getAsFloat(String key);
    double getAsDouble(String key);
    char getAsChar(String key);
    default char getAsCharacter(String key) {
        return getAsChar(key);
    }
    boolean getAsBool(String key);
    default boolean getAsBoolean(String key) {
        return getAsBool(key);
    }
    String getAsString(String key);

    int size();
    boolean isEmpty();

    /* Hierarchy. */

    IConfiguration getParent();

    /* Types. */

    String asString();
    IConfiguration asConfiguration();
    ISubconfiguration asSubconfiguration();

}