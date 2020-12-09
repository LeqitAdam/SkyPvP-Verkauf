package eu.playcen.skypvp.commands;

import eu.playcen.skypvp.main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_Sun implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(sender.hasPermission("skypvp.sun") || sender.hasPermission("skypvp.*")) {
                if(cmd.getName().equalsIgnoreCase("sun")) {
                    if(args.length == 0) {
                        p.getWorld().setThundering(false);
                        p.getWorld().setStorm(false);
                        p.getWorld().setTime(1000);
                        sender.sendMessage(Main.prefix + " §7Du hast das §aWetter §7auf §eSonnig §7gestellt!");
                    }else
                        sender.sendMessage(Main.prefix + " §cBitte benutze: §7/sun");
                }
            }else
                p.sendMessage(Main.prefix + Main.noperm);
        }else
            sender.sendMessage(Main.prefix + " §cNur Spieler können diesen Befehl nutzen!");

        return false;
    }
}
