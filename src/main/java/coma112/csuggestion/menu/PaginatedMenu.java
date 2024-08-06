package coma112.csuggestion.menu;

import coma112.csuggestion.managers.Suggestion;
import coma112.csuggestion.utils.MenuUtils;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public abstract class PaginatedMenu extends Menu {
    public abstract void addMenuBorder();

    protected int page = 0;
    protected int nextPage = page + 1;
    protected int totalPages = (int) Math.ceil((double) getList().size() / getMaxItemsPerPage());
    protected int startIndex = page * getMaxItemsPerPage();
    protected int endIndex = Math.min(startIndex + getMaxItemsPerPage(), getList().size());
    protected int adjustedEndIndex = Math.min(endIndex, getList().size());

    public abstract int getMaxItemsPerPage();
    public abstract List<Suggestion> getList();

    public PaginatedMenu(@NotNull MenuUtils menuUtils) {
        super(menuUtils);
    }
}

