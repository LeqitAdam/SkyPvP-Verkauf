package eu.playcen.skypvp.commands;

import eu.playcen.skypvp.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_Tppos implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(Main.prefix + " §cNur Spieler können diesen Befehl nutzen!");
            return true;
        }
        Player p = (Player) sender;

        if(p.hasPermission("skypvp.tppos") || p.hasPermission("skypvp.*")) {
            if(args.length == 3) {
                double x = Integer.valueOf(args[0]);
                double y = Integer.valueOf(args[1]);
                double z = Integer.valueOf(args[2]);

                Location loc = new Location(p.getWorld(), x, y, z);

                p.teleport(loc);
            } else if(args.length == 4) {
                Player target = Bukkit.getServer().getPlayer(args[3]);
                if(target != null) {
                    double x = Integer.valueOf(args[0]);
                    double y = Integer.valueOf(args[1]);
                    double z = Integer.valueOf(args[2]);

                    Location loc = new Location(p.getWorld(), x, y, z);

                    target.teleport(loc);
                }else
                    p.sendMessage(Main.prefix + " §cDieser Spieler ist nicht online!");
            }else
                p.sendMessage(Main.prefix + " §cBitte benutze: §7/tppos X Y Z");

        } else {
            p.sendMessage(Main.prefix + Main.noperm);
        }

        return false;
    }
}
