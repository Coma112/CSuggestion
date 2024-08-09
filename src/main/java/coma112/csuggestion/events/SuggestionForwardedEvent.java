package coma112.csuggestion.events;

import coma112.csuggestion.interfaces.PlaceholderProvider;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Getter
public final class SuggestionForwardedEvent extends Event implements PlaceholderProvider {
    private static final HandlerList handlers = new HandlerList();
    private final Player player;
    private final String suggestion;

    public SuggestionForwardedEvent(@Nullable Player player, @NotNull String suggestion) {
        this.suggestion = suggestion;
        this.player = player;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    @Override
    public Map<String, String> getPlaceholders() {
        Map<String, String> placeholders = new HashMap<>();

        placeholders.put("{player}", Objects.requireNonNull(Objects.requireNonNull(player).getName()));
        placeholders.put("{suggestion}", Objects.requireNonNull(suggestion));

        return placeholders;
    }
}
