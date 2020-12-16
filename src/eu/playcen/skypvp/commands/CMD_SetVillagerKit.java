package eu.playcen.skypvp.commands;

import eu.playcen.skypvp.main.Main;
import eu.playcen.skypvp.methods.CreateVillager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_SetVillagerKit implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(p.hasPermission("skypvp.setvillager") || p.hasPermission("skypvp.*")) {
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("kits") || args[0].equalsIgnoreCase("kit")) {
                        CreateVillager cv = new CreateVillager();
                        cv.setVillager(p,"§eKits");
                        p.sendMessage(Main.prefix + " §aKit-Villager §7wurde erstellt.");
                    }
                } else
                    p.sendMessage(Main.prefix + " §cBitte benutze: §7/cvillager <kits>");
            } else
                p.sendMessage(Main.prefix + Main.noperm);
        }else
            sender.sendMessage(Main.prefix + " §cNur Spieler können diesen Befehl nutzen!");
        return false;
    }
}
