package eu.playcen.skypvp.commands;

import eu.playcen.skypvp.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class CMD_Clear implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        File config = new File("plugins/SkyPvP", "config.yml");
        YamlConfiguration conf = YamlConfiguration.loadConfiguration(config);

        String prefix = conf.getString("Prefix");
        prefix = ChatColor.translateAlternateColorCodes('&', prefix);
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(p.hasPermission("skypvp.clear") || p.hasPermission("skypvp.*")) {
                if(cmd.getName().equalsIgnoreCase("clear")) {
                    if(args.length == 0) {
                        p.getInventory().clear();
                        p.sendMessage(prefix + " §7Dein §eInventar §7wurde §cgeleert!");
                    }else if(args.length == 1) {
                        Player target = Bukkit.getPlayer(args[0]);
                        if(target != null) {
                            target.getInventory().clear();
                            p.sendMessage(prefix + " §7Das §eInventar §7von §a" + target.getName() + " §7wurde §cgeleert!");
                        }else
                            p.sendMessage(prefix + " §7Der Spieler §a" + args[0] + " §7ist §cnicht online!");
                    }else
                        p.sendMessage(prefix + " §cBitte benutze: §7/clear");
                }else
                    p.sendMessage(prefix + " §cBitte benutze: §7/clear");
            }else {
                p.sendMessage(prefix + Main.noperm);
            }
        }else
            sender.sendMessage(prefix + " §cNur Spieler können diesen Befehl benutzen!");


        return false;
    }
}
