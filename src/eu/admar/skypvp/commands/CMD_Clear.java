package eu.admar.skypvp.commands;

import eu.admar.skypvp.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_Clear implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(p.hasPermission("skypvp.clear") || p.hasPermission("skypvp.*")) {
                if(cmd.getName().equalsIgnoreCase("clear")) {
                    if(args.length == 0) {
                        p.getInventory().clear();
                        p.sendMessage(Main.prefix + " §7Dein §eInventar §7wurde §cgeleert!");
                    }else if(args.length == 1) {
                        Player target = Bukkit.getPlayer(args[0]);
                        if(target != null) {
                            target.getInventory().clear();
                            p.sendMessage(Main.prefix + " §7Das §eInventar §7von §a" + target.getName() + " §7wurde §cgeleert!");
                        }else
                            p.sendMessage(Main.prefix + " §7Der Spieler §a" + args[0] + " §7ist §cnicht online!");
                    }else
                        p.sendMessage(Main.prefix + " §cBitte benutze: §7/clear");
                }else
                    p.sendMessage(Main.prefix + " §cBitte benutze: §7/clear");
            }else {
                p.sendMessage(Main.prefix + Main.noperm);
            }
        }else
            sender.sendMessage(Main.prefix + " §cNur Spieler können diesen Befehl benutzen!");
        return false;
    }
}
