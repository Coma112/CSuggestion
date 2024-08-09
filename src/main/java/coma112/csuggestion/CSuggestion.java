package coma112.csuggestion;

import com.github.Anon8281.universalScheduler.UniversalScheduler;
import com.github.Anon8281.universalScheduler.scheduling.schedulers.TaskScheduler;
import coma112.csuggestion.config.Config;
import coma112.csuggestion.database.AbstractDatabase;
import coma112.csuggestion.database.MySQL;
import coma112.csuggestion.database.SQLite;
import coma112.csuggestion.enums.DatabaseType;
import coma112.csuggestion.enums.LanguageType;
import coma112.csuggestion.enums.keys.ConfigKeys;
import coma112.csuggestion.language.Language;
import coma112.csuggestion.utils.SuggestionLogger;
import coma112.csuggestion.utils.StartingUtils;
import lombok.Getter;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;
import java.util.Objects;

import static coma112.csuggestion.utils.StartingUtils.registerListenersAndCommands;
import static coma112.csuggestion.utils.StartingUtils.saveResourceIfNotExists;

public final class CSuggestion extends JavaPlugin {

    @Getter private static CSuggestion instance;
    @Getter private static AbstractDatabase databaseManager;
    @Getter private Language language;
    @Getter private TaskScheduler scheduler;
    private Config config;

    @Override
    public void onLoad() {
        instance = this;
        scheduler = UniversalScheduler.getScheduler(this);

        StartingUtils.checkVersion();
    }

    @Override
    public void onEnable() {
        StartingUtils.checkVM();
        saveDefaultConfig();
        initializeComponents();
        registerListenersAndCommands();
        initializeDatabaseManager();

        new Metrics(this, 22909);
    }

    @Override
    public void onDisable() {
        if (databaseManager != null) databaseManager.disconnect();
    }

    public Config getConfiguration() {
        return config;
    }

    private void initializeComponents() {
        config = new Config();

        saveResourceIfNotExists("locales/messages_en.yml");
        saveResourceIfNotExists("locales/messages_hu.yml");
        saveResourceIfNotExists("locales/messages_de.yml");
        saveResourceIfNotExists("locales/messages_es.yml");

        language = new Language("messages_" + LanguageType.valueOf(ConfigKeys.LANGUAGE.getString()));
    }

    private void initializeDatabaseManager() {
        try {
            switch (DatabaseType.valueOf(ConfigKeys.DATABASE.getString())) {
                case MYSQL, mysql -> {
                    databaseManager = new MySQL(Objects.requireNonNull(getConfiguration().getSection("database.mysql")));
                    MySQL mySQL = (MySQL) databaseManager;
                    mySQL.createTable();
                }

                case SQLITE, sqlite -> {
                    databaseManager = new SQLite();
                    SQLite sqLite = (SQLite) databaseManager;
                    sqLite.createTable();
                }
            }
        } catch (SQLException | ClassNotFoundException exception) {
            SuggestionLogger.error(exception.getMessage());
        }
    }
}
