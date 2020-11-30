package eu.playcen.skypvp.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class CMD_Teamspeak implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player){
            File file = new File("plugins/SkyPvP", "config.yml");
            YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

            String prefix = cfg.getString("Prefix");
            prefix = ChatColor.translateAlternateColorCodes('&', prefix);
            Player p = (Player) sender;
            p.sendMessage(prefix + " §7Teamspeak: §e" + cfg.get("Teamspeak"));
        }
        return false;
    }
}
