package eu.playcen.skypvp.listeners;

import eu.playcen.skypvp.methods.PerksMethod;
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
                    PerksMethod.flyperk(p);
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
