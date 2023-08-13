package gg.quartzdev.qspleef;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class SpleefPlayer {

    private OfflinePlayer player;
    private boolean isActive;
    private int kills;
    private int gamesPlayed;
    private int wins;
    private int currentArenaID;

    public SpleefPlayer(Player player){
        this.player = player;
    }
    public SpleefPlayer(Player player, int currentArenaID){
        super();
        this.currentArenaID = currentArenaID;
        this.isActive = true;
    }
//
    public OfflinePlayer getPlayer(){
        return this.player;
    }

//    Get a players number of wins
    public int getWins(){
        return this.wins;
    }
//    Set a players number of wins
    public void setWins(int wins){
        this.wins = wins;
    }
//    Add one win to a player
    public void addWin(){
        this.wins++;
    }
}
