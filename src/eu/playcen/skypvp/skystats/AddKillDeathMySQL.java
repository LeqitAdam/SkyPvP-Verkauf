package eu.playcen.skypvp.skystats;

import eu.playcen.skypvp.methods.ScoreboardMethod;
import eu.playcen.skypvp.mysql.MySQL;
import org.bukkit.Bukkit;
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
            SkyStatsMethod.updateDeaths(p.getUniqueId(), 1, false, p.getName());

            for(Player all : Bukkit.getOnlinePlayers()) {
                ScoreboardMethod.setScoreBoard(all);
            }
        }
    }

    @EventHandler
    public void onKill(EntityDeathEvent e){
        if(e.getEntity().getKiller() != null){
            Player p = e.getEntity().getKiller();
            p.sendMessage("test");
            SkyStatsMethod.updateKills(p.getUniqueId(), 1, false, p.getName());

            for(Player all : Bukkit.getOnlinePlayers()) {
                ScoreboardMethod.setScoreBoard(all);
            }
        }
    }
}
