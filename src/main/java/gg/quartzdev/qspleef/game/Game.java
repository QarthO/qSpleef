package gg.quartzdev.qspleef.game;

import gg.quartzdev.qspleef.game.arena.Arena;
import gg.quartzdev.qspleef.game.players.SpleefPlayer;
import gg.quartzdev.qspleef.game.players.SpleefPlayerState;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class Game {
    private UUID id;
    private Arena arena;
    private ConcurrentHashMap<UUID, SpleefPlayer> players;

//    private GameState;

    public Game(Arena arena){
        this.id = UUID.randomUUID();
        this.arena = arena;
        players = new ConcurrentHashMap<>();
    }

    public void start() {
//        Update player states
        for(SpleefPlayer spleefPlayer : players.values()){
//            Spectators stay spectating
            if(spleefPlayer.getState() == SpleefPlayerState.SPECTATING) continue;
            spleefPlayer.setState(SpleefPlayerState.PLAYING);
        }
    }

    public void addPlayer(Player player, SpleefPlayerState state){
        SpleefPlayer spleefPlayer = new SpleefPlayer(player, state);
        players.put(player.getUniqueId(), spleefPlayer);
    }

    public void removePlayer(Player player){
        players.remove(player.getUniqueId());
    }
}
