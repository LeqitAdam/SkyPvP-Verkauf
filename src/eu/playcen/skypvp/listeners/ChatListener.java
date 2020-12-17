package eu.playcen.skypvp.listeners;

import eu.playcen.skypvp.commands.CMD_Build;
import eu.playcen.skypvp.commands.CMD_Globalmute;
import eu.playcen.skypvp.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.help.HelpTopic;

import java.io.File;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        if (CMD_Globalmute.globalmute) {
            if (event.getPlayer().hasPermission("skypvp.globalmute.bypass")  || event.getPlayer().hasPermission("skypvp.*")) {
                return;
            }
            event.getPlayer().sendMessage(Main.prefix + " §7Der §c§lGlobalmute §7ist derzeit §aaktiviert!");
            event.setCancelled(true);
        }
        File config = new File("plugins/SkyPvP", "config.yml");
        YamlConfiguration conf = YamlConfiguration.loadConfiguration(config);
        if(conf.getBoolean("Build Befehl nutzen") == false) {
            event.getPlayer().sendMessage(Main.prefix + " §7Dieser Befehl ist derzeit §cdeaktiviert!");
        }
    }

    @EventHandler
    public void onUnknownCmd(PlayerCommandPreprocessEvent event) {
        if(!event.isCancelled()) {
            Player p = event.getPlayer();
            String cmd = event.getMessage().split(" ")[0];
            HelpTopic topic = Bukkit.getServer().getHelpMap().getHelpTopic(cmd);
            if (topic == null) {
                p.sendMessage(Main.prefix + " §cDen Befehl §e" + cmd + " §cgibt es nicht!");
                event.setCancelled(true);
            }
        }
    }
}
