package com.wzz.w_loader.util;

import com.wzz.w_loader.bootstrap.Bootstrap;
import com.wzz.w_loader.logger.WLogger;

import java.lang.instrument.Instrumentation;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class InstrumentationUtil {
    private static final Instrumentation INSTRUMENTATION;
    static {
        Instrumentation instrumentation1;
        instrumentation1 = Bootstrap.getINSTRUMENTATION();
        if (instrumentation1 == null){
            try {
                Field InstField = Bootstrap.class.getDeclaredField("INSTRUMENTATION");
                InstField.setAccessible(true);
                instrumentation1 = (Instrumentation) InstField.get(null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
        INSTRUMENTATION = instrumentation1;
    }
    public static void unlockUniverse() {
        Module myModule = InstrumentationUtil.class.getModule();
        Set<Module> targets = Set.of(myModule);

        ModuleLayer.boot().modules().forEach(m -> {
            Set<String> packages = m.getPackages();
            try {
                INSTRUMENTATION.redefineModule(
                        m,
                        Set.of(),
                        Map.of(),
                        packages.stream().collect(Collectors.toMap(p -> p, p -> targets)),
                        Set.of(),
                        Map.of()
                );
            } catch (Exception e) {
                WLogger.warn("Could not unlock module: " + m.getName());
            }
        });
        WLogger.info("Module Layer Unlocked for W_loader.");
    }

    public static Object getInternal(Object obj, String fieldName) {
        try {
            Field f = obj.getClass().getDeclaredField(fieldName);
            f.setAccessible(true);
            return f.get(obj);
        } catch (Exception e) {
            WLogger.error("Failed to get field: " + fieldName);
            return null;
        }
    }

    public static Instrumentation getINSTRUMENTATION() {
        return INSTRUMENTATION;
    }
}
