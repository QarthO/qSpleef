package gg.quartzdev.qspleef.events;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class SpleefStartEvent extends Event implements Cancellable {

    private final @NotNull boolean spleefGame;

    private static final HandlerList HANDLERS = new HandlerList();
    private boolean cancelled = false;

    public SpleefStartEvent(@NotNull boolean spleefGame){
        this.spleefGame = spleefGame;
    }

    public boolean getSpleefGame(){
        return this.spleefGame;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public boolean isCancelled() {
        return this.cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
