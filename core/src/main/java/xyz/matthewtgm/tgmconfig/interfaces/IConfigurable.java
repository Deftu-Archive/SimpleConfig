package xyz.matthewtgm.tgmconfig.interfaces;

import xyz.matthewtgm.json.entities.JsonObject;
import xyz.matthewtgm.tgmconfig.Configuration;
import xyz.matthewtgm.tgmconfig.Subconfiguration;

public interface IConfigurable {
    Configuration getAsConfiguration();
    Subconfiguration getAsSubconfiguration();
    JsonObject jsonify();
}