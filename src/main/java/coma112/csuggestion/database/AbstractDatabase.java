package coma112.csuggestion.database;

import coma112.csuggestion.managers.Suggestion;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public abstract class AbstractDatabase {
    public abstract boolean isConnected();
    public abstract void disconnect();

    public abstract void createSuggestion(@NotNull Player player, @NotNull String suggestionText);

    public abstract List<Suggestion> getSuggestions();

    public abstract String getPlayer(@NotNull Suggestion suggestion);

    public abstract void deleteSuggestion(int id);

    public abstract void reconnect();
}
