package eu.admar.skypvp.commands;

import eu.admar.skypvp.listeners.InventoryClickListener;
import eu.admar.skypvp.main.Main;
import org.bukkit.Bukkit;
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

public class CMD_Msgtoggle implements CommandExecutor {
    private String use = " §cBitte benutze: §7/msgtoggle";

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(p.hasPermission("skypvp.msg.toggle") || p.hasPermission("skypvp.*")) {
                if(cmd.getName().equalsIgnoreCase("msgtoggle")) {
                    if(args.length == 0) {
                        openMSGToggleInv(p);
                    }else
                        p.sendMessage(Main.prefix + use);
                }else
                    p.sendMessage(Main.prefix + use);
            }else
                p.sendMessage(Main.prefix + Main.noperm);
        }else
            sender.sendMessage(Main.prefix + " §cNur Spieler können diesen Befehl nutzen!");
        return false;
    }

    public static void openMSGToggleInv(Player p) {
        Inventory inventory = Bukkit.createInventory(p, 9 * 3, "§9SkyMSG");

        ItemStack msg = new ItemStack(Material.BOOK_AND_QUILL, 1);
        ItemMeta msg1 = msg.getItemMeta();
        msg1.setDisplayName("§6MSG §8- §eNachrichten");
        msg.setItemMeta(msg1);

        File player = new File("plugins/SkyPvP/Spieler", p.getUniqueId() + ".yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(player);

        if(cfg.getBoolean("MSG") == true) {
            ItemStack imsg = new ItemStack(Material.INK_SACK, 1, (short) 10);
            ItemMeta msgon = imsg.getItemMeta();
            msgon.setDisplayName("§aAktiviert");
            imsg.setItemMeta(msgon);
            inventory.setItem(15, new ItemStack(imsg));
            }else if(cfg.getBoolean("MSG") == false){
            ItemStack imsg = new ItemStack(Material.INK_SACK, 1, (short) 1);
            ItemMeta msgoff = imsg.getItemMeta();
            msgoff.setDisplayName("§cDeaktiviert");
            imsg.setItemMeta(msgoff);
            inventory.setItem(15, new ItemStack(imsg));
            }

        inventory.setItem(11, new ItemStack(msg));

        InventoryClickListener.getNoClick().add(p.getUniqueId());
        p.openInventory(inventory);
    }
}
