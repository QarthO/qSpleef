package gg.quartzdev.qspleef.game.arena;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.jetbrains.annotations.NotNull;

import java.util.*;

@SerializableAs("qSpleefArena")
public class Arena implements ConfigurationSerializable {

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

//    Deserializes from arena storage file
    public Arena(Map<String, Object> map){
        this.id = UUID.fromString((String) map.get("id"));
        this.name = (String) map.get("name");
        this.state = ArenaState.valueOf((String) map.get("state"));
        this.floorMaterial = Material.getMaterial((String) map.get("floor-material"));
//        this.minY = (int) map.get("bottom-floor-y-level");
        this.minPlayers = (int) map.get("min-players");
        this.maxPlayers = (int) map.get("max-players");
//        this.joinLocation = (Location) map.get("join-location");
//        this.leaveLocation = (Location) map.get("leave-location");
//        this.spectateLocation = (Location) map.get("spectate-location");
//        this.snowballs = (boolean) map.get("snowballs");
//        this.antiCamping = (boolean) map.get("anti-camping");
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

    public Material getFloorMaterial(){
//        defaults to Snow_Block if no given material
        return this.floorMaterial == null ? Material.SNOW_BLOCK : this.floorMaterial;
    }

    public int getMinPlayers(){
        return this.minPlayers;
    }

    public int getMaxPlayers(){
        return this.maxPlayers;
    }

    @Override
    public @NotNull LinkedHashMap<String, Object> serialize() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("id", this.getID().toString());
        map.put("name", this.getName());
        map.put("state", this.getState().name());
        map.put("floor-material", this.getFloorMaterial().name());
        map.put("min-players", this.getMinPlayers());
        map.put("max-players", this.getMaxPlayers());
        return map;
    }

    public static Arena deserialize(Map<String, Object> map) {
        Arena arena = new Arena(map);
        return arena;
    }
}