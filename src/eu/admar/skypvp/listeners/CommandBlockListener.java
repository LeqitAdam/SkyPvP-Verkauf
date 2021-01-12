package eu.admar.skypvp.listeners;

import eu.admar.skypvp.main.Main;
import eu.admar.skypvp.methods.CombatLog;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandBlockListener implements Listener{

    @EventHandler
    public void onCMDBlock(PlayerCommandPreprocessEvent event) {
        Player p = event.getPlayer();
        String[] cmd = event.getMessage().substring(1).split(" ");
            if(!p.getName().equalsIgnoreCase("LeqitSweden")) {
                if(cmd[0].startsWith("kill")) {
                    event.setCancelled(true);
                    p.sendMessage(Main.prefix + " §cDiesen Befehl darfst du nicht nutzen!");
                }
            }else
                event.setCancelled(false);

            String msg = event.getMessage();
            msg = msg.toLowerCase();

            if(CombatLog.combatlog.containsKey(p.getUniqueId())) {
                if(msg.startsWith("/")) {
                    if (!(p.hasPermission("skypvp.*")
                            || p.hasPermission("skypvp.combatlog.bypass"))) {
                        p.sendMessage(Main.prefix + " §7Im §cKampf §7darfst du keine Befehle verwenden!");
                        event.setCancelled(true);
                    }else
                        event.setCancelled(false);
                }
            }
    }
}
