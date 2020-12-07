package eu.playcen.skypvp.methods;

import org.bukkit.ChatColor;
import org.bukkit.Material;
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


        File kit = new File("plugins/SkyPvP/Kits", "spieler.yml");
        YamlConfiguration kitconf = YamlConfiguration.loadConfiguration(kit);

        for(String s : kitconf.getConfigurationSection("kit").getKeys(false)){
            ItemStack itemStack = new ItemStack(Material.getMaterial(kitconf.getString("kit." + s + ".type")));
            itemStack.setAmount(kitconf.getInt("kit." + s + ".amount"));
            itemStack.setDurability((short) kitconf.getInt("kit." + s + ".meta"));
            p.getInventory().setItem(Integer.parseInt(s), itemStack);
        }


        p.sendMessage(prefix + " §7Du hast das §7Spieler §8- §eKit §aerhalten!");

    }

}
