package coma112.csuggestion.enums.keys;

import coma112.csuggestion.CSuggestion;
import coma112.csuggestion.processor.MessageProcessor;
import org.jetbrains.annotations.NotNull;

public enum ConfigKeys {
    LANGUAGE("language"),
    DATABASE("database.type"),
    FORWARD_ON_CLICK("forward-on-click"),

    MENU_TITLE("menu.title"),
    MENU_FORWARD_SLOT("menu.forward-item.slot"),
    MENU_BACK_SLOT("menu.back-item.slot"),
    MENU_SIZE("menu.size"),
    MENU_FILLER_GLASS("menu.filler-glass"),
    MENU_TICK("menu.update-tick");

    private final String path;

    ConfigKeys(@NotNull final String path) {
        this.path = path;
    }

    public String getString() {
        return MessageProcessor.process(CSuggestion.getInstance().getConfiguration().getString(path));
    }

    public boolean getBoolean() {
        return CSuggestion.getInstance().getConfiguration().getBoolean(path);
    }

    public int getInt() {
        return CSuggestion.getInstance().getConfiguration().getInt(path);
    }

}
