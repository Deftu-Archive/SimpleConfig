package xyz.qalcyo.simpleconfig;

public class BasicTest {

    private static final BasicTest INSTANCE = new BasicTest();

    public void start() {
        Configuration configuration = Configuration.empty("basic");
        configuration.add("test", "test");
        configuration.add("boolean", false);

        System.out.println(configuration.getAsString("test"));
        System.out.println(configuration.getAsBool("boolean"));

        Subconfiguration subconfiguration = configuration.createSubconfiguration("subconfiguration", false);
        subconfiguration.add("test", "test");
        subconfiguration.add("integer", 30);
        System.out.println(subconfiguration);

        configuration.save();
    }

    public static void main(String[] args) {
        INSTANCE.start();
    }

}