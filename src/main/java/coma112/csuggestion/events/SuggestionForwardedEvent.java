package coma112.csuggestion.events;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Getter
public final class SuggestionForwardedEvent extends Event {
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
}