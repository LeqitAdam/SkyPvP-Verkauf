package eu.playcen.skypvp.commands;

import eu.playcen.skypvp.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import java.io.File;

public class CMD_Anvil implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        File config = new File("plugins/SkyPvP", "config.yml");
        YamlConfiguration conf = YamlConfiguration.loadConfiguration(config);
        String prefix = conf.getString("Prefix");
        prefix = ChatColor.translateAlternateColorCodes('&', prefix);

        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(p.hasPermission("skypvp.anvil") || p.hasPermission("skypvp.*")) {
                if(cmd.getName().equalsIgnoreCase("anvil") || cmd.getName().equalsIgnoreCase("amboss")) {
                    if(args.length == 0) {
                        Inventory inv = Bukkit.createInventory(null, InventoryType.ANVIL);
                        p.openInventory(inv);
                        p.sendMessage(prefix + " §7Du hast ein §eAmboss §ageöffnet!");
                    }else
                        p.sendMessage(prefix + " §cBitte benutze: §7/anvil");
                }else
                    p.sendMessage(prefix + " §cBitte benutze: §7/anvil");
            }else
                p.sendMessage(prefix + Main.noperm);
        }else
            sender.sendMessage(prefix + " §cNur Spieler können diesen Befehl nutzen!");

        return false;
    }
}
