package coma112.csuggestion.listeners;

import coma112.csuggestion.events.SuggestionForwardedEvent;
import coma112.csuggestion.hooks.Webhook;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.io.IOException;
import java.net.URISyntaxException;

public class SuggestionForwardedListener implements Listener {
    @EventHandler
    public void onForward(final SuggestionForwardedEvent event) throws IOException, URISyntaxException {
        Webhook.sendWebhookFromString("webhook.suggestion-forwarded-embed", event);
    }
}
