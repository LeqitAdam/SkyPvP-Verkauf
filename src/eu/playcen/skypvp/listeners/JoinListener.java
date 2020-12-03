package eu.playcen.skypvp.listeners;

import eu.playcen.skypvp.main.Main;
import eu.playcen.skypvp.methods.ImageChar;
import eu.playcen.skypvp.methods.ImageMessage;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){

        File config = new File("plugins/SkyPvP", "config.yml");
        YamlConfiguration conf = YamlConfiguration.loadConfiguration(config);

        String prefix = conf.getString("Prefix");
        prefix = ChatColor.translateAlternateColorCodes('&', prefix);

        Player p = event.getPlayer(); //Den Spieler vom Event Deklariert

        //Join Message
        //event.setJoinMessage(prefix + " §aWillkommen " + event.getPlayer().getName());
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
                    prefix + " §aWillkommen §r" + event.getPlayer().getDisplayName(),
                    "",
                    prefix + " §cVersion §nv" + Main.getPlugin().getDescription().getVersion()
            ).sendToPlayer(p);
        } catch (Exception e) {
            System.out.println("[Player-Head] Kopf konnte nicht geladen werden.");
        }

    }
}
