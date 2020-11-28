package eu.playcen.skypvp.commands;

import eu.playcen.skypvp.listeners.InventoryClickListener;
import eu.playcen.skypvp.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;

public class CMD_Kit implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;

        if(sender instanceof Player) {
            if(p.hasPermission("skypvp.kit")) {
                if(args.length == 0) {

                }else
                    p.sendMessage(Main.pre + "§cBitte benutze: §7/kit");
            }else
                p.sendMessage(Main.pre + Main.noperm);
        }

        return false;
    }

    public static void kitinventory(Player p) {
        File config = new File("plugins/SkyPvP/kits", "kits.yml");
        YamlConfiguration kits = YamlConfiguration.loadConfiguration(config);
        String menutitle = kits.getString("menutitle");
        menutitle = ChatColor.translateAlternateColorCodes('&', menutitle);

        Inventory inventory = Bukkit.createInventory(p, 9 * 6, menutitle);

        ItemStack spielerkit = new ItemStack(Material.WOOD_SWORD, 1);
        ItemMeta kit1 = spielerkit.getItemMeta();
        kit1.setDisplayName("§7Spieler §8- §eKit");
        spielerkit.setItemMeta(kit1);

        ItemStack premiumrkit = new ItemStack(Material.STONE_SWORD, 1);
        ItemMeta kit2 = spielerkit.getItemMeta();
        kit2.setDisplayName("§6Premium §8- §eKit");
        premiumrkit.setItemMeta(kit2);

        ItemStack ultrakit = new ItemStack(Material.IRON_SWORD, 1);
        ItemMeta kit3 = spielerkit.getItemMeta();
        kit3.setDisplayName("§1Ultra §8- §eKit");
        ultrakit.setItemMeta(kit3);

        ItemStack godkit = new ItemStack(Material.DIAMOND_SWORD, 1);
        ItemMeta kit4 = spielerkit.getItemMeta();
        kit4.setDisplayName("§0God §8- §eKit");
        godkit.setItemMeta(kit4);


        InventoryClickListener.getNoClick().add(p.getUniqueId());
        p.openInventory(inventory);

    }
}
