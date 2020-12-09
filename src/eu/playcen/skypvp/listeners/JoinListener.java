package eu.playcen.skypvp.listeners;

import eu.playcen.skypvp.main.Main;
import eu.playcen.skypvp.methods.ImageChar;
import eu.playcen.skypvp.methods.ImageMessage;
import eu.playcen.skypvp.methods.ScoreboardMethod;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){

        event.setJoinMessage(null);

        Player p = event.getPlayer(); //Den Spieler vom Event Deklariert
        for(Player all : Bukkit.getOnlinePlayers()) {
            ScoreboardMethod.setScoreBoard(all);
        }
        //Join Message
        //event.setJoinMessage(Main.prefix + " §aWillkommen " + event.getPlayer().getName());
        try{
            BufferedImage imageToSend = ImageIO.read(new URL("https://crafatar.com/avatars/" + p.getUniqueId().toString()));
            new ImageMessage(
                    imageToSend,
                    8,
                    ImageChar.BLOCK.getChar()
            ).appendCenteredText(
                    "",
                    "",
                    "",
                    Main.prefix + " §aWillkommen §r" + event.getPlayer().getDisplayName(),
                    "",
                    Main.prefix + " §cVersion §nv" + Main.getPlugin().getDescription().getVersion()
            ).sendToPlayer(p);
        } catch (Exception e) {
            System.out.println("[Player-Head] Kopf konnte nicht geladen werden.");
        }

    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e){
        e.setQuitMessage(null);
    }
}
