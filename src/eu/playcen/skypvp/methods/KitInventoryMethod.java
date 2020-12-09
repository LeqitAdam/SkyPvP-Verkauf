package eu.playcen.skypvp.methods;

import eu.playcen.skypvp.main.Main;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class KitInventoryMethod {

    public static void getSpielerKit(Player p) {
        File kit = new File("plugins/SkyPvP/Kits", "spieler.yml");
        YamlConfiguration kitconf = YamlConfiguration.loadConfiguration(kit);

        for(String s : kitconf.getConfigurationSection("kit").getKeys(false)){
            ItemStack itemStack = new ItemStack(Material.getMaterial(kitconf.getString("kit." + s + ".type")));
            itemStack.setAmount(kitconf.getInt("kit." + s + ".amount"));
            itemStack.setDurability((short) kitconf.getInt("kit." + s + ".meta"));
            List ench = Arrays.asList(kitconf.getString("kit." + s + ".enchantments").getBytes());
            itemStack.addEnchantments((Map<Enchantment, Integer>) ench);
            p.getInventory().setItem(Integer.parseInt(s), itemStack);
        }
        p.sendMessage(Main.prefix + " §7Du hast das §7Spieler §8- §eKit §aerhalten!");
    }
}
