package eu.playcen.skypvp.commands;

import eu.playcen.skypvp.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_Fly implements CommandExecutor {
    //test
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(args.length == 0){
                if(p.hasPermission("skypvp.fly")){
                    p.setAllowFlight(!p.getAllowFlight());
                    return true;
                }
            }
            if(args.length == 1) {
                if (p.hasPermission("skypvp.fly.other")){
                    if (Bukkit.getServer().getPlayer(args[0]) != null) {
                        Player target = Bukkit.getServer().getPlayer(args[0]);
                        target.setAllowFlight(!target.getAllowFlight());
                        return true;
                    } else {
                        p.sendMessage(Main.pre + "Dieser Spieler ist nicht online!");
                        return true;
                    }
                }
            }
            else {
                p.sendMessage(Main.pre + "/fly <user>");
            }
        }
        return false;
    }
}
