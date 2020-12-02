package eu.playcen.skypvp.methods;

import eu.playcen.skypvp.listeners.InventoryClickListener;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;

public class PerksInvsMethod {

    public static void openInv(Player p) {
        File perks = new File("plugins/SkyPvP/Perks", p.getUniqueId() + ".yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(perks);
        if(cfg.getBoolean("Fly") == true) {
        }else if(cfg.getBoolean("Fly") == false) {

        }
    }

    public static void InvFlyOn(Player p) {
        Inventory inventory = Bukkit.createInventory(p, 9 * 6, "§9Sky§ePerks");

        ItemStack fly = new ItemStack(Material.FEATHER, 1);
        ItemMeta perk1 = fly.getItemMeta();
        perk1.setDisplayName("§9Fly §8- §ePerk");
        fly.setItemMeta(perk1);
        ItemStack flyon = new ItemStack(Material.INK_SACK, 1, (short) 10);
        ItemMeta perk1on = flyon.getItemMeta();
        perk1on.setDisplayName("§aAktiviert");
        flyon.setItemMeta(perk1on);

        ItemStack speed = new ItemStack(Material.POTION, 1, (short) 8194);
        ItemMeta perk2 = speed.getItemMeta();
        perk2.setDisplayName("§9Speed §8- §ePerk");
        speed.setItemMeta(perk2);

        ItemStack speed2 = new ItemStack(Material.POTION, 1, (short) 8226);
        ItemMeta perk3 = speed2.getItemMeta();
        perk3.setDisplayName("§9Speed II §8- §ePerk");
        speed2.setItemMeta(perk3);

        ItemStack jumpb = new ItemStack(Material.RABBIT_FOOT, 1);
        ItemMeta perk4 = jumpb.getItemMeta();
        perk4.setDisplayName("§9JumpBoost §8- §ePerk");
        jumpb.setItemMeta(perk4);


        ItemStack glas = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
        ItemMeta glas1 = glas.getItemMeta();
        glas1.setDisplayName(" ");
        glas.setItemMeta(glas1);

        inventory.setItem(1, new ItemStack(fly));
        inventory.setItem(2, new ItemStack(flyon));
        inventory.setItem(10, new ItemStack(jumpb));
        inventory.setItem(19, new ItemStack(speed));
        inventory.setItem(28, new ItemStack(speed2));


        inventory.setItem(4, new ItemStack(glas));
        inventory.setItem(13, new ItemStack(glas));
        inventory.setItem(22, new ItemStack(glas));
        inventory.setItem(31, new ItemStack(glas));
        inventory.setItem(40, new ItemStack(glas));
        inventory.setItem(49, new ItemStack(glas));


        InventoryClickListener.getNoClick().add(p.getUniqueId());
        p.openInventory(inventory);
    }

    public static void InvFlyOff(Player p) {
        Inventory inventory = Bukkit.createInventory(p, 9 * 6, "§9Sky§ePerks");

        ItemStack fly = new ItemStack(Material.FEATHER, 1);
        ItemMeta perk1 = fly.getItemMeta();
        perk1.setDisplayName("§9Fly §8- §ePerk");
        fly.setItemMeta(perk1);
        ItemStack flyoff = new ItemStack(Material.INK_SACK, 1, (short) 1);
        ItemMeta perk1off = fly.getItemMeta();
        perk1off.setDisplayName("§cDeaktiviert");
        flyoff.setItemMeta(perk1off);

        ItemStack speed = new ItemStack(Material.POTION, 1, (short) 8194);
        ItemMeta perk2 = speed.getItemMeta();
        perk2.setDisplayName("§9Speed §8- §ePerk");
        speed.setItemMeta(perk2);

        ItemStack speed2 = new ItemStack(Material.POTION, 1, (short) 8226);
        ItemMeta perk3 = speed2.getItemMeta();
        perk3.setDisplayName("§9Speed II §8- §ePerk");
        speed2.setItemMeta(perk3);

        ItemStack jumpb = new ItemStack(Material.RABBIT_FOOT, 1);
        ItemMeta perk4 = jumpb.getItemMeta();
        perk4.setDisplayName("§9JumpBoost §8- §ePerk");
        jumpb.setItemMeta(perk4);


        ItemStack glas = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
        ItemMeta glas1 = glas.getItemMeta();
        glas1.setDisplayName(" ");
        glas.setItemMeta(glas1);

        inventory.setItem(1, new ItemStack(fly));
        inventory.setItem(2, new ItemStack(flyoff));
        inventory.setItem(10, new ItemStack(jumpb));
        inventory.setItem(19, new ItemStack(speed));
        inventory.setItem(28, new ItemStack(speed2));


        inventory.setItem(4, new ItemStack(glas));
        inventory.setItem(13, new ItemStack(glas));
        inventory.setItem(22, new ItemStack(glas));
        inventory.setItem(31, new ItemStack(glas));
        inventory.setItem(40, new ItemStack(glas));
        inventory.setItem(49, new ItemStack(glas));


        InventoryClickListener.getNoClick().add(p.getUniqueId());
        p.openInventory(inventory);
    }
}
