package coma112.csuggestion.listeners;

import coma112.csuggestion.enums.keys.ConfigKeys;
import coma112.csuggestion.events.SuggestionCreatedEvent;
import coma112.csuggestion.hooks.Webhook;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.io.IOException;
import java.net.URISyntaxException;

import static coma112.csuggestion.hooks.Webhook.replacePlaceholdersSuggestionCreated;

public class SuggestionCreatedListener implements Listener {
    @EventHandler
    public void onCreate(final SuggestionCreatedEvent event) throws IOException, NoSuchFieldException, IllegalAccessException, URISyntaxException {
        Webhook.sendWebhook(
                ConfigKeys.WEBHOOK_SUGGESTION_CREATED_EMBED_URL.getString(),
                ConfigKeys.WEBHOOK_SUGGESTION_CREATED_EMBED_ENABLED.getBoolean(),
                replacePlaceholdersSuggestionCreated(ConfigKeys.WEBHOOK_SUGGESTION_CREATED_EMBED_DESCRIPTION.getString(), event),
                ConfigKeys.WEBHOOK_SUGGESTION_CREATED_EMBED_COLOR.getString(),
                replacePlaceholdersSuggestionCreated(ConfigKeys.WEBHOOK_SUGGESTION_CREATED_EMBED_AUTHOR_NAME.getString(), event),
                replacePlaceholdersSuggestionCreated(ConfigKeys.WEBHOOK_SUGGESTION_CREATED_EMBED_AUTHOR_URL.getString(), event),
                replacePlaceholdersSuggestionCreated(ConfigKeys.WEBHOOK_SUGGESTION_CREATED_EMBED_AUTHOR_ICON.getString(), event),
                replacePlaceholdersSuggestionCreated(ConfigKeys.WEBHOOK_SUGGESTION_CREATED_EMBED_FOOTER_TEXT.getString(), event),
                replacePlaceholdersSuggestionCreated(ConfigKeys.WEBHOOK_SUGGESTION_CREATED_EMBED_FOOTER_ICON.getString(), event),
                replacePlaceholdersSuggestionCreated(ConfigKeys.WEBHOOK_SUGGESTION_CREATED_EMBED_THUMBNAIL.getString(), event),
                replacePlaceholdersSuggestionCreated(ConfigKeys.WEBHOOK_SUGGESTION_CREATED_EMBED_TITLE.getString(), event),
                replacePlaceholdersSuggestionCreated(ConfigKeys.WEBHOOK_SUGGESTION_CREATED_EMBED_IMAGE.getString(), event)
        );
    }
}
