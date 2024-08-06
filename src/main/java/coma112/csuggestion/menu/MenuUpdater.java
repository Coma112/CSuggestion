package coma112.csuggestion.menu;

import com.github.Anon8281.universalScheduler.scheduling.tasks.MyScheduledTask;
import coma112.csuggestion.CSuggestion;
import org.jetbrains.annotations.NotNull;

public class MenuUpdater {
    private final Menu menu;
    private MyScheduledTask task;
    private boolean running = true;

    public MenuUpdater(@NotNull Menu menu) {
        this.menu = menu;
    }

    public void run() {
        if (!running) {
            stop();
            return;
        }

        if (menu.getInventory().getViewers().contains(menu.menuUtils.getOwner())) menu.updateMenuItems();
        else stop();
    }

    public void start(int intervalTicks) {
        if (task == null) task = CSuggestion.getInstance().getScheduler().runTaskTimer(this::run, intervalTicks, intervalTicks);
    }

    public void stop() {
        running = false;

        if (task != null) {
            task.cancel();
            task = null;
        }
    }
}

