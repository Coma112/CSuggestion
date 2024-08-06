package coma112.csuggestion.version;

import coma112.csuggestion.utils.SuggestionLogger;
import lombok.Getter;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;

@Getter
public class VersionSupport {
    private final ServerVersionSupport versionSupport;

    public VersionSupport(@NotNull Plugin plugin, @NotNull MinecraftVersion version) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        if (version == MinecraftVersion.UNKNOWN) throw new IllegalArgumentException("VERSION NOT FOUND!!!");


        Class<?> clazz = Class.forName("coma112.csuggestion.version.nms." + version.name() + ".Version");
        versionSupport = (ServerVersionSupport) clazz.getConstructor(Plugin.class).newInstance(plugin);

        if (!versionSupport.isSupported()) {
            SuggestionLogger.warn("---   VERSION IS SUPPORTED BUT,   ---");
            SuggestionLogger.warn("The version you are using is badly");
            SuggestionLogger.warn("implemented. Many features won't work.");
            SuggestionLogger.warn("Please consider updating your server ");
            SuggestionLogger.warn("version to a newer version. (like 1.19_R2)");
            SuggestionLogger.warn("---   PLEASE READ THIS MESSAGE!   ---");
        }

        SuggestionLogger.info("Version support for {} loaded!", version);
    }
}
