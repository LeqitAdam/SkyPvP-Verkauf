package eu.playcen.skypvp.commands;

import eu.playcen.skypvp.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_Fly implements CommandExecutor {
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(args.length == 0){
                if(p.hasPermission("skypvp.fly") || p.hasPermission("skypvp.*")){
                    if (p.getAllowFlight()) {
                        p.setAllowFlight(false);
                        p.sendMessage(Main.prefix + " §7Du hast deinen §eFlugmodus §8[§cdeaktiviert!§8]");
                    } else {
                        p.setAllowFlight(true);
                        p.sendMessage(Main.prefix + " §7Du hast deinen §eFlugmodus §8[§aaktiviert!§8]");
                    }
                }else
                    p.sendMessage(Main.prefix + Main.noperm);
            }else if(args.length == 1) {
                if (p.hasPermission("skypvp.fly.other") || p.hasPermission("skypvp.*")){
                    if (Bukkit.getServer().getPlayer(args[0]) != null) {
                        Player target = Bukkit.getServer().getPlayer(args[0]);
                        if (target.getAllowFlight()) {
                            target.setAllowFlight(false);
                            target.sendMessage(Main.prefix + " §7Der Spieler §a" + p.getName() + " §7hat deinen §eFlugmodus §8[§cdeaktiviert!§8]");
                            p.sendMessage(Main.prefix + " §7Du hast den §eFlugmodus §7von §a" + target.getName() + " §8[§cdeaktiviert!§8]");
                        } else {
                            target.setAllowFlight(true);
                            target.sendMessage(Main.prefix + " §7Der Spieler §a" + p.getName() + " §7hat deinen §eFlugmodus §8[§aaktiviert!§8]");
                            p.sendMessage(Main.prefix + " §7Du hast den §eFlugmodus §7von §a" + target.getName() + " §8[§aaktiviert!§8]");
                        }
                    } else
                        p.sendMessage(Main.prefix + " §cDieser Spieler ist nicht online!");
                }else
                    p.sendMessage(Main.prefix + Main.noperm);
            } else
                p.sendMessage(Main.prefix + " §cBitte benutze: §7/fly <Spieler>");
        }
        return false;
    }
}
