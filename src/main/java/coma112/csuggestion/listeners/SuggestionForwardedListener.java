package coma112.csuggestion.listeners;

import coma112.csuggestion.enums.keys.ConfigKeys;
import coma112.csuggestion.events.SuggestionForwardedEvent;
import coma112.csuggestion.hooks.Webhook;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.io.IOException;
import java.net.URISyntaxException;

import static coma112.csuggestion.hooks.Webhook.replacePlaceholdersSuggestionForwarded;

public class SuggestionForwardedListener implements Listener {
    @EventHandler
    public void onForward(final SuggestionForwardedEvent event) throws IOException, NoSuchFieldException, IllegalAccessException, URISyntaxException {
        Webhook.sendWebhook(
                ConfigKeys.WEBHOOK_SUGGESTION_FORWARDED_EMBED_URL.getString(),
                ConfigKeys.FORWARD_ON_CLICK.getBoolean(),
                replacePlaceholdersSuggestionForwarded(ConfigKeys.WEBHOOK_SUGGESTION_FORWARDED_EMBED_DESCRIPTION.getString(), event),
                ConfigKeys.WEBHOOK_SUGGESTION_FORWARDED_EMBED_COLOR.getString(),
                replacePlaceholdersSuggestionForwarded(ConfigKeys.WEBHOOK_SUGGESTION_FORWARDED_EMBED_AUTHOR_NAME.getString(), event),
                replacePlaceholdersSuggestionForwarded(ConfigKeys.WEBHOOK_SUGGESTION_FORWARDED_EMBED_AUTHOR_URL.getString(), event),
                replacePlaceholdersSuggestionForwarded(ConfigKeys.WEBHOOK_SUGGESTION_FORWARDED_EMBED_AUTHOR_ICON.getString(), event),
                replacePlaceholdersSuggestionForwarded(ConfigKeys.WEBHOOK_SUGGESTION_FORWARDED_EMBED_FOOTER_TEXT.getString(), event),
                replacePlaceholdersSuggestionForwarded(ConfigKeys.WEBHOOK_SUGGESTION_FORWARDED_EMBED_FOOTER_ICON.getString(), event),
                replacePlaceholdersSuggestionForwarded(ConfigKeys.WEBHOOK_SUGGESTION_FORWARDED_EMBED_THUMBNAIL.getString(), event),
                replacePlaceholdersSuggestionForwarded(ConfigKeys.WEBHOOK_SUGGESTION_FORWARDED_EMBED_TITLE.getString(), event),
                replacePlaceholdersSuggestionForwarded(ConfigKeys.WEBHOOK_SUGGESTION_FORWARDED_EMBED_IMAGE.getString(), event)
        );
    }
}
