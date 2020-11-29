package eu.playcen.skypvp.listeners;

import eu.playcen.skypvp.main.Main;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){

        File config = new File("plugins/SkyPvP", "config.yml");
        YamlConfiguration conf = YamlConfiguration.loadConfiguration(config);

        String prefix = conf.getString("Prefix");
        prefix = ChatColor.translateAlternateColorCodes('&', prefix);

        Player p = event.getPlayer(); //Den Spieler vom Event Deklariert

        //Join Message
        event.setJoinMessage(prefix + " §aWillkommen!");

    }
}
