package coma112.csuggestion.language;

import coma112.csuggestion.CSuggestion;
import coma112.csuggestion.utils.ConfigUtils;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class Language extends ConfigUtils {
    public Language(@NotNull String name) {
        super(CSuggestion.getInstance().getDataFolder().getPath() + File.separator + "locales", name);
        save();
    }
}
