import xyz.deftu.simpleconfig.Configuration;
import xyz.deftu.simpleconfig.Subconfiguration;

import java.io.File;

public class TestApplication {

    public static final TestApplication instance = new TestApplication();

    public void start() {
        Configuration configuration = new Configuration(new File("./core/config"));
        configuration.createSubconfiguration("games");
        Subconfiguration games = configuration.getSubconfiguration("games");
        games.createSubconfiguration("first_person_shooters");
        Subconfiguration firstPersonShooters = games.getSubconfiguration("first_person_shooters");
        firstPersonShooters.add("call_of_duty", true);
        configuration.save();
    }

    public static void main(String[] args) {
        instance.start();
    }

}