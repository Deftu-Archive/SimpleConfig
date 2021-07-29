import xyz.matthewtgm.json.entities.JsonObject;
import xyz.matthewtgm.tgmconfig.Configuration;
import xyz.matthewtgm.tgmconfig.Subconfiguration;

import java.io.File;

public class TestApplication {

    public static final TestApplication instance = new TestApplication();

    public void start() {
        Configuration configuration = new Configuration(new File("config"));
        configuration.createSubconfiguration("gaming", new JsonObject().add("gamingggg", "gg"));
        Subconfiguration subconfiguration = configuration.getSubconfiguration("gaming");
        subconfiguration.add("gaming2", "ez");
        configuration.save();
    }

    public static void main(String[] args) {
        instance.start();
    }

}