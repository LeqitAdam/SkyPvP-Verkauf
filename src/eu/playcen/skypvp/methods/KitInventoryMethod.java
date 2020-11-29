package eu.playcen.skypvp.methods;

import eu.playcen.skypvp.main.Main;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;

public class KitInventoryMethod {

    public static void getSpielerKit(Player p) {
        File config = new File("plugins/SkyPvP", "config.yml");
        YamlConfiguration conf = YamlConfiguration.loadConfiguration(config);
        String prefix = conf.getString("Prefix");
        prefix = ChatColor.translateAlternateColorCodes('&', prefix);


        File kit = new File("plugins/SkyPvP/Kits", "Spieler.yml");
        YamlConfiguration kitconf = YamlConfiguration.loadConfiguration(config);

        ItemStack item = kitconf.getItemStack("Items");

        p.sendMessage(prefix + " §7Du hast das §7Spieler §8- §eKit §aerhalten!");

    }

}
