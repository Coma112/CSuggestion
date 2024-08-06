package coma112.csuggestion.utils;

import coma112.csuggestion.CSuggestion;
import coma112.csuggestion.commands.CommandSuggestion;
import coma112.csuggestion.listeners.SuggestionCreatedListener;
import coma112.csuggestion.listeners.SuggestionForwardedListener;
import coma112.csuggestion.menu.MenuListener;
import org.bukkit.event.Listener;
import revxrsal.commands.bukkit.BukkitCommandHandler;

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("deprecation")
public class RegisterUtils {
    public static void registerEvents() {
        getListenerClasses().forEach(clazz -> {
            try {
                CSuggestion.getInstance().getServer().getPluginManager().registerEvents(clazz.newInstance(), CSuggestion.getInstance());
            } catch (InstantiationException | IllegalAccessException exception) {
                SuggestionLogger.error(exception.getMessage());
            }
        });
    }

    public static void registerCommands() {
        BukkitCommandHandler handler = BukkitCommandHandler.create(CSuggestion.getInstance());

        handler.register(new CommandSuggestion());
    }

    private static Set<Class<? extends Listener>> getListenerClasses() {
        Set<Class<? extends Listener>> listenerClasses = new HashSet<>();

        listenerClasses.add(MenuListener.class);
        listenerClasses.add(SuggestionCreatedListener.class);
        listenerClasses.add(SuggestionForwardedListener.class);

        return listenerClasses;
    }
}
