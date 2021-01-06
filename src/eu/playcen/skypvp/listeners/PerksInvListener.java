package eu.playcen.skypvp.listeners;

import eu.playcen.skypvp.main.Main;
import eu.playcen.skypvp.methods.PerksMethod;
import org.bukkit.GameMode;
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

                    if(!p.hasPermission("skypvp.*") || !p.hasPermission("skypvp.perks.bypass")) {
                        if(p.getLocation().getBlockY() >= (height)) {
                            PerksMethod.flyperk(p);
                        }else
                            p.sendMessage(Main.prefix + " §cDu darfst dieses §ePerk §chier nicht benutzen!");
                            if(p.isFlying()) {
                                p.setAllowFlight(false);
                            }
                    }else if(p.hasPermission("skypvp.perks.fly") || p.hasPermission("skypvp.fly.bypass")) {
                        PerksMethod.flyperk(p);
                    }else
                        p.sendMessage(Main.prefix + " §cDieses §ePerk §cdarfst du nicht benutzen!");
                }else if(event.getCurrentItem().getItemMeta().getLore().contains("§9JumpBoost §aaktiv!") || event.getCurrentItem().getItemMeta().getLore().contains("§9JumpBoost §cinaktiv!")) {
                    PerksMethod.jumpboostPerk(p);
                }else if(event.getCurrentItem().getItemMeta().getLore().contains("§9Speed §aaktiv!") || event.getCurrentItem().getItemMeta().getLore().contains("§9Speed §cinaktiv!")) {
                    PerksMethod.speedIPerk(p);
                }else if(event.getCurrentItem().getItemMeta().getLore().contains("§9Kein Hunger §aaktiv!") || event.getCurrentItem().getItemMeta().getLore().contains("§9Kein Hunger §cinaktiv!")) {
                    PerksMethod.keinHunger(p);
                }else if(event.getCurrentItem().getItemMeta().getLore().contains("§9Kein Fallschaden §aaktiv!") || event.getCurrentItem().getItemMeta().getLore().contains("§9Kein Fallschaden §cinaktiv!")) {
                    PerksMethod.keinFallschaden(p);
                }else {

                }
            }
    }
}
