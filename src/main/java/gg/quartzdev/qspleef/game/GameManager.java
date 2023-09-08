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

    public Game getGame(String arenaName){
        return games.get(arenaName);
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

    public void join(Player player, String arenaName){

        if(this.isPlaying(player)){
            util.sendMessage(player, Language.ERROR_JOIN_GAME);
            return;
        }

        SpleefPlayer spleefPlayer = new SpleefPlayer(player, SpleefPlayerState.JOINING);
        spleefPlayers.put(player.getUniqueId(), spleefPlayer);

        Game game = this.getGame(arenaName);

        if(game == null){
            util.sendMessage(player, Language.ERROR_ARENA_NOT_FOUND);
            return;
        }
    }

    public String listAvailableArenas(){
        Set<String> arenaNames = this.arenaManager.getList();
        return String.join(", ", arenaNames);
    }
}
