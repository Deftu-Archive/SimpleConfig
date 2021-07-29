package xyz.matthewtgm.tgmconfig;

import lombok.Getter;
import xyz.matthewtgm.json.entities.JsonObject;
import xyz.matthewtgm.tgmconfig.interfaces.IConfigurable;

public final class Subconfiguration implements IConfigurable {

    @Getter private final IConfigurable parent;
    @Getter private final JsonObject config;

    public Subconfiguration(IConfigurable parent, JsonObject config) {
        this.parent = parent;
        this.config = config;
    }

    public Subconfiguration(IConfigurable parent) {
        this(parent, new JsonObject());
    }

    public Configuration getAsConfiguration() {
        return parent.getAsConfiguration();
    }

    public Subconfiguration getAsSubconfiguration() {
        return this;
    }

    public JsonObject jsonify() {
        return config.copy();
    }

}