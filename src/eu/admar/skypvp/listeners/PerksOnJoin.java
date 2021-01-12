package eu.admar.skypvp.listeners;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.io.File;

public class PerksOnJoin implements Listener {

    @EventHandler
    public void onJoinPerk(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        File perks = new File("plugins/SkyPvP/Perks", p.getUniqueId() + ".yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(perks);

        if(cfg.getBoolean("Fly") == true) {
            event.getPlayer().setAllowFlight(true);
            }
        if(cfg.getBoolean("JumpBoost") == true) {
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 999999, 1));
            }
        if(cfg.getBoolean("Speed") == true) {
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 999999, 1));
            }
    }
}
