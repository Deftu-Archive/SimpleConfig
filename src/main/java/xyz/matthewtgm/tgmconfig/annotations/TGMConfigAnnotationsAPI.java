package xyz.matthewtgm.tgmconfig.annotations;

import xyz.matthewtgm.tgmconfig.ConfigEntry;
import xyz.matthewtgm.tgmconfig.TGMConfig;
import xyz.matthewtgm.tgmconfig.annotations.options.BaseOption;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class TGMConfigAnnotationsAPI {

    public static TGMConfig handle(Object o) {
        try {
            TGMConfig config = new TGMConfig(o.getClass().getSimpleName());
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

}