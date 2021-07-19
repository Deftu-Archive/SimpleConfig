package xyz.matthewtgm.tgmconfig;

import xyz.matthewtgm.tgmconfig.annotations.options.impl.*;

public class TestApplication {

    public static TestApplication instance = new TestApplication();

    public void start() {
        TGMConfig config = new TGMConfig("noo");
        config.add(new ConfigEntry<>("bool", new BooleanOption()));
        System.out.println(config);
    }

}