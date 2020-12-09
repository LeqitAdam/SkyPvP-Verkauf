package eu.playcen.skypvp.skystats;

import eu.playcen.skypvp.mysql.MySQL;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class AddDefaultValuesMySQL implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();

        if(MySQL.isConnected()){
            if(!SkyStatsMethod.isUserExists(p.getUniqueId())){
                SkyStatsMethod.updateKills(p.getUniqueId(), 0, false, p.getName());
                SkyStatsMethod.updateDeats(p.getUniqueId(), 0, false, p.getName());
            }
        }
    }

}
