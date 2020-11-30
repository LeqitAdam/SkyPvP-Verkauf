package eu.playcen.skypvp.listeners;

import eu.playcen.skypvp.commands.CMD_Globalmute;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.io.File;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        File config = new File("plugins/SkyPvP", "config.yml");
        YamlConfiguration conf = YamlConfiguration.loadConfiguration(config);

        String prefix = conf.getString("Prefix");
        prefix = ChatColor.translateAlternateColorCodes('&', prefix);

        if (CMD_Globalmute.globalmute) {
            if (event.getPlayer().hasPermission("skypvp.globalmute.bypass")) {
                return;
            }
            event.getPlayer().sendMessage(prefix + " §7Der §c§lGlobalmute §7ist derzeit §aaktiviert!");
            event.setCancelled(true);
        }
    }
}
