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
    MENU_TICK("menu.update-tick"),

    WEBHOOK_SUGGESTION_CREATED_EMBED_URL("webhook.suggestion-created-embed.url"),
    WEBHOOK_SUGGESTION_CREATED_EMBED_ENABLED("webhook.suggestion-created-embed.enabled"),
    WEBHOOK_SUGGESTION_CREATED_EMBED_TITLE("webhook.suggestion-created-embed.title"),
    WEBHOOK_SUGGESTION_CREATED_EMBED_DESCRIPTION("webhook.suggestion-created-embed.description"),
    WEBHOOK_SUGGESTION_CREATED_EMBED_COLOR("webhook.suggestion-created-embed.color"),
    WEBHOOK_SUGGESTION_CREATED_EMBED_AUTHOR_NAME("webhook.suggestion-created-embed.author-name"),
    WEBHOOK_SUGGESTION_CREATED_EMBED_AUTHOR_URL("webhook.suggestion-created-embed.author-url"),
    WEBHOOK_SUGGESTION_CREATED_EMBED_AUTHOR_ICON("webhook.suggestion-created-embed.author-icon"),
    WEBHOOK_SUGGESTION_CREATED_EMBED_FOOTER_TEXT("webhook.suggestion-created-embed.footer-text"),
    WEBHOOK_SUGGESTION_CREATED_EMBED_FOOTER_ICON("webhook.suggestion-created-embed.footer-icon"),
    WEBHOOK_SUGGESTION_CREATED_EMBED_THUMBNAIL("webhook.suggestion-created-embed.thumbnail"),
    WEBHOOK_SUGGESTION_CREATED_EMBED_IMAGE("webhook.suggestion-created-embed.image"),

    WEBHOOK_SUGGESTION_FORWARDED_EMBED_URL("webhook.suggestion-forwarded-embed.url"),
    WEBHOOK_SUGGESTION_FORWARDED_EMBED_TITLE("webhook.suggestion-forwarded-embed.title"),
    WEBHOOK_SUGGESTION_FORWARDED_EMBED_DESCRIPTION("webhook.suggestion-forwarded-embed.description"),
    WEBHOOK_SUGGESTION_FORWARDED_EMBED_COLOR("webhook.suggestion-forwarded-embed.color"),
    WEBHOOK_SUGGESTION_FORWARDED_EMBED_AUTHOR_NAME("webhook.suggestion-forwarded-embed.author-name"),
    WEBHOOK_SUGGESTION_FORWARDED_EMBED_AUTHOR_URL("webhook.suggestion-forwarded-embed.author-url"),
    WEBHOOK_SUGGESTION_FORWARDED_EMBED_AUTHOR_ICON("webhook.suggestion-forwarded-embed.author-icon"),
    WEBHOOK_SUGGESTION_FORWARDED_EMBED_FOOTER_TEXT("webhook.suggestion-forwarded-embed.footer-text"),
    WEBHOOK_SUGGESTION_FORWARDED_EMBED_FOOTER_ICON("webhook.suggestion-forwarded-embed.footer-icon"),
    WEBHOOK_SUGGESTION_FORWARDED_EMBED_THUMBNAIL("webhook.suggestion-forwarded-embed.thumbnail"),
    WEBHOOK_SUGGESTION_FORWARDED_EMBED_IMAGE("webhook.suggestion-forwarded-embed.image");

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
