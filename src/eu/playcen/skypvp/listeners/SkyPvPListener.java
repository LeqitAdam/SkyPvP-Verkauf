package eu.playcen.skypvp.listeners;

import eu.playcen.skypvp.commands.CMD_Build;
import eu.playcen.skypvp.main.Main;
import eu.playcen.skypvp.methods.CombatLog;
import org.bukkit.GameMode;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;

import java.io.File;


public class SkyPvPListener implements Listener {

    @EventHandler
    public void noFallDamage(EntityDamageEvent event) {
        if(event.getEntity() instanceof Player) {
            if (event.getEntity().getType() == EntityType.PLAYER && event.getCause() == EntityDamageEvent.DamageCause.FALL) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPvP(EntityDamageByEntityEvent e) {

        if(e.getEntity() instanceof Player) {
            File file = new File("plugins/SkyPvP", "config.yml");
            YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
            int height = cfg.getInt("PvPHoehe");
            Player p = (Player) e.getEntity();
            if(e.getDamager() instanceof Player) {
                Player target = (Player) e.getDamager();
                if (!(CMD_Build.builders.contains(target) || target.hasPermission("skypvp.*"))) {
                    if (p.getLocation().getBlockY() >= (height)) {
                        e.setCancelled(true);
                        target.sendMessage(Main.prefix + " §7Du kannst hier §cnicht Kämpfen!");
                    }
                } else
                    e.setCancelled(false);

                CombatLog.combatlog.put(e.getEntity().getUniqueId(), 11);
                CombatLog.combatlog.put(e.getDamager().getUniqueId(), 11);
            }
        }

    }

    @EventHandler
    public void onFly(PlayerMoveEvent event) {
        File file = new File("plugins/SkyPvP", "config.yml");
        YamlConfiguration cfg2 = YamlConfiguration.loadConfiguration(file);
        int height = cfg2.getInt("PvPHoehe");

        if(event.getPlayer().getLocation().getBlockY() <= (height)) {
            if(!(event.getPlayer().hasPermission("skypvp.*")
                    || event.getPlayer().hasPermission("skypvp.fly.bypass")
                    || event.getPlayer().getGameMode() == GameMode.CREATIVE)) {
                if(event.getPlayer().isFlying()) {
                    event.getPlayer().setAllowFlight(false);
                }
            }
        }
        if(CombatLog.combatlog.containsKey(event.getPlayer().getUniqueId())) {
            if(!(event.getPlayer().hasPermission("skypvp.combatlog.bypass")
                    || event.getPlayer().hasPermission("skypvp.*"))) {
                if(event.getPlayer().isFlying()) {
                    event.getPlayer().setAllowFlight(false);
                }
            }
        }

    }
}
