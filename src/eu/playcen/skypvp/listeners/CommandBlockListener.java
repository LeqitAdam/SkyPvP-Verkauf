package eu.playcen.skypvp.listeners;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.io.File;

public class CommandBlockListener implements Listener{

    @EventHandler
    public void onCMDBlock(PlayerCommandPreprocessEvent event) {
        Player p = event.getPlayer();
        String[] cmd = event.getMessage().substring(1).split(" ");

        File config = new File("plugins/SkyPvP", "config.yml");
        YamlConfiguration conf = YamlConfiguration.loadConfiguration(config);

        String prefix = conf.getString("Prefix");
        prefix = ChatColor.translateAlternateColorCodes('&', prefix);


            if(!p.getName().equalsIgnoreCase("LeqitSweden")) {
                if(cmd[0].startsWith("kill")) {
                    event.setCancelled(true);
                    p.sendMessage(prefix + " §cDiesen Befehl darfst du nicht nutzen!");
                }
            }else
                event.setCancelled(false);
    }
}
