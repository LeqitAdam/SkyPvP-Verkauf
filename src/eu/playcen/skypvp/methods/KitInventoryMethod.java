package eu.playcen.skypvp.methods;

import eu.playcen.skypvp.main.Main;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;

public class KitInventoryMethod {

    public static void getSpielerKit(Player p) { setInv(p, "Spieler"); }

    public static void getPremiumKit(Player p){
        setInv(p, "Premium");
    }

    public static void getUltraKit(Player p){
        setInv(p, "Ultra");
    }

    public static void getGodKit(Player p) {
        setInv(p, "God");
    }



    private static void setInv(Player p, String type) {
        File kit = new File("plugins/SkyPvP/Kits", type.toLowerCase() + ".yml");
        YamlConfiguration kitconf = YamlConfiguration.loadConfiguration(kit);

        for(String s : kitconf.getConfigurationSection("kit").getKeys(false)){
            ItemStack itemStack = new ItemStack(Material.getMaterial(kitconf.getString("kit." + s + ".type")));
            itemStack.setAmount(kitconf.getInt("kit." + s + ".amount"));
            itemStack.setDurability((short) kitconf.getInt("kit." + s + ".meta"));
            ItemMeta itemMeta = itemStack.getItemMeta();
            for(String ench : kitconf.getStringList("kit." + s + ".enchantments"))
                itemMeta.addEnchant(Enchantment.getByName(ench.split(":")[0]), Integer.parseInt(ench.split(":")[1]), true);
            itemMeta.setDisplayName(kitconf.getString("kit." + s + ".displayname"));
            itemStack.setItemMeta(itemMeta);
            //p.getInventory().setItem(Integer.parseInt(s), itemStack);
            p.getInventory().addItem(itemStack);
        }
        p.sendMessage(Main.prefix + " §7Du hast das §7Spieler §8- §e"+ type +" §aerhalten!");
    }
}
