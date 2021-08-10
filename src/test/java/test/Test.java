package test;

import net.auoeke.jon.Jon;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;

public class Test {
    public static final PreLaunchEntrypoint init = () -> {
        var locale = Jon.locale("en_us");
        locale.put("gui.done", "bruh momentum");
    };
}
