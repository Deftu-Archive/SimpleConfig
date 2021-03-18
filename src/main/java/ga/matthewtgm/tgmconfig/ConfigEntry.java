package ga.matthewtgm.tgmconfig;

import lombok.Getter;
import lombok.Setter;

public class ConfigEntry<T> {

    public ConfigEntry(String name, T value) {
        this.name = name;
        this.value = value;
    }

    @Getter @Setter private String name;
    @Getter @Setter private T value;

}