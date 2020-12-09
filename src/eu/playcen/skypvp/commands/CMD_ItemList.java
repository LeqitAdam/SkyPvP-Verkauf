package eu.playcen.skypvp.commands;

import eu.playcen.skypvp.main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_ItemList implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(p.hasPermission("skypvp.itemlist") || p.hasPermission("skypvp.*")){
                if(args.length == 0){
                    p.sendMessage(Main.prefix + " §7Eine Liste aller Items findest du unter https://primax4k.github.io/ItemList/");
                    return true;
                } else {
                    p.sendMessage(Main.prefix + " §cBitte benutze: §7/itemlist");
                }
            } else {
                p.sendMessage(Main.prefix + Main.noperm);
            }
        } else
            sender.sendMessage(Main.prefix + " §cNur Spieler könne diesen Befehl nutzen!");
        return false;
    }
}
