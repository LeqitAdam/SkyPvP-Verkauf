package eu.admar.skypvp.commands;

import eu.admar.skypvp.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class CMD_Anvil implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(p.hasPermission("skypvp.anvil") || p.hasPermission("skypvp.*")) {
                if(cmd.getName().equalsIgnoreCase("anvil") || cmd.getName().equalsIgnoreCase("amboss")) {
                    if(args.length == 0) {
                        Inventory inv = Bukkit.createInventory(null, InventoryType.ANVIL);
                        p.openInventory(inv);
                        p.sendMessage(Main.prefix + " §7Du hast ein §eAmboss §ageöffnet!");
                    }else
                        p.sendMessage(Main.prefix + " §cBitte benutze: §7/anvil");
                }else
                    p.sendMessage(Main.prefix + " §cBitte benutze: §7/anvil");
            }else
                p.sendMessage(Main.prefix + Main.noperm);
        }else
            sender.sendMessage(Main.prefix + " §cNur Spieler können diesen Befehl nutzen!");

        return false;
    }
}
