package eu.playcen.skypvp.commands;

import eu.playcen.skypvp.main.Main;
import eu.playcen.skypvp.methods.CreateVillager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class CMD_SetVillagerKit implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player){
            File config = new File("plugins/SkyPvP", "config.yml");
            YamlConfiguration conf = YamlConfiguration.loadConfiguration(config);

            String prefix = conf.getString("Prefix");
            prefix = ChatColor.translateAlternateColorCodes('&', prefix);

            Player p = (Player) sender;
            if(p.hasPermission("skypvp.setvillager")) {
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("kits") || args[0].equalsIgnoreCase("kit")) {
                        CreateVillager cv = new CreateVillager();
                        cv.setVillager(p,"§eKits");
                        p.sendMessage(prefix + " §aKit-Villager §7wurde erstellt.");
                    }
                } else
                    p.sendMessage(prefix + " §7Bitte benutze /cvillager [kits]");
            } else
                p.sendMessage(prefix + Main.noperm);
        }
        return false;
    }
}
