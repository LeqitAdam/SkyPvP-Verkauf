package eu.playcen.skypvp.listeners;

import eu.playcen.skypvp.main.Main;
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
    }
}
