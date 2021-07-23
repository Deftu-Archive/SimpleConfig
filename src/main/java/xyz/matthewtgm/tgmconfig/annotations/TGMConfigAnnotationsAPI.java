package xyz.matthewtgm.tgmconfig.annotations;

import xyz.matthewtgm.tgmconfig.ConfigEntry;
import xyz.matthewtgm.tgmconfig.TGMConfig;
import xyz.matthewtgm.tgmconfig.annotations.options.BaseOption;

import java.io.File;
import java.lang.reflect.Field;

public class TGMConfigAnnotationsAPI {

    public static TGMConfig handle(TGMConfig config, Object o) {
        try {
            Class<?> clz = o.getClass();
            for (Field field : clz.getDeclaredFields()) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(ConfigOption.class)) {
                    ConfigOption option = field.getAnnotation(ConfigOption.class);
                    String name = option.value();
                    if (name == null || name.isEmpty())
                        name = field.getName();
                    Object gotten = field.get(o);
                    if (gotten instanceof BaseOption<?>)
                        gotten = ((BaseOption<?>) gotten).get();
                    config.add(new ConfigEntry<>(name, gotten));
                }
            }
            return config;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static TGMConfig handle(String name, File directory, Object o) {
        return handle(new TGMConfig(name, directory), o);
    }

    public static TGMConfig handle(String name, Object o) {
        return handle(new TGMConfig(name), o);
    }

    public static TGMConfig handle(Object o) {
        return handle(new TGMConfig(o.getClass().getSimpleName()), o);
    }

}