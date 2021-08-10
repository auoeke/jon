package net.auoeke.jon;

import it.unimi.dsi.fastutil.objects.Object2ReferenceOpenHashMap;
import it.unimi.dsi.fastutil.objects.Reference2ReferenceOpenHashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Jon {
    public static final Map<String, List<Map<String, String>>> locales = new Object2ReferenceOpenHashMap<>();

    public static Map<String, String> locale(String code) {
        var locale = new Reference2ReferenceOpenHashMap<String, String>();
        locales.computeIfAbsent(code, code2 -> new ArrayList<>()).add(locale);

        return locale;
    }
}
