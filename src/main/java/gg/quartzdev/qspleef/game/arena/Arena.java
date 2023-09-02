package gg.quartzdev.qspleef.game.arena;

import org.bukkit.Location;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Arena {

    private UUID id;
    private String name;

    private ArenaState state;
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
    public Arena(String name){
        this.id = UUID.randomUUID();
        this.setState(ArenaState.INCOMPLETE);
        this.name = name;
    }

    public Arena(UUID id, String name, Material floorMaterial, int minY, int minPlayers, int maxPlayers, Location joinLocation, Location leaveLocation, Location spectateLocation, boolean snowballs, int snowballPerBlock, boolean antiCamping, int campingDelay){
        this.id = id;
        this.name = name;
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

    public List<String> isSetup(){
        List<String> missing = new ArrayList<>();
//        If missing and id or name, something is broken
        if(this.id == null) return null;
        if(this.name == null) return null;

//        Minimum Requirements for an arena to be playable
        if(this.joinLocation == null) missing.add("Join location");
        if(this.leaveLocation == null) missing.add("Leave Location");
        if(this.spectateLocation == null) missing.add("Spectate Location");

        return missing;
    }

    public ArenaState getState(){
        return this.state;
    }

    public void setState(ArenaState state){
        this.state = state;
    }

    public String getName(){
        return this.name;
    }

    public UUID getID(){
        return this.id;
    }
}