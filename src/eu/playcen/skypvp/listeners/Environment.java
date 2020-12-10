package eu.playcen.skypvp.listeners;

import eu.playcen.skypvp.commands.CMD_Build;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
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
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK)
            if(!CMD_Build.builders.contains(e.getPlayer()))
                e.setCancelled(true);

    }
}
