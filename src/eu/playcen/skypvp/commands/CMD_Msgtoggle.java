package eu.playcen.skypvp.commands;

import eu.playcen.skypvp.main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_Msgtoggle implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {

        }else
            sender.sendMessage(Main.prefix + " §cNur Spieler können diesen Befehl nutzen!");
        return false;
    }
}
