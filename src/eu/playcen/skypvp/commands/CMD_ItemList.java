package eu.playcen.skypvp.commands;

import eu.playcen.skypvp.main.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class CMD_ItemList implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        File config = new File("plugins/SkyPvP", "config.yml");
        YamlConfiguration conf = YamlConfiguration.loadConfiguration(config);

        String prefix = conf.getString("Prefix");
        prefix = ChatColor.translateAlternateColorCodes('&', prefix);

        if(sender instanceof Player){
            Player p = (Player) sender;



            if(p.hasPermission("skypvp.itemlist")){
                if(args.length == 0){
                    p.sendMessage(prefix + "§7Eine Liste aller Items findest du unter https://primax4k.github.io/ItemList/");
                    return true;
                } else {
                    p.sendMessage(prefix + " §cBitte benutze: §7/itemlist");
                }
            } else {
                p.sendMessage(prefix + Main.noperm);
            }
        } else
            sender.sendMessage(prefix + " §cNur Spieler könne diesen Befehl nutzen!");
        return false;
    }
}
