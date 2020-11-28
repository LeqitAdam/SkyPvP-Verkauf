package eu.playcen.skypvp.commands;

import eu.playcen.skypvp.main.Main;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_Tppos implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player p = (Player) sender;

        if(p.hasPermission("system.tppos") || p.hasPermission("system.*")) {
            if(args.length == 3) {
                double x = Integer.valueOf(args[0]);
                double y = Integer.valueOf(args[1]);
                double z = Integer.valueOf(args[2]);

                Location loc = new Location(p.getWorld(), x, y, z);

                p.teleport(loc);
            } else {
                p.sendMessage(Main.pre + "§cBitte benutze: §7/tppos X Y Z");
            }

        } else {
            p.sendMessage(Main.pre + Main.noperm);
        }

        return false;
    }
}
