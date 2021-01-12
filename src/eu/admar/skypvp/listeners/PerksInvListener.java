package eu.admar.skypvp.listeners;

import eu.admar.skypvp.main.Main;
import eu.admar.skypvp.methods.Perks;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.io.File;

public class PerksInvListener implements Listener {

    @EventHandler
    public void onClickPerk(InventoryClickEvent event) {
        Player p = (Player) event.getWhoClicked();

        if (event.getClickedInventory() == null) {
            return;
        }
            if(event.getClickedInventory().getTitle().equals("§9Sky§ePerks")) {
                event.setCancelled(true);
                if(event.getCurrentItem().getItemMeta().getLore().contains("§9Flugmodus §aaktiv!") || event.getCurrentItem().getItemMeta().getLore().contains("§9Flugmodus §cinaktiv!")) {
                    File file = new File("plugins/SkyPvP", "config.yml");
                    YamlConfiguration cfg2 = YamlConfiguration.loadConfiguration(file);
                    int height = cfg2.getInt("PvPHoehe");
                    if(p.hasPermission("skypvp.perks.fly")) {
                        if(!p.hasPermission("skypvp.*") || !p.hasPermission("skypvp.perks.bypass") || !p.hasPermission("skypvp.fly.bypass")) {
                            if(p.getLocation().getBlockY() >= (height)) {
                                Perks.flyperk(p);
                            }else
                                p.sendMessage(Main.prefix + " §cDu darfst dieses §ePerk §chier nicht benutzen!");
                            if(p.isFlying()) {
                                p.setAllowFlight(false);
                            }
                        }else
                            Perks.flyperk(p);
                    }else
                        p.sendMessage(Main.prefix + " §7Dieses §ePerk §7hast du §cnicht §afreigeschaltet!");
                }else if(event.getCurrentItem().getItemMeta().getLore().contains("§9JumpBoost §aaktiv!") || event.getCurrentItem().getItemMeta().getLore().contains("§9JumpBoost §cinaktiv!")) {
                    Perks.jumpboostPerk(p);
                }else if(event.getCurrentItem().getItemMeta().getLore().contains("§9Speed §aaktiv!") || event.getCurrentItem().getItemMeta().getLore().contains("§9Speed §cinaktiv!")) {
                    Perks.speedIPerk(p);
                }else if(event.getCurrentItem().getItemMeta().getLore().contains("§9Kein Hunger §aaktiv!") || event.getCurrentItem().getItemMeta().getLore().contains("§9Kein Hunger §cinaktiv!")) {
                    Perks.keinHunger(p);
                }else if(event.getCurrentItem().getItemMeta().getLore().contains("§9Kein Fallschaden §aaktiv!") || event.getCurrentItem().getItemMeta().getLore().contains("§9Kein Fallschaden §cinaktiv!")) {
                    Perks.keinFallschaden(p);
                }else {

                }
            }
    }
}
