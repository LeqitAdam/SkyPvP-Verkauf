package eu.playcen.skypvp.commands;

import eu.playcen.skypvp.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_Globalmute implements CommandExecutor {

    public static boolean globalmute = false;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(p.hasPermission("skypvp.globalmute") || p.hasPermission("skypvp.*")) {
                if(cmd.getName().equalsIgnoreCase("globalmute") || cmd.getName().equalsIgnoreCase("gmute")) {
                    if(args.length == 0) {
                        if (globalmute) {
                            globalmute = false;
                            Bukkit.broadcastMessage(Main.prefix + " §7Der Globalmute wurde von §e" + sender.getName() + " §cdeaktiviert");
                        } else {
                            globalmute = true;
                            Bukkit.broadcastMessage(Main.prefix + " §7Der Globalmute wurde von §e" + sender.getName() + " §aaktiviert");
                        }
                    }else if(args.length == 1) {
                        if(args[0].equalsIgnoreCase("status")) {
                            if(globalmute) {
                                p.sendMessage(Main.prefix + " §7Der §cGlobalmute §7ist derzeit §aaktiviert!");
                            }else {
                                p.sendMessage(Main.prefix + " §7Der §cGlobalmute §7ist derzeit §cdeaktiviert!");
                            }
                        }
                    }else
                        p.sendMessage(Main.prefix + " §cBitte benutze: §7/globalmute");
                }else
                    p.sendMessage(Main.prefix + " §cBitte benutze: §7/globalmute");
            }else
                p.sendMessage(Main.prefix + Main.noperm);
        }else
            sender.sendMessage(Main.prefix + " §cNur Spieler könne diesen Befehl nutzen!");

        return false;
    }
}
