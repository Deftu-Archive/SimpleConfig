package ga.matthewtgm.tgmconfig;

import lombok.Getter;
import lombok.Setter;

public class ConfigEntry<T> {

    @Getter @Setter private String name;
    @Getter @Setter private T type;

    public ConfigEntry(String name, T type) {
        this.name = name;
        this.type = type;
    }

}