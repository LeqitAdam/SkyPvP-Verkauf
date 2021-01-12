package eu.admar.skypvp.methods;

import eu.admar.skypvp.commands.CMD_Msgtoggle;
import eu.admar.skypvp.main.Main;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.io.File;
import java.io.IOException;

public class MSGToggle implements Listener {

    public static void MSGToggle(Player p) {
        File player = new File("plugins/SkyPvP/Spieler", p.getUniqueId() + ".yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(player);

        if(cfg.getBoolean("MSG") == false) {
            p.sendMessage(Main.prefix + " §7Du empfängst wieder §eNachrichten!");
            cfg.set("MSG", true);

            try {
                cfg.save(player);
            } catch (IOException e) {
                e.printStackTrace();
            }
            CMD_Msgtoggle.openMSGToggleInv(p);
        }else {
            p.sendMessage(Main.prefix + " §7Du empfängst nun §ckeine §eNachrichten!");
            cfg.set("MSG", false);

            try {
                cfg.save(player);
            } catch (IOException e) {
                e.printStackTrace();
            }
            CMD_Msgtoggle.openMSGToggleInv(p);
        }
    }

    @EventHandler
    public void onMSGInvClick(InventoryClickEvent event) {
        Player p = (Player) event.getWhoClicked();

        if (event.getClickedInventory() == null) {
            return;
        }
        if(event.getClickedInventory().getTitle().equals("§9SkyMSG")) {
            event.setCancelled(true);
            if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aAktiviert") || event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDeaktiviert")) {
                MSGToggle(p);
            }
        }
    }
}
