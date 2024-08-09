package coma112.csuggestion.menu.menus;
import coma112.csuggestion.CSuggestion;
import coma112.csuggestion.enums.keys.ConfigKeys;
import coma112.csuggestion.enums.keys.ItemKeys;
import coma112.csuggestion.enums.keys.MessageKeys;
import coma112.csuggestion.managers.Suggestion;
import coma112.csuggestion.menu.PaginatedMenu;
import coma112.csuggestion.utils.MenuUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@SuppressWarnings("deprecation")
public class SuggestionMenu extends PaginatedMenu implements Listener {

    public SuggestionMenu(@NotNull MenuUtils menuUtils) {
        super(menuUtils);
    }

    @Override
    public void addMenuBorder() {
        inventory.setItem(ConfigKeys.MENU_BACK_SLOT.getInt(), ItemKeys.MENU_BACK_ITEM.getItem());
        inventory.setItem(ConfigKeys.MENU_FORWARD_SLOT.getInt(), ItemKeys.MENU_FORWARD_ITEM.getItem());
        setFillerGlass();
    }

    @Override
    public List<Suggestion> getList() {
        return CSuggestion.getDatabaseManager().getSuggestions();
    }

    @Override
    public String getMenuName() {
        return ConfigKeys.MENU_TITLE.getString();
    }

    @Override
    public int getSlots() {
        return ConfigKeys.MENU_SIZE.getInt();
    }

    @Override
    public int getMaxItemsPerPage() {
        return ConfigKeys.MENU_SIZE.getInt() - 2;
    }

    @Override
    public int getMenuTick() {
        return ConfigKeys.MENU_TICK.getInt();
    }

    @Override
    public boolean enableFillerGlass() {
        return ConfigKeys.MENU_FILLER_GLASS.getBoolean();
    }

    @Override
    public void setMenuItems() {
        inventory.clear();

        if (getList().isEmpty() || startIndex >= getList().size()) {
            addMenuBorder();
            return;
        }

        IntStream
                .range(startIndex, adjustedEndIndex)
                .forEach(index -> inventory.addItem(createSuggestionItem(getList().get(index))));

        addMenuBorder();
    }

    @EventHandler
    public void onClose(final InventoryCloseEvent event) {
        if (event.getInventory().equals(inventory)) close();
    }

    @Override
    public void handleMenu(final InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player player)) return;
        if (!event.getInventory().equals(inventory)) return;

        event.setCancelled(true);

        int clickedIndex = event.getSlot();

        if (clickedIndex == ConfigKeys.MENU_FORWARD_SLOT.getInt()) {
            if (nextPage >= totalPages) {
                player.sendMessage(MessageKeys.LAST_PAGE.getMessage());
                return;
            } else {
                page++;
                super.open();
            }
        }

        if (clickedIndex == ConfigKeys.MENU_BACK_SLOT.getInt()) {
            if (page == 0) {
                player.sendMessage(MessageKeys.FIRST_PAGE.getMessage());
            } else {
                page--;
                super.open();
            }
        }

        if (!ConfigKeys.FORWARD_ON_CLICK.getBoolean()) return;
        if (!player.hasPermission("csuggestion.forward")) return;

        if (clickedIndex >= 0 && clickedIndex < getList().size()) {
            inventory.close();
            CSuggestion.getDatabaseManager().deleteSuggestion(getList().get(clickedIndex).id());
        }
    }

    private static ItemStack createSuggestionItem(@NotNull Suggestion suggestion) {
        ItemStack itemStack = ItemKeys.SUGGESTION_ITEM.getItem();
        ItemMeta meta = itemStack.getItemMeta();

        if (meta != null) {
            String displayName = meta.getDisplayName()
                    .replace("{player}", suggestion.player())
                    .replace("{id}", String.valueOf(suggestion.id()))
                    .replace("{suggestion}", suggestion.suggestion());

            meta.setDisplayName(displayName);

            List<String> lore = meta.getLore();

            if (lore != null) {
                List<String> updatedLore = new ArrayList<>();

                lore.forEach(line -> {
                    String updatedLine = line
                            .replace("{suggestion}", suggestion.suggestion())
                            .replace("{player}", suggestion.player())
                            .replace("{id}", String.valueOf(suggestion.id()));

                    updatedLore.add(updatedLine);
                });   

                meta.setLore(updatedLore);
            }
        }

        itemStack.setItemMeta(meta);
        return itemStack;
    }
}
