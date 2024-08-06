package coma112.csuggestion.enums.keys;

import coma112.csuggestion.CSuggestion;
import coma112.csuggestion.processor.MessageProcessor;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public enum MessageKeys {
    RELOAD("messages.reload"),
    FIRST_PAGE("messages.first-page"),
    LAST_PAGE("messages.last-page"),
    ADMIN_MESSAGE("messages.admin-message"),
    SUCCESSFUL_SUGGESTION("messages.successful-suggestion"),
    HELP("messages.help");


    private final String path;

    MessageKeys(@NotNull String path) {
        this.path = path;
    }

    public String getMessage() {
        return MessageProcessor.process(CSuggestion.getInstance().getLanguage().getString(path));
    }

    public List<String> getMessages() {
        return CSuggestion.getInstance().getLanguage().getList(path)
                .stream()
                .map(MessageProcessor::process)
                .toList();
    }
}
