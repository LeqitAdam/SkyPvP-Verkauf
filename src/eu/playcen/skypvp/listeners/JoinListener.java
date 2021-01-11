package eu.playcen.skypvp.listeners;

import eu.playcen.skypvp.commands.CMD_Build;
import eu.playcen.skypvp.main.Main;
import eu.playcen.skypvp.methods.CombatLog;
import eu.playcen.skypvp.methods.ImageChar;
import eu.playcen.skypvp.methods.ImageMessage;
import eu.playcen.skypvp.methods.Scoreboard;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){

        event.setJoinMessage(null);

        Player p = event.getPlayer(); //Den Spieler vom Event Deklariert
        p.setGameMode(GameMode.SURVIVAL);
        for(Player all : Bukkit.getOnlinePlayers()) {
            Scoreboard.setScoreBoard(all);
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

        File player = new File("plugins/SkyPvP/Spieler", p.getUniqueId() + ".yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(player);

        if(!player.exists()) {
            cfg.set("MSG", false);

            try {
                cfg.save(player);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        File config = new File("plugins/SkyPvP", "config.yml");
        YamlConfiguration conf = YamlConfiguration.loadConfiguration(config);
        if(config.exists()) {
            if(!conf.getBoolean("Build Befehl nutzen")) {
                CMD_Build.builders.add(p);
            }
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event){
        event.setQuitMessage(null);

        Player p = event.getPlayer();
        if(CombatLog.combatlog.containsKey(p.getUniqueId())) {
            p.setHealth(0);
            CombatLog.combatlog.remove(p.getUniqueId());
        }
    }
}
