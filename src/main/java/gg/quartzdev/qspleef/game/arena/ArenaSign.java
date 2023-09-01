package gg.quartzdev.qspleef.game.arena;

import gg.quartzdev.qspleef.util.Language;
import gg.quartzdev.qspleef.util.Logger;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.block.sign.Side;
import org.bukkit.block.sign.SignSide;

import javax.sound.sampled.Line;
import java.util.ArrayList;
import java.util.List;

public class ArenaSign {
    private Arena arena;
    private SignType type;
    private Location location;
    private Sign sign;
    private Side side;
    private List<Component> lines;
    public ArenaSign(Arena arena, SignType type, Location location, Side side) {
        this.arena = arena;
        this.type = type;
        this.location = location;
        this.sign = (Sign) location.getBlock().getState();
        this.side = side;
        this.lines = new ArrayList<>();

        switch(type){
            case JOIN:
                lines.add(buildLine(Language.SIGN_JOIN_LINE_1));
                lines.add(buildLine(Language.SIGN_JOIN_LINE_2));
                lines.add(buildLine(Language.SIGN_JOIN_LINE_3));
                lines.add(buildLine(Language.SIGN_JOIN_LINE_4));
                break;
            case LEAVE:
                lines.add(buildLine(Language.SIGN_LEAVE_LINE_1));
                lines.add(buildLine(Language.SIGN_LEAVE_LINE_2));
                lines.add(buildLine(Language.SIGN_LEAVE_LINE_3));
                lines.add(buildLine(Language.SIGN_LEAVE_LINE_4));
                break;
            case SPECTATE:
                lines.add(buildLine(Language.SIGN_SPECTATE_LINE_1));
                lines.add(buildLine(Language.SIGN_SPECTATE_LINE_2));
                lines.add(buildLine(Language.SIGN_SPECTATE_LINE_3));
                lines.add(buildLine(Language.SIGN_SPECTATE_LINE_4));
                break;
        }

        this.updateText();

    }

    public Component buildLine(Language msg){
        MiniMessage mm = MiniMessage.miniMessage();
        return mm.deserialize(msg.getMessage(),
                Placeholder.parsed("chat-prefix", Language.CHAT_PREFIX.getMessage()),
                Placeholder.parsed("arena", this.arena.getName()),
                Placeholder.parsed("info", this.parsePlaceholder(this.arena.getState(), SignPlaceholder.INFO)),
                Placeholder.parsed("status", this.parsePlaceholder(this.arena.getState(), SignPlaceholder.STATUS))
        );
    }

    public String parsePlaceholder(ArenaState state, SignPlaceholder placeholder){
        String parse = " ";
        Logger.log(state.name() + " - " + placeholder.name());
        switch(state){
            case INCOMPLETE:
                if(placeholder == SignPlaceholder.INFO)
                    parse = "😀 <blue>Click for wiki</blue>";
                if(placeholder == SignPlaceholder.STATUS)
                    parse = "<yellow>Incomplete</yellow>";
                break;
            case LOCKED:
                parse = Language.SIGN_PLACEHOLDER_INFO_LOCKED.getMessage();
                break;
            case READY:
                parse = Language.SIGN_PLACEHOLDER_INFO_READY.getMessage();
                break;
            case PLAYING:
                parse = Language.SIGN_PLACEHOLDER_INFO_PLAYING.getMessage();
                break;
            case RESETTING:
                parse = Language.SIGN_PLACEHOLDER_INFO_RESETTING.getMessage();
                break;
            case BROKEN:
                parse = "<dark_red>Broken</dark_red>";
                break;
            default:
                parse = "test";
                break;
        }
        return parse;
    }
    public void updateText(){
       SignSide signSide = this.sign.getSide(this.side);
       signSide.line(0, this.lines.get(0));
       signSide.line(1, this.lines.get(1));
       signSide.line(2, this.lines.get(2));
       signSide.line(3, this.lines.get(3));
       this.sign.update();
    }
}
