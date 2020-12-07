package eu.playcen.skypvp.commands;

import eu.playcen.skypvp.listeners.InventoryClickListener;
import eu.playcen.skypvp.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class CMD_Invsee implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            File config = new File("plugins/SkyPvP", "config.yml");
            YamlConfiguration conf = YamlConfiguration.loadConfiguration(config);

            String prefix = conf.getString("Prefix");
            prefix = ChatColor.translateAlternateColorCodes('&', prefix);

            if(args.length == 1){
                if(p.hasPermission("skypvp.invsee") || p.hasPermission("skypvp.*")) {
                    if (!(p.hasPermission("skypvp.invsee.edit") || p.hasPermission("skypvp.*"))) {
                        Player target = Bukkit.getServer().getPlayer(args[0]);

                        if (target != null) {
                            p.openInventory(target.getInventory());
                            InventoryClickListener.getNoClick().add(p.getUniqueId());
                            return true;
                        }
                    }

                    if (p.hasPermission("skypvp.invsee.edit") ||p.hasPermission("skypvp.*")) {
                        Player target = Bukkit.getServer().getPlayer(args[0]);

                        if (target != null) {
                            p.openInventory(target.getInventory());
                            return true;
                        }
                    }
                } else
                    p.sendMessage(prefix + Main.noperm);

            } else if(args.length == 0){
                if(p.hasPermission("skypvp.invsee") || p.hasPermission("skypvp.*")){
                    p.openInventory(p.getInventory());
                    return true;
                } else
                    p.sendMessage(prefix + Main.noperm);
            } else
                p.sendMessage(prefix + " §7Bitte benutze /invsee <Spieler>");
        }
        return false;
    }
}
