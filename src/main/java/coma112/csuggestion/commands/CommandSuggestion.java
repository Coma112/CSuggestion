package coma112.csuggestion.commands;

import coma112.csuggestion.CSuggestion;
import coma112.csuggestion.enums.keys.MessageKeys;
import coma112.csuggestion.menu.menus.SuggestionMenu;
import coma112.csuggestion.utils.MenuUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.annotation.DefaultFor;
import revxrsal.commands.annotation.Subcommand;
import revxrsal.commands.bukkit.annotation.CommandPermission;

@Command({"suggestion", "csuggestion"})
public class CommandSuggestion {
    @DefaultFor({"suggestion", "csuggestion"})
    public void defaultCommand(@NotNull CommandSender sender) {
        help(sender);
    }

    @Subcommand("help")
    public void help(@NotNull CommandSender sender) {
        MessageKeys.HELP
                .getMessages()
                .forEach(sender::sendMessage);
    }


    @Subcommand("new")
    @CommandPermission("csuggestion.use")
    public void newSuggestion(@NotNull Player player, @NotNull String suggestion) {
        Bukkit.getOnlinePlayers().forEach(onlinePlayers -> {
            if (onlinePlayers.hasPermission("csuggestion.admin")) {
                onlinePlayers.sendMessage(MessageKeys.ADMIN_MESSAGE.getMessage()
                        .replace("{player}", player.getName())
                        .replace("{suggestion}", suggestion));
            }
        });

        player.sendMessage(MessageKeys.SUCCESSFUL_SUGGESTION.getMessage());

        CSuggestion.getDatabaseManager().createSuggestion(player, (suggestion + " ").trim());
    }

    @Subcommand("reload")
    @CommandPermission("csuggestion.reload")
    public void reload(@NotNull CommandSender sender) {
        CSuggestion.getInstance().getLanguage().reload();
        CSuggestion.getInstance().getConfiguration().reload();
        sender.sendMessage(MessageKeys.RELOAD.getMessage());
    }

    @Subcommand("menu")
    @CommandPermission("csuggestion.menu")
    public void menu(@NotNull Player player) {
        new SuggestionMenu(MenuUtils.getMenuUtils(player)).open();
    }
}
