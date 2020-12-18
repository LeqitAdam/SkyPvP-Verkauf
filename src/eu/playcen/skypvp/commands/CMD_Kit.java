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
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class CMD_Kit implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(p.hasPermission("skypvp.kit") || p.hasPermission("skypvp.*")) {
                if(cmd.getName().equalsIgnoreCase("kit") || cmd.getName().equalsIgnoreCase("kits")) {
                    if (args.length == 0) {
                        kitinventory(p);
                    }else if(args.length == 2 && args[0].equalsIgnoreCase("setup")) {
                        if(args[1].equalsIgnoreCase("spieler") || args[1].equalsIgnoreCase("premium") ||
                                args[1].equalsIgnoreCase("Ultra") || args[1].equalsIgnoreCase("god")){
                            File kit = new File("plugins/SkyPvP/Kits", args[1].toLowerCase() + ".yml");
                            YamlConfiguration kitconf = YamlConfiguration.loadConfiguration(kit);

                            kitconf.set("kit", null);
                            for(int i = 0; i < p.getInventory().getSize(); i++){
                                ItemStack itemStack = p.getInventory().getItem(i);
                                if(itemStack != null){
                                    kitconf.set("kit." + i + ".type", itemStack.getType().toString());
                                    kitconf.set("kit." + i + ".amount", itemStack.getAmount());
                                    kitconf.set("kit." + i + ".displayname", itemStack.getItemMeta().getDisplayName());
                                    kitconf.set("kit." + i + ".meta", itemStack.getDurability());
                                    String[] ench = new String[itemStack.getEnchantments().size()];
                                    int f = 0;
                                    if(itemStack.getEnchantments() !=null){
                                        for(Map.Entry<Enchantment, Integer> en : itemStack.getEnchantments().entrySet()){
                                            int level = en.getValue();
                                            Enchantment enc = en.getKey();
                                            ench[f] = enc.getName() + ":" + level;
                                            f++;
                                        }
                                    }
                                    kitconf.set("kit." + i + ".enchantments", ench);
                                }

                            }
                            try {
                                kitconf.save(kit);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            p.getInventory().clear();
                            p.sendMessage(Main.prefix + " §7Das §eKit §7wurde §agespeichert!");
                        } else
                            p.sendMessage(Main.prefix + " §cBitte benutze: §7/kit setup [Spieler, Premium, Ultra, God]");

                    }else
                        p.sendMessage(Main.prefix + " §cBitte benutze: §7/kit setup [Spieler, Premium, Ultra, God]");
                }else
                    p.sendMessage(Main.prefix + " §cBitte benutze: §7/kit");
            }else
                p.sendMessage(Main.prefix + Main.noperm);
        }else
            sender.sendMessage(Main.prefix + " §cNur Spieler können diesen Befehl nutzen!");
        return false;
    }

    public static void kitinventory(Player p) {
        File config = new File("plugins/SkyPvP", "config.yml");
        YamlConfiguration kits = YamlConfiguration.loadConfiguration(config);
        String menutitle = kits.getString("menutitle");
        menutitle = ChatColor.translateAlternateColorCodes('&', menutitle);

        Inventory inventory = Bukkit.createInventory(p, 9 * 6, menutitle);

        ItemStack spielerkit = new ItemStack(Material.WOOD_SWORD, 1);
        ItemMeta kit1 = spielerkit.getItemMeta();
        kit1.setDisplayName("§7Spieler §8- §eKit");
        spielerkit.setItemMeta(kit1);

        ItemStack premiumrkit = new ItemStack(Material.STONE_SWORD, 1);
        ItemMeta kit2 = premiumrkit.getItemMeta();
        kit2.setDisplayName("§6Premium §8- §eKit");
        premiumrkit.setItemMeta(kit2);

        ItemStack ultrakit = new ItemStack(Material.IRON_SWORD, 1);
        ItemMeta kit3 = ultrakit.getItemMeta();
        kit3.setDisplayName("§bUltra §8- §eKit");
        ultrakit.setItemMeta(kit3);

        ItemStack godkit = new ItemStack(Material.DIAMOND_SWORD, 1);
        ItemMeta kit4 = godkit.getItemMeta();
        kit4.setDisplayName("§4God §8- §eKit");
        godkit.setItemMeta(kit4);

        inventory.setItem(11, new ItemStack(spielerkit));
        inventory.setItem(15, new ItemStack(premiumrkit));
        inventory.setItem(38, new ItemStack(ultrakit));
        inventory.setItem(42, new ItemStack(godkit));


        InventoryClickListener.getNoClick().add(p.getUniqueId());
        p.openInventory(inventory);

    }
}
