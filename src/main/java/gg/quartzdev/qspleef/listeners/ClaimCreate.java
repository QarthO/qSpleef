package gg.quartzdev.qspleef.listeners;

import me.ryanhamshire.GriefPrevention.*;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import me.ryanhamshire.GriefPrevention.events.ClaimCreatedEvent;

import java.util.ArrayList;

public class ClaimCreate implements Listener {

    @EventHandler
    public void onClaimCreate(ClaimCreatedEvent event){
        DataStore gpDataStore = GriefPrevention.instance.dataStore;
        Claim newClaim = event.getClaim();
        CommandSender owner = event.getCreator();

//        Makes sure creator is a player
        if(!(owner  instanceof Player)) return;

        Player player = (Player) owner;
        PlayerData playerData;

//        Makes sure player exists in GP Data
        try {
            playerData = gpDataStore.getPlayerData(player.getUniqueId());
        } catch(Exception e){
            return;
        }

//        Gets the last claim they made before the current one
        Claim lastClaim = playerData.lastClaim;
//        If last claim is null, then no trustees need to be added
        if(lastClaim == null) return;

//        initializes all trustee types lists
        ArrayList<String> builders = new ArrayList<>();
        ArrayList<String> containers = new ArrayList<>();
        ArrayList<String> accessors = new ArrayList<>();
        ArrayList<String> managers = new ArrayList<>();
//        gets all trustees of the last claim
        lastClaim.getPermissions(builders, containers, accessors, managers);

//        total number of trusted players from previous claim
        int totalTrusteeCount = builders.size() + containers.size() + accessors.size() + managers.size();

//        does nothing if no one to trust
        if(totalTrusteeCount == 0) return;
//        adds each type of trustee to the new claim respectively
        for(String builder : builders) {
            newClaim.setPermission(builder, ClaimPermission.Build);
        }
        for(String container : containers) {
            newClaim.setPermission(container, ClaimPermission.Inventory);
        }
        for(String accessor : accessors) {
            newClaim.setPermission(accessor, ClaimPermission.Access);
        }
        for(String manager : managers) {
            newClaim.setPermission(manager, ClaimPermission.Manage);
        }

        MiniMessage mm = MiniMessage.miniMessage();
        String plural = (totalTrusteeCount > 1) ? "s" : "";
        Component parse = mm.deserialize("  <gray>>> <green>Trusted <aqua>" + totalTrusteeCount + " <green>player" + plural + " to your new claim");
        player.sendMessage(parse);
        // Saves Claim to datastore making it effective
        gpDataStore.saveClaim(newClaim);

    }

}
