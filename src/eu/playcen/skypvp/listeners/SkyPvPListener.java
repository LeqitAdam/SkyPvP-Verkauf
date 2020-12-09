package eu.playcen.skypvp.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;


public class SkyPvPListener implements Listener {

    @EventHandler
    public void noFallDamage(EntityDamageEvent event) {
        if(event.getEntity() instanceof Player) {
            if (event.getEntity().getType() == EntityType.PLAYER && event.getCause() == EntityDamageEvent.DamageCause.FALL) {
                event.setCancelled(true);
            }
        }
    }
}
