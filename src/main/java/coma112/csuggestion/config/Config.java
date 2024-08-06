package coma112.csuggestion.config;


import coma112.csuggestion.CSuggestion;
import coma112.csuggestion.utils.ConfigUtils;

public class Config extends ConfigUtils {
    public Config() {
        super(CSuggestion.getInstance().getDataFolder().getPath(), "config");
        save();
    }
}

