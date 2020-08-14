package eu.playcen.skypvp.listeners;

import eu.playcen.skypvp.main.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){

        Player p = event.getPlayer(); //Den Spieler vom Event Deklariert

        //Join Message
        event.setJoinMessage(Main.pre + "Willkommen!");

    }
}
