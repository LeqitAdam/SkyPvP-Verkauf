package eu.playcen.skypvp.commands;

import com.google.common.collect.Lists;
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
import java.util.List;

public class CMD_Perks implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        File config = new File("plugins/SkyPvP", "config.yml");
        YamlConfiguration conf = YamlConfiguration.loadConfiguration(config);

        String prefix = conf.getString("Prefix");
        prefix = ChatColor.translateAlternateColorCodes('&', prefix);

        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(p.hasPermission("skypvp.perks") || p.hasPermission("skypvp.*")) {
                if(cmd.getName().equalsIgnoreCase("perks")) {
                    if(args.length == 0) {
                        openPerksInv(p);
                    }else
                        p.sendMessage(prefix + "§cBitte benutze: §7/perks");
                }else
                    p.sendMessage(prefix + "§cBitte benutze: §7/perks");
            }else
                p.sendMessage(prefix + Main.noperm);
        }else
            sender.sendMessage(prefix + " §cNur Spieler können diesen Befehl nutzen!");

        return false;
    }


    public static void openPerksInv(Player p) {
        Inventory inventory = Bukkit.createInventory(p, 9 * 6, "§9Sky§ePerks");

        ItemStack fly = new ItemStack(Material.FEATHER, 1);
        ItemMeta perk1 = fly.getItemMeta();
        perk1.setDisplayName("§9Fly §8- §ePerk");
        fly.setItemMeta(perk1);

        ItemStack speed = new ItemStack(Material.POTION, 1, (short) 8194);
        ItemMeta perk2 = speed.getItemMeta();
        perk2.setDisplayName("§9Speed §8- §ePerk");
        speed.setItemMeta(perk2);

        ItemStack keinhunger = new ItemStack(Material.GOLDEN_CARROT, 1);
        ItemMeta perk3 = keinhunger.getItemMeta();
        perk3.setDisplayName("§9Kein Hunger §8- §ePerk");
        keinhunger.setItemMeta(perk3);

        ItemStack jumpb = new ItemStack(Material.RABBIT_FOOT, 1);
        ItemMeta perk4 = jumpb.getItemMeta();
        perk4.setDisplayName("§9JumpBoost §8- §ePerk");
        jumpb.setItemMeta(perk4);

        ItemStack keinfallschaden = new ItemStack(Material.DIAMOND_BOOTS, 1);
        ItemMeta perk5 = keinfallschaden.getItemMeta();
        perk5.setDisplayName("§9Kein Fallschaden §8- §ePerk");
        perk5.addEnchant(Enchantment.PROTECTION_FALL, 2, true);
        keinfallschaden.setItemMeta(perk5);


        ItemStack glas = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
        ItemMeta glas1 = glas.getItemMeta();
        glas1.setDisplayName(" ");
        glas.setItemMeta(glas1);

        File perks = new File("plugins/SkyPvP/Perks", p.getUniqueId() + ".yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(perks);

        if(cfg.getBoolean("Fly") == true) {
            ItemStack flyon = new ItemStack(Material.INK_SACK, 1, (short) 10);
            ItemMeta perk1on = flyon.getItemMeta();
            perk1on.setDisplayName("§aAktiviert");
            flyon.setItemMeta(perk1on);

            final ItemMeta itemMeta = flyon.getItemMeta();
            List<String> lore;
            if (itemMeta.getLore() == null) {
                lore = Lists.newArrayList();
            } else {
                lore = itemMeta.getLore();
            }
            lore.add("§9Flugmodus §aaktiv!");
            itemMeta.setLore(lore);
            flyon.setItemMeta(itemMeta);
            inventory.setItem(2, new ItemStack(flyon));
            }else if(cfg.getBoolean("Fly") == false){
            ItemStack flyoff = new ItemStack(Material.INK_SACK, 1, (short) 1);
            ItemMeta perk1off = flyoff.getItemMeta();

            perk1off.setDisplayName("§cDeaktiviert");
            flyoff.setItemMeta(perk1off);

            final ItemMeta itemMeta = flyoff.getItemMeta();
            List<String> lore;
            if (itemMeta.getLore() == null) {
                lore = Lists.newArrayList();
            } else {
                lore = itemMeta.getLore();
            }
            lore.add("§9Flugmodus §cinaktiv!");
            itemMeta.setLore(lore);
            flyoff.setItemMeta(itemMeta);

            inventory.setItem(2, new ItemStack(flyoff));
            }
        if(cfg.getBoolean("JumpBoost") == true) {
            ItemStack speed1on = new ItemStack(Material.INK_SACK, 1, (short) 10);
            ItemMeta perk2on = speed1on.getItemMeta();
            perk2on.setDisplayName("§aAktiviert");
            speed1on.setItemMeta(perk2on);

            final ItemMeta itemMeta = speed1on.getItemMeta();
            List<String> lore;
            if (itemMeta.getLore() == null) {
                lore = Lists.newArrayList();
            } else {
                lore = itemMeta.getLore();
            }
            lore.add("§9JumpBoost §aaktiv!");
            itemMeta.setLore(lore);
            speed1on.setItemMeta(itemMeta);

            inventory.setItem(11, new ItemStack(speed1on));
        }else if(cfg.getBoolean("JumpBoost") == false){
            ItemStack speed1off = new ItemStack(Material.INK_SACK, 1, (short) 1);
            ItemMeta perk2off = speed1off.getItemMeta();
            perk2off.setDisplayName("§cDeaktiviert");
            speed1off.setItemMeta(perk2off);

            final ItemMeta itemMeta = speed1off.getItemMeta();
            List<String> lore;
            if (itemMeta.getLore() == null) {
                lore = Lists.newArrayList();
            } else {
                lore = itemMeta.getLore();
            }
            lore.add("§9JumpBoost §cinaktiv!");
            itemMeta.setLore(lore);
            speed1off.setItemMeta(itemMeta);

            inventory.setItem(11, new ItemStack(speed1off));
        }
        if(cfg.getBoolean("Speed") == true) {
            ItemStack speed1on = new ItemStack(Material.INK_SACK, 1, (short) 10);
            ItemMeta perk2on = speed1on.getItemMeta();
            perk2on.setDisplayName("§aAktiviert");
            speed1on.setItemMeta(perk2on);

            final ItemMeta itemMeta = speed1on.getItemMeta();
            List<String> lore;
            if (itemMeta.getLore() == null) {
                lore = Lists.newArrayList();
            } else {
                lore = itemMeta.getLore();
            }
            lore.add("§9Speed §aaktiv!");
            itemMeta.setLore(lore);
            speed1on.setItemMeta(itemMeta);

            inventory.setItem(20, new ItemStack(speed1on));
            }else if(cfg.getBoolean("Speed") == false){
            ItemStack speed1off = new ItemStack(Material.INK_SACK, 1, (short) 1);
            ItemMeta perk2off = speed1off.getItemMeta();
            perk2off.setDisplayName("§cDeaktiviert");
            speed1off.setItemMeta(perk2off);

            final ItemMeta itemMeta = speed1off.getItemMeta();
            List<String> lore;
            if (itemMeta.getLore() == null) {
                lore = Lists.newArrayList();
            } else {
                lore = itemMeta.getLore();
            }
            lore.add("§9Speed §cinaktiv!");
            itemMeta.setLore(lore);
            speed1off.setItemMeta(itemMeta);

            inventory.setItem(20, new ItemStack(speed1off));
            }
        if(cfg.getBoolean("KeinHunger") == true) {
            ItemStack speed1on = new ItemStack(Material.INK_SACK, 1, (short) 10);
            ItemMeta perk2on = speed1on.getItemMeta();
            perk2on.setDisplayName("§aAktiviert");
            speed1on.setItemMeta(perk2on);

            final ItemMeta itemMeta = speed1on.getItemMeta();
            List<String> lore;
            if (itemMeta.getLore() == null) {
                lore = Lists.newArrayList();
            } else {
                lore = itemMeta.getLore();
            }
            lore.add("§9Kein Hunger §aaktiv!");
            itemMeta.setLore(lore);
            speed1on.setItemMeta(itemMeta);

            inventory.setItem(29, new ItemStack(speed1on));
        }else if(cfg.getBoolean("KeinHunger") == false){
            ItemStack speed1off = new ItemStack(Material.INK_SACK, 1, (short) 1);
            ItemMeta perk2off = speed1off.getItemMeta();
            perk2off.setDisplayName("§cDeaktiviert");
            speed1off.setItemMeta(perk2off);

            final ItemMeta itemMeta = speed1off.getItemMeta();
            List<String> lore;
            if (itemMeta.getLore() == null) {
                lore = Lists.newArrayList();
            } else {
                lore = itemMeta.getLore();
            }
            lore.add("§9Kein Hunger §cinaktiv!");
            itemMeta.setLore(lore);
            speed1off.setItemMeta(itemMeta);

            inventory.setItem(29, new ItemStack(speed1off));
        }
        if(cfg.getBoolean("KeinFallschaden") == true) {
            ItemStack speed1on = new ItemStack(Material.INK_SACK, 1, (short) 10);
            ItemMeta perk2on = speed1on.getItemMeta();
            perk2on.setDisplayName("§aAktiviert");
            speed1on.setItemMeta(perk2on);

            final ItemMeta itemMeta = speed1on.getItemMeta();
            List<String> lore;
            if (itemMeta.getLore() == null) {
                lore = Lists.newArrayList();
            } else {
                lore = itemMeta.getLore();
            }
            lore.add("§9Kein Fallschaden §aaktiv!");
            itemMeta.setLore(lore);
            speed1on.setItemMeta(itemMeta);

            inventory.setItem(38, new ItemStack(speed1on));
        }else if(cfg.getBoolean("KeinFallschaden") == false){
            ItemStack speed1off = new ItemStack(Material.INK_SACK, 1, (short) 1);
            ItemMeta perk2off = speed1off.getItemMeta();
            perk2off.setDisplayName("§cDeaktiviert");
            speed1off.setItemMeta(perk2off);

            final ItemMeta itemMeta = speed1off.getItemMeta();
            List<String> lore;
            if (itemMeta.getLore() == null) {
                lore = Lists.newArrayList();
            } else {
                lore = itemMeta.getLore();
            }
            lore.add("§9Kein Fallschaden §cinaktiv!");
            itemMeta.setLore(lore);
            speed1off.setItemMeta(itemMeta);

            inventory.setItem(38, new ItemStack(speed1off));
        }

        inventory.setItem(1, new ItemStack(fly));
        inventory.setItem(10, new ItemStack(jumpb));
        inventory.setItem(19, new ItemStack(speed));
        inventory.setItem(28, new ItemStack(keinhunger));
        inventory.setItem(37, new ItemStack(keinfallschaden));

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
