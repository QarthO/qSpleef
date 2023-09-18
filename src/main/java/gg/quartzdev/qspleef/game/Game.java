package gg.quartzdev.qspleef.game;

import gg.quartzdev.qspleef.game.arena.Arena;
import gg.quartzdev.qspleef.game.players.SpleefPlayer;
import gg.quartzdev.qspleef.game.players.SpleefPlayerState;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class Game {
    private UUID id;
    private Arena arena;
    private ConcurrentHashMap<UUID, SpleefPlayer> players;
    private GameState state;

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
        SpleefPlayer spleefPlayer = new SpleefPlayer(player, this.arena, state);
        players.put(player.getUniqueId(), spleefPlayer);
    }

    public void removePlayer(Player player){
//        teleport player out

//        reset inventory to what it was before

//        reward player if necessary

//        remove player from the game's playerlist
        players.remove(player.getUniqueId());
    }

    public GameState getState(){
        return this.state;
    }
}
