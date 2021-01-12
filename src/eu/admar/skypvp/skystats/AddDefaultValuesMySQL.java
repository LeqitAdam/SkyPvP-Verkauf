package eu.admar.skypvp.skystats;

import eu.admar.skypvp.methods.Scoreboard;
import eu.admar.skypvp.mysql.MySQL;
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
                SkyStatsMethod.updateDeaths(p.getUniqueId(), 0, false, p.getName());
                Scoreboard.setScoreBoard(p);
            }
        }
    }

}
