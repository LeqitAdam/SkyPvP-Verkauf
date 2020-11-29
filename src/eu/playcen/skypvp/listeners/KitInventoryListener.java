package eu.playcen.skypvp.listeners;

import eu.playcen.skypvp.methods.KitInventoryMethod;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.io.File;

public class KitInventoryListener implements Listener {

    @EventHandler
    public void onKitInvClick(InventoryClickEvent event) {

        Player p = (Player) event.getWhoClicked();

        File config = new File("plugins/SkyPvP", "config.yml");
        YamlConfiguration conf = YamlConfiguration.loadConfiguration(config);
        String menutitle = conf.getString("menutitle");
        menutitle = ChatColor.translateAlternateColorCodes('&', menutitle);

        if (event.getClickedInventory() == null) {
            return;
        }
        if(event.getClickedInventory().getTitle().equals(menutitle)) {
            event.setCancelled(true);
            if(event.getCurrentItem().getItemMeta().getDisplayName().equals("§7Spieler §8- §eKit")) {
                p.sendMessage("test");
                KitInventoryMethod.getSpielerKit(p);
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equals("§6Premium §8- §eKit")) {

            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equals("§bUltra §8- §eKit")) {

            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equals("§4God §8- §eKit")) {

            }
        }
    }
}
