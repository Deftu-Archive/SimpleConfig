package xyz.qalcyo.simpleconfig;

import xyz.qalcyo.simpleconfig.exceptions.InvalidTypeException;
import xyz.qalcyo.simpleconfig.exceptions.SubconfigurationNotFoundException;

public interface IConfiguration<G> {

    /* Duplication. */

    /**
     * Will create an exact replica of this configuration object.
     *
     * @return An exact copy of this configuration.
     */
    IConfiguration<G> copy();

    /* Modification. */

    <T> IConfiguration<G> add(String key, T value);
    /**
     * Creates a new subconfiguration.
     *
     * @param name The name of this subconfiguration.
     * @return The subconfiguration created.
     */
    ISubconfiguration<G> createSubconfiguration(String name, boolean reset);
    /**
     * Creates a new subconfiguration and replaces the current one if it exists.
     *
     * @param name The name of this subconfiguration.
     * @return The subconfiguration created.
     */
    default ISubconfiguration<G> createSubconfiguration(String name) {
        return createSubconfiguration(name, true);
    }
    /**
     * Gets and returns the subconfiguration provided.
     *
     * @param name The name of the subconfiguration you want.
     * @return The subconfiguration requested.
     */
    ISubconfiguration<G> getSubconfiguration(String name) throws SubconfigurationNotFoundException, InvalidTypeException;
    /**
     * Saves this configuration to a file.
     *
     * @return This configuration.
     */
    IConfiguration<G> save();
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
    IConfiguration<G> clear();

    /* Interaction. */

    G get(String key);
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

    boolean isApex();
    IConfiguration<G> getParent();

    /* Types. */

    String asString();
    IConfiguration<G> asConfiguration();
    ISubconfiguration<G> asSubconfiguration();

}