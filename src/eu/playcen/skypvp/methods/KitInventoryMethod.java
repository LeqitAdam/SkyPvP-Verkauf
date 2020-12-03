package eu.playcen.skypvp.methods;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.io.File;

public class KitInventoryMethod {

    public static void getSpielerKit(Player p) {
        File config = new File("plugins/SkyPvP", "config.yml");
        YamlConfiguration conf = YamlConfiguration.loadConfiguration(config);
        String prefix = conf.getString("Prefix");
        prefix = ChatColor.translateAlternateColorCodes('&', prefix);


        File kit = new File("plugins/SkyPvP/Kits", "Spieler.yml");
        YamlConfiguration kitconf = YamlConfiguration.loadConfiguration(config);



        //ItemStack item = kitconf.getItemStack("Items.type");
        Inventory inv = Bukkit.createInventory(null, 9, "TestInventory");
        //inv.addItem((ItemStack)item);
        p.openInventory(inv);




        p.sendMessage(prefix + " §7Du hast das §7Spieler §8- §eKit §aerhalten!");

    }

}
