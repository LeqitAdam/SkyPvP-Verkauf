package eu.playcen.skypvp.commands;

import eu.playcen.skypvp.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_Heal implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(args.length == 0){
                if(p.hasPermission("skypvp.heal")){
                    p.setHealth(p.getMaxHealth());
                    p.sendMessage(Main.pre + "§aDu wurdest geheilt!");
                    return true;
                }
            }
            if(args.length == 1){
                if(p.hasPermission("skypvp.heal.other")){
                    if(Bukkit.getServer().getPlayer(args[0]) != null){
                        Player target = Bukkit.getServer().getPlayer(args[0]);
                        target.setHealth(target.getMaxHealth());
                        target.sendMessage(Main.pre + "§aDu wurdest von §e" + p.getName() + " §ageheilt!");
                        p.sendMessage(Main.pre + "§aDu hast §e" + target.getName() + " §ageheilt!");
                        return true;
                    }
                }
            }
            if(args.length > 1){
                p.sendMessage(Main.pre + "§cBitte benutze: §7/heal [Spieler]");
            }
        }
        return false;
    }
}
