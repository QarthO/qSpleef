package gg.quartzdev.qspleef.game.players;

import gg.quartzdev.qspleef.game.arena.Arena;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.UUID;

public class SpleefPlayer {

    private Player player;
    private Arena currentArena;

    private Inventory playerInventory;

    private SpleefPlayerState state;

    public SpleefPlayer(Player player, SpleefPlayerState state) {
        this.player = player;
        this.state = state;
    }

    //
    public Player getPlayer() {
        return this.player;
    }

    public void setArena(Arena arena){
        this.currentArena = arena;
    }

    public Arena getArena(){
        return this.currentArena;
    }

    public SpleefPlayerState getState(){
        return this.state;
    }

    public void setState(SpleefPlayerState state){
        switch(state){
            case JOINING:

            case PLAYING:

            case SPECTATING:

            this.state = state;
        }

        this.state = state;
    }

//    public void
}
