package eu.playcen.skypvp.listeners;

import eu.playcen.skypvp.main.Main;
import eu.playcen.skypvp.methods.ImageChar;
import eu.playcen.skypvp.methods.ImageMessage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
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

        File perks = new File("plugins/SkyPvP/Perks", p.getUniqueId() + ".yml");

        if(perks.exists()) {
            Bukkit.getConsoleSender().sendMessage("§c[Skypvp] §7Config ist bereits erstellt");
            return;
        }
        Bukkit.getConsoleSender().sendMessage("§c[Skypvp] §7Config existiert noch nicht");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(perks);

        cfg.set("Fly", false);
        cfg.set("JumpBoost", false);
        cfg.set("Speed", false);
        cfg.set("KeinHunger", false);
        cfg.set("KeinFallschaden", false);

        try {
            cfg.save(perks);
            Bukkit.getConsoleSender().sendMessage("§c[Skypvp] §7Config wurde ohne Fehler erstellt");
        } catch (IOException e) {
            Bukkit.getConsoleSender().sendMessage("§c[Skypvp] §4Config konnte nicht erstellt werden");
            e.printStackTrace();
        }

    }
}
