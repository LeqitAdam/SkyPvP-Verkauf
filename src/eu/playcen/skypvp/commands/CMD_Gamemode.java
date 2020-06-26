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

        }else sender.sendMessage(Main.pre +  "§cDiesen Befehl dürfen nur Spieler benutzen!");

        return false;
    }
}
