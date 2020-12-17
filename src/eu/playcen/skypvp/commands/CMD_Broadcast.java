package eu.playcen.skypvp.commands;

import eu.playcen.skypvp.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_Broadcast implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player){
            Player p = (Player) sender;
            if(p.hasPermission("skypvp.broadcast")){
                if(args.length >= 1){
                    String message = "";

                    for(int i = 0; i < args.length; i++)
                        message += args[i];

                    message.trim();

                    message = ChatColor.translateAlternateColorCodes('&', message);

                    BroadcastMessage(p, message);
                } else{
                    p.sendMessage(Main.prefix + " §7Bitte benutze §c/broadcast <Message>");
                    return true;
                }
            } else
                p.sendMessage(Main.prefix + Main.noperm);
        }else
            sender.sendMessage(Main.prefix + " §cNur Spieler können diesen Befehl nutzen!");
        return false;
    }
    private void BroadcastMessage(Player p, String message){
        for(Player all : Bukkit.getOnlinePlayers()){
            all.sendMessage("§7»");
            all.sendMessage("§a");
            all.sendMessage("§8[§eBroadcast§8] §r" + p.getDisplayName() + " §7»§r " + message);
            all.sendMessage("§a");
            all.sendMessage("§7»");
        }
    }
}
