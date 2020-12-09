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
                if(p.hasPermission("skypvp.heal") || p.hasPermission("skypvp.*")){
                    p.setHealth(p.getMaxHealth());
                    p.setFoodLevel(40);
                    p.sendMessage(Main.prefix + " §aDu wurdest geheilt!");
                    return true;
                }
            }
            if(args.length == 1){
                if(p.hasPermission("skypvp.heal.other") || p.hasPermission("skypvp.*")){
                    if(Bukkit.getServer().getPlayer(args[0]) != null){
                        Player target = Bukkit.getServer().getPlayer(args[0]);
                        target.setHealth(target.getMaxHealth());
                        target.setFoodLevel(40);
                        target.sendMessage(Main.prefix + " §aDu wurdest von §e" + p.getName() + " §ageheilt!");
                        p.sendMessage(Main.prefix + " §aDu hast §e" + target.getName() + " §ageheilt!");
                        return true;
                    } else
                        p.sendMessage(Main.prefix + " §cDieser Spieler ist nicht online");
                }
            }
            if(args.length > 1){
                p.sendMessage(Main.prefix + "§cBitte benutze: §7/heal <Spieler>");
            }
        }
        return false;
    }
}
