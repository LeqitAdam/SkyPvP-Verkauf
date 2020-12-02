package eu.playcen.skypvp.listeners;

import com.google.common.collect.Lists;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.List;
import java.util.UUID;

public class InventoryClickListener implements Listener {

    public static List<UUID> getNoClick() {
        return noClick;
    }

    public static List<UUID> noClick = Lists.newArrayList();

    @EventHandler
    public void onclick(final InventoryClickEvent e) {

        if(e.getCurrentItem() != null) {
            if (getNoClick().contains(e.getWhoClicked().getUniqueId())) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onClose(final InventoryCloseEvent e) {

        if (getNoClick().contains(e.getPlayer().getUniqueId())) {
            getNoClick().remove(e.getPlayer().getUniqueId());
        }
    }
}
