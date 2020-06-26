package eu.playcen.skypvp.commands;

import eu.playcen.skypvp.main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_Gamemode implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(p.hasPermission("system.gamemode")) {
                if(cmd.getName().equalsIgnoreCase("gamemode") || cmd.getName().equalsIgnoreCase("gm")) {

                }else p.sendMessage(Main.pre + "§cBitte benutze: §7/gm [0, 1, 2, 3]");
            }else p.sendMessage(Main.pre + Main.noperm);
        }else sender.sendMessage(Main.pre +  Main.cmdp);

        return false;
    }
}
