package coma112.csuggestion.listeners;

import coma112.csuggestion.events.SuggestionCreatedEvent;
import coma112.csuggestion.hooks.Webhook;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.io.IOException;
import java.net.URISyntaxException;

public class SuggestionCreatedListener implements Listener {
    @EventHandler
    public void onCreate(final SuggestionCreatedEvent event) throws IOException, URISyntaxException {
        Webhook.sendWebhookFromString("webhook.suggestion-created-embed", event);
    }
}
