package eu.admar.skypvp.commands;

import eu.admar.skypvp.main.Main;
import eu.admar.skypvp.skystats.StatsMethod;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_StatsReset implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(p.hasPermission("skypvp.statsreset") || p.hasPermission("skypvp.*")) {
                if(cmd.getName().equalsIgnoreCase("statsreset")) {
                    if(args.length == 0) {
                        StatsMethod.statsReset(p);
                        p.sendMessage(Main.prefix + " §7Deine §eStats §7wurden §aerfolgreich §czurückgesetzt!");
                    }else if(args.length == 1) {
                        Player target = Bukkit.getPlayer(args[0]);
                        OfflinePlayer offtarget = Bukkit.getOfflinePlayer(args[0]);
                        if(target != null) {
                            StatsMethod.statsReset(target);
                            p.sendMessage(Main.prefix + " §7Die §eStats §7von §a" + target.getName() + " §7wurden §aerfolgreich §czurückgesetzt!");
                            target.sendMessage(Main.prefix + " §7Deine §eStats §7wurden §czurückgesetzt!");
                        }else if(offtarget != null) {
                            StatsMethod.statsResetOff(offtarget);
                            p.sendMessage(Main.prefix + " §7Die §eStats §7von §a" + offtarget.getName() + " §7wurden §aerfolgreich §czurückgesetzt!");
                        }else
                            p.sendMessage(Main.prefix + " §cDiser Spieler hat nie das Netzwerk betreten!");
                    }else
                        p.sendMessage(Main.prefix + " §cBitte benutze: §7/statsreset <Name>");
                }else
                    p.sendMessage(Main.prefix + " §cBitte benutze: §7/statsreset <Name>");
            }else
                p.sendMessage(Main.prefix + Main.noperm);
        }else
            sender.sendMessage(Main.prefix + " §cNur Spieler können diesen Befehl nutzen!");
        return false;
    }
}
