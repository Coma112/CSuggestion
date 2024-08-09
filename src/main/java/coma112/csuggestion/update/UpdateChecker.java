package coma112.csuggestion.update;

import coma112.csuggestion.CSuggestion;
import coma112.csuggestion.utils.SuggestionLogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;
import java.util.function.Consumer;

public class UpdateChecker {
    private final int resourceId;

    public UpdateChecker(int resourceId) {
        this.resourceId = resourceId;
    }

    public void getVersion(final Consumer<String> consumer) {
        CSuggestion.getInstance().getScheduler().runTaskAsynchronously(() -> {
            try {
                URI uri = new URI("https://api.spigotmc.org/legacy/update.php?resource=" + this.resourceId + "/~");
                HttpURLConnection connection = (HttpURLConnection) uri.toURL().openConnection();

                connection.setRequestMethod("GET");

                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    Scanner scanner = new Scanner(reader);

                    if (scanner.hasNext()) consumer.accept(scanner.next());
                } catch (IOException exception) {
                    SuggestionLogger.warn(exception.getMessage());
                }

            } catch (URISyntaxException | IOException exception) {
                SuggestionLogger.warn(exception.getMessage());
            }
        });
    }
}
