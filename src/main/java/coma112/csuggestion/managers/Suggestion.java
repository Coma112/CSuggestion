package coma112.csuggestion.managers;

import org.jetbrains.annotations.NotNull;

public record Suggestion(int id, @NotNull String player, @NotNull String suggestion) {}
