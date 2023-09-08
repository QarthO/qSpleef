package gg.quartzdev.qspleef.game.arena;

import gg.quartzdev.qspleef.util.Logger;
import org.bukkit.GameRule;
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
    private int floorBottomLevel;
//    The min players an arena needs before auto-starting
    private int minPlayers, maxPlayers;
//    The location a player teleports to when...
    private Location joinLocation, leaveLocation, spectateLocation;

    private final List<GameRule> enabledGameRules;
    public Arena(String name){
        this.id = UUID.randomUUID();
        this.setState(ArenaState.INCOMPLETE);
        this.name = name;
        this.enabledGameRules = new ArrayList<>();
    }

//    Deserializes from arena storage file
    public Arena(Map<String, Object> map){
        this.name = (String) map.get("name");
        this.state = ArenaState.valueOf((String) map.get("state"));
        this.floorMaterial = Material.getMaterial((String) map.get("floor-material"));
        this.floorBottomLevel = (int) map.get("floor-level");
        this.minPlayers = (int) map.get("min-players");
        this.maxPlayers = (int) map.get("max-players");
        this.joinLocation = (Location) map.get("join-location");
        this.leaveLocation = (Location) map.get("leave-location");
        this.spectateLocation = (Location) map.get("spectate-location");
        this.enabledGameRules = (List<GameRule>) map.get("enabled-gamerules");
    }

    /**
     * Sets an Arena's ID upon loading from storage.
     * IDs are final, and this method should never be called.
     * @param id
     */
    public void initID(String id){
        if(this.id != null)
            Logger.error("Error: Shouldn't be using this method IDs are final");
        else
            this.id = UUID.fromString(id);
    }

    public List<String> isSetup(){
        List<String> missing = new ArrayList<>();
//        If missing and id or name, something is broken
        if(this.id == null) missing.add("ID");
        if(this.name == null) missing.add("Name");

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
    
    public void setName(String name){
        this.name = name;
    }

    public UUID getID(){
        return this.id;
    }

    public Material getFloorMaterial(){
//        defaults to Snow_Block if no given material
        return this.floorMaterial == null ? Material.SNOW_BLOCK : this.floorMaterial;
    }

    public int getFloorBottomLevel(){
        return this.floorBottomLevel;
    }

    public int getMinPlayers(){
        return this.minPlayers;
    }

    public int getMaxPlayers(){
        return this.maxPlayers;
    }
    public Location getjoinLocation(){
        return this.joinLocation;
    }
    public Location getLeaveLocation(){
        return this.leaveLocation;
    }
    public Location getSpectateLocation(){
        return this.spectateLocation;
    }

    public List<GameRule> getEnabledGameRules(){
        return this.enabledGameRules;
    }

    @Override
    public @NotNull LinkedHashMap<String, Object> serialize() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("name", this.getName());
        map.put("state", this.getState().name());
        map.put("floor-material", this.getFloorMaterial().name());
        map.put("floor-level", this.getFloorBottomLevel());
        map.put("min-players", this.getMinPlayers());
        map.put("max-players", this.getMaxPlayers());
        map.put("join-location", this.getjoinLocation());
        map.put("leave-location", this.getLeaveLocation());
        map.put("spectate-location", this.getSpectateLocation());
        map.put("enabled-gamerules", this.getEnabledGameRules());
        return map;
    }

    public static Arena deserialize(Map<String, Object> map) {
        Arena arena = new Arena(map);
        return arena;
    }
}