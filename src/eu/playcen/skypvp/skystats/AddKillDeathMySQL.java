package eu.playcen.skypvp.skystats;

import eu.playcen.skypvp.mysql.MySQL;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class AddKillDeathMySQL implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        Player p = e.getEntity().getPlayer();
        if(MySQL.isConnected()){
            SkyStatsMethod.updateDeats(p.getUniqueId(), 1, false, p.getName());
        }
    }

    @EventHandler
    public void onKill(EntityDeathEvent e){
        if(e.getEntity().getKiller() != null){
            Player p = e.getEntity().getKiller();
            p.sendMessage("test");
            SkyStatsMethod.updateKills(p.getUniqueId(), 1, false, p.getName());
        }

    }
}
