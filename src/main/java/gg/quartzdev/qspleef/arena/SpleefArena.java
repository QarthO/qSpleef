package gg.quartzdev.qspleef.arena;

import org.bukkit.Location;
import org.bukkit.Material;

import java.util.UUID;

public class SpleefArena {

    private UUID arenaID;
    private Material floorMaterial;
    private int minY;
//    The min players an arena needs before auto-starting
    private int minPlayers;
    private int maxPlayers;
//    The location a player teleports to upon joining the arena
    private Location joinLocation;
//    The location a player teleports to upon leaving the arena
    private Location leaveLocation;
//    The location a player teleports to upon spectating the arena or after getting out during the game
    private Location spectateLocation;

//Gamerules
    private boolean autoStart;
    private int startTimer;
//    Snowballs (only 1 in inventory, but the amount of snowballs is determined by their exp level)
    private boolean snowballs;
    private int snowballPerBlock;
//    Prevent camping by destroying the blocks below players who arent moving after a certain amount of time
    private boolean antiCamping;
//    - the delay in seconds until the blocks are destroyed below the player after not moving
    private int campingDelay;
    public SpleefArena(){
        this.arenaID = UUID.randomUUID();
    }

    public SpleefArena(UUID arenaID, Material floorMaterial, int minY, int minPlayers, int maxPlayers, Location joinLocation, Location leaveLocation, Location spectateLocation, boolean snowballs, int snowballPerBlock, boolean antiCamping, int campingDelay){
        this.arenaID = arenaID;
        this.floorMaterial = floorMaterial;
        this.minY = minY;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
        this.joinLocation = joinLocation;
        this.leaveLocation = leaveLocation;
        this.spectateLocation = spectateLocation;
        this.snowballs = snowballs;
        this.snowballPerBlock = snowballPerBlock;
        this.antiCamping = antiCamping;
        this.campingDelay = campingDelay;
    }

    public boolean isSetup(){
        return true;
    }
}
