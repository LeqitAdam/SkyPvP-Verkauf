package eu.admar.skypvp.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class ColorSigns implements Listener {

    @EventHandler
    public void onChange(SignChangeEvent e){
        Player p = e.getPlayer();
        if(p.hasPermission("skypvp.coloredsigns") || p.hasPermission("skypvp.*")){
            for(int i = 0; i <= 3; i++){
                String line = e.getLine(i);
                line = ChatColor.translateAlternateColorCodes('&', line);
                e.setLine(i, line);
            }
        }
    }
}
