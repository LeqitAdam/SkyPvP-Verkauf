package eu.playcen.skypvp.listeners;

import eu.playcen.skypvp.commands.CMD_Build;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerAchievementAwardedEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class Environment implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent e){
        if(!CMD_Build.builders.contains(e.getPlayer()))
            e.setCancelled(true);
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e){
        if(!CMD_Build.builders.contains(e.getPlayer()))
            e.setCancelled(true);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        if(!CMD_Build.builders.contains(e.getPlayer()))
            e.setCancelled(true);
    }

    @EventHandler
    public void onAchieve(PlayerAchievementAwardedEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void entityDeathEvent(EntityDeathEvent e){
        if(e.getEntity().getType() != EntityType.PLAYER)
            e.getDrops().clear();
    }
}
