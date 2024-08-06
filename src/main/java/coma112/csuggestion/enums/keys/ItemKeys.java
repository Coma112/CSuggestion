package coma112.csuggestion.enums.keys;

import coma112.csuggestion.item.IItemBuilder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public enum ItemKeys {
    SUGGESTION_ITEM("suggestion-item"),
    FILLER_GLASS_ITEM("filler-glass-item"),

    MENU_BACK_ITEM("menu.back-item"),
    MENU_FORWARD_ITEM("menu.forward-item");

    private final String path;

    ItemKeys(@NotNull final String path) {
        this.path = path;
    }

    public ItemStack getItem() {
        return IItemBuilder.createItemFromString(path);
    };
}
