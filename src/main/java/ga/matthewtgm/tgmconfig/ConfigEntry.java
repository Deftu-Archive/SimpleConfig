package ga.matthewtgm.tgmconfig;

import lombok.Getter;
import lombok.Setter;

public class ConfigEntry<T> {

    @Getter @Setter private String name;
    @Getter @Setter private T value;

    public ConfigEntry(String name, T value) {
        this.name = name;
        this.value = value;
    }

}