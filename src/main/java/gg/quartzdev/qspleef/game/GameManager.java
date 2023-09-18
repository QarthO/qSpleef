package gg.quartzdev.qspleef.game;

import gg.quartzdev.qspleef.game.arena.Arena;
import gg.quartzdev.qspleef.game.arena.ArenaManager;
import gg.quartzdev.qspleef.game.players.SpleefPlayer;
import gg.quartzdev.qspleef.game.players.SpleefPlayerState;
import gg.quartzdev.qspleef.qSpleef;
import gg.quartzdev.qspleef.util.Language;
import gg.quartzdev.qspleef.util.Util;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class GameManager {
    qSpleef plugin;
    Util util;
    ArenaManager arenaManager;

//    Player UUID -> SpleefPlayer
    ConcurrentHashMap<UUID, SpleefPlayer> spleefPlayers;
//    Arena UUID -> Game
    ConcurrentHashMap<UUID, Game> games;

    ConcurrentHashMap<UUID, Arena> arenas;

    public GameManager(qSpleef plugin){
        this.plugin = plugin;
//        this.util = plugin.getUtil();
        spleefPlayers = new ConcurrentHashMap<>();
        games = new ConcurrentHashMap<>();
        this.arenaManager = plugin.getArenaManager();
    }

    /** Gets all the current games
     *
     * @return ConcurrentHashMap<UUID, Game>
     */
    public Collection<Game> getGames(){
        return this.games.values();
    }

    public void updateAllArenas(ConcurrentHashMap<UUID, Arena> arenas) {
        this.arenas = arenas;
    }

    public ConcurrentHashMap<UUID, SpleefPlayer> getSpleefPlayers(){
        return this.spleefPlayers;
    }

    public Collection<Arena> getArenas(){
        return this.arenas.values();
    }

    public Game getGame(Arena arena){
//        tries to get from games map
        Game game = this.games.get(arena.getID());
//        if game doesn't exist with that arena
        if(game == null)
//            create a new game
            game = new Game(arena);
//        return the game
        return game;
    }

    public boolean isPlaying(Player player){
        return isPlaying(player.getUniqueId());
    }

    public boolean isPlaying(UUID id){
        return spleefPlayers.containsKey(id);
    }

    public SpleefPlayer getSpleefPlayer(Player player){
        return getSpleefPlayer(player.getUniqueId());
    }

    public SpleefPlayer getSpleefPlayer(UUID id){
        return spleefPlayers.get(id);
    }

    public boolean isArenaInUse(Arena arena){
        return true;
    }

    public boolean join(Player player, String arenaName){

//        Gets arena from args
        Arena arena = this.arenaManager.getArena(arenaName);
//        Checks if arena exists
        if(arena == null){
            this.util.sendMessage(player, Language.ERROR_ARENA_NOT_FOUND.setArena(arenaName));
            return false;
        }
//        Checks if player is already in a game
        if(this.isPlaying(player)){
            util.sendMessage(player, Language.ERROR_JOIN_GAME);
            return false;
        }
//        Creates SpleefPlayer
        SpleefPlayer spleefPlayer = new SpleefPlayer(player, SpleefPlayerState.JOINING);
//        Adds to spleef players (map to validate if a player is in a game or not without looping thru every game)
        spleefPlayers.put(player.getUniqueId(), spleefPlayer);

//        Gets game from arena
        Game game = this.getGame(arena);
        game.addPlayer(player, SpleefPlayerState.JOINING);
        return true;
    }

    public String listAvailableArenas(){
        Set<String> arenaNames = this.arenaManager.getList();
        return String.join(", ", arenaNames);
    }
}
