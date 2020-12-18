package eu.playcen.skypvp.listeners;

import eu.playcen.skypvp.main.Main;
import eu.playcen.skypvp.methods.KitInventoryMethod;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
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
            if(event.getCurrentItem().getItemMeta() != null){
                switch (event.getCurrentItem().getItemMeta().getDisplayName()) {
                    case "§7Spieler §8- §eKit":
                        try {
                            KitInventoryMethod.getSpielerKit(p);
                            p.playSound(p.getLocation(), Sound.LEVEL_UP, 1f, 1f);
                        } catch (Exception e) {
                            p.sendMessage(Main.prefix + " §cDas Kit konnte nicht geladen werden");
                        }

                        p.closeInventory();
                        break;
                    case "§6Premium §8- §eKit":
                        try {
                            KitInventoryMethod.getPremiumKit(p);
                            p.playSound(p.getLocation(), Sound.LEVEL_UP, 1f, 1f);
                        } catch (Exception e) {
                            p.sendMessage(Main.prefix + " §cDas Kit konnte nicht geladen werden");
                        }
                        p.closeInventory();
                        break;
                    case "§bUltra §8- §eKit":
                        try {
                            KitInventoryMethod.getUltraKit(p);
                            p.playSound(p.getLocation(), Sound.LEVEL_UP, 1f, 1f);
                        } catch (Exception e) {
                            p.sendMessage(Main.prefix + " §cDas Kit konnte nicht geladen werden");
                        }
                        p.closeInventory();
                        break;
                    case "§4God §8- §eKit":
                        try {
                            KitInventoryMethod.getGodKit(p);
                            p.playSound(p.getLocation(), Sound.LEVEL_UP, 1f, 1f);
                        } catch (Exception e) {
                            p.sendMessage(Main.prefix + " §cDas Kit konnte nicht geladen werden");
                        }
                        p.closeInventory();
                        break;
                }
            }

        }
    }
}
