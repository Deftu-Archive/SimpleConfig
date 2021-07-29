package xyz.matthewtgm.tgmconfig;

import java.io.File;

public class TestApplication {

    public static TestApplication instance = new TestApplication();

    public void start() {
        Configuration configuration = new Configuration(new File("config"));
        configuration.add("hello", "yoo");
        configuration.save();
    }

}