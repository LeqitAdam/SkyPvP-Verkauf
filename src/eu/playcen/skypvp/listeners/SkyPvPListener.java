package eu.playcen.skypvp.listeners;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import java.io.File;


public class SkyPvPListener implements Listener {

    public void noFallDamage(EntityDamageEvent event) {
        if(event.getEntity() instanceof Player){
            Player p = (Player) event.getEntity();
            if(event.getEntity().getType() == EntityType.PLAYER && event.getCause() == EntityDamageEvent.DamageCause.FALL){
                File perks = new File("plugins/SkyPvP/Perks", p.getUniqueId() + ".yml");
                YamlConfiguration cfg = YamlConfiguration.loadConfiguration(perks);
                if(cfg.getBoolean("KeinFallschaden") == true) {
                    event.setCancelled(true);
                }else if(cfg.getBoolean("KeinFallschaden") == false)
                    event.setCancelled(false);
            }
        }
    }
}
