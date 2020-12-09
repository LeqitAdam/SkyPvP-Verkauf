package eu.playcen.skypvp.commands;

import eu.playcen.skypvp.main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class CMD_Discord implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player){
            File file = new File("plugins/SkyPvP", "config.yml");
            YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
            
            Player p = (Player) sender;
            p.sendMessage(Main.prefix + " §7Discord: §e" + cfg.get("Discord"));
        }
        return false;
    }
}
