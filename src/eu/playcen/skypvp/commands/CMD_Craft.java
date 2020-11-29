package eu.playcen.skypvp.commands;

import eu.playcen.skypvp.main.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class CMD_Craft implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        File config = new File("plugins/SkyPvP", "config.yml");
        YamlConfiguration conf = YamlConfiguration.loadConfiguration(config);

        String prefix = conf.getString("Prefix");
        prefix = ChatColor.translateAlternateColorCodes('&', prefix);

        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(p.hasPermission("skypvp.craft") || p.hasPermission("skypvp.*")) {
                if(cmd.getName().equalsIgnoreCase("craft")) {
                    if(args.length == 0) {
                        p.openWorkbench(p.getLocation(), true);
                    }else
                        p.sendMessage(prefix + " §cBitte benutze: §7/craft");
                }else
                    p.sendMessage(prefix + " §cBitte benutze: §7/craft");
            }else
                p.sendMessage(prefix + Main.noperm);
        }else
            sender.sendMessage(prefix + " §cNur Spieler können diesen Befehl nutzen!");

        return false;
    }
}
