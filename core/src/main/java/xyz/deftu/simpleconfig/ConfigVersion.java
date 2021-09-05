package xyz.deftu.simpleconfig;

public final class ConfigVersion {

    public static final ConfigVersion CURRENT = new ConfigVersion(3, 2, 0);

    public final int major;
    public final int minor;
    public final int patch;

    public final String version;

    public ConfigVersion(int major, int minor, int patch) {
        this.major = major;
        this.minor = minor;
        this.patch = patch;

        this.version = major + "." + minor + "." + patch;
    }

    public boolean isAtLeast(int major, int minor) {
        return this.major > major || (this.major == major && this.minor >= minor);
    }

    public boolean isAtLeast(int major, int minor, int patch) {
        return this.major > major || (this.major == major && (this.minor > minor || this.minor == minor && this.patch >= patch));
    }

}