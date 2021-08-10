package net.auoeke.jon.mixin;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.auoeke.jon.Jon;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.resource.language.LanguageDefinition;
import net.minecraft.client.resource.language.TranslationStorage;
import net.minecraft.resource.ResourceManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(TranslationStorage.class)
@Environment(EnvType.CLIENT)
abstract class TranslationStorageMixin {
    @Inject(method = "load(Lnet/minecraft/resource/ResourceManager;Ljava/util/List;)Lnet/minecraft/client/resource/language/TranslationStorage;",
            at = @At(value = "INVOKE",
                     target = "Lnet/minecraft/client/resource/language/TranslationStorage;load(Ljava/util/List;Ljava/util/Map;)V",
                     shift = At.Shift.AFTER),
            locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    private static void addJonTranslations(
        ResourceManager resourceManager,
        List<LanguageDefinition> definitions,
        CallbackInfoReturnable<TranslationStorage> info,
        Map<String, String> translations,
        boolean rtl,
        Iterator<LanguageDefinition> definitionIterator,
        LanguageDefinition definition,
        String path,
        Iterator<?> namespaces,
        String namespace) {
        var locale = Jon.locales.get(definition.getCode());

        if (locale != null) {
            // speeeeeeed
            //noinspection StringEquality
            if (namespace == "minecraft") {
                locale.forEach(translations::putAll);
            } else {
                locale.forEach(map -> map.forEach(translations::putIfAbsent));
            }
        }
    }
}
