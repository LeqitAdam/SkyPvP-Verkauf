package eu.admar.skypvp.skystats;

import eu.admar.skypvp.main.Main;
import eu.playcen.coins.api.CoinsAPI;
import eu.admar.skypvp.methods.CombatLog;
import eu.admar.skypvp.methods.Scoreboard;
import eu.admar.skypvp.mysql.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class AddKillDeathMySQL implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event){
        Player p = event.getEntity().getPlayer();
        Player killer = event.getEntity().getPlayer().getKiller();
        if(MySQL.isConnected()){
            if(CoinsAPI.getBalance(p.getUniqueId()) >=5) {
                SkyStatsMethod.updateDeaths(p.getUniqueId(), 1, false, p.getName());
                CoinsAPI.addCoins(p.getUniqueId(), -5);
                for(Player all : Bukkit.getOnlinePlayers()) {
                    Scoreboard.setScoreBoard(all);
                }
            }else if(CoinsAPI.getBalance(p.getUniqueId()) <5) {
                SkyStatsMethod.updateDeaths(p.getUniqueId(), 1, false, p.getName());
                CoinsAPI.setBalance(p.getUniqueId(), 0);
                for(Player all : Bukkit.getOnlinePlayers()) {
                    Scoreboard.setScoreBoard(all);
                }
            }
        }
        p.playSound(p.getLocation(), Sound.CAT_HISS, 1, 1);
        if(killer != null) {
            event.setDeathMessage(Main.prefix + " §7Der Spieler §a" + p.getDisplayName() + " §7wurde von §a" + killer.getDisplayName() + " §cgetötet!");
            if(CombatLog.combatlog.containsKey(p.getUniqueId())) {
                CombatLog.combatlog.remove(p.getUniqueId());
            }
        }else
            event.setDeathMessage(Main.prefix + " §7Der Spieler §a" + p.getDisplayName() + " §7ist §cgestorben!");
            if(CombatLog.combatlog.containsKey(p.getUniqueId())) {
                CombatLog.combatlog.remove(p.getUniqueId());
            }
    }

    @EventHandler
    public void onKill(EntityDeathEvent event){
        if(event.getEntity().getKiller() != null && event.getEntity() instanceof Player){
            Player p = event.getEntity().getKiller();
            SkyStatsMethod.updateKills(p.getUniqueId(), 1, false, p.getName());
            CoinsAPI.addCoins(p.getUniqueId(), 10);
            for(Player all : Bukkit.getOnlinePlayers()) {
                Scoreboard.setScoreBoard(all);
            }
            p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 1);
        }
    }
}
