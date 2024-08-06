package coma112.csuggestion.version.nms.v1_19_R2;

import coma112.csuggestion.utils.SuggestionLogger;
import coma112.csuggestion.version.ServerVersionSupport;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class Version implements ServerVersionSupport {

    @Contract(pure = true)
    public Version(@NotNull Plugin plugin) {
        SuggestionLogger.info("Loading support for version 1.19.2...");
        SuggestionLogger.info("Support for 1.19.2 is loaded!");
    }

    @Override
    public String getName() {
        return "1.19_R2";
    }

    @Override
    public boolean isSupported() {
        return true;
    }
}
