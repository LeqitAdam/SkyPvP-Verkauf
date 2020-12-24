package eu.playcen.skypvp.listeners;

import eu.playcen.skypvp.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;

public class RespawnListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        final File config = new File("plugins/SkyPvP", "config.yml");
        final YamlConfiguration conf = YamlConfiguration.loadConfiguration(config);
        Player p = e.getEntity().getPlayer();

        Bukkit.getScheduler().runTaskLater(Main.getPlugin(), new Runnable() {
            @Override
            public void run() {
                p.spigot().respawn();
                try{
                    World w = Bukkit.getWorld(conf.getString("Spawn.world"));
                    Location loc = new Location(w, conf.getDouble("Spawn.x"), conf.getDouble("Spawn.y"), conf.getDouble("Spawn.z"));
                    loc.setYaw((float) conf.getDouble("Spawn.yaw"));
                    loc.setPitch((float) conf.getDouble("Spawn.pitch"));

                    p.teleport(loc);
                } catch (Exception ex){
                    p.sendMessage(Main.prefix + " §cDer Spawn wurde noch nicht gesetzt!");
                    p.sendMessage(Main.prefix + " §c/setspawn");
                }
            }
        }, 20);

    }

    @EventHandler
    public void onSpawn(PlayerJoinEvent e){
        final File config = new File("plugins/SkyPvP", "config.yml");
        final YamlConfiguration conf = YamlConfiguration.loadConfiguration(config);

        Player p = e.getPlayer();
        try{
            World w = Bukkit.getWorld(conf.getString("Spawn.world"));
            Location loc = new Location(w, conf.getDouble("Spawn.x"), conf.getDouble("Spawn.y"), conf.getDouble("Spawn.z"));
            loc.setYaw((float) conf.getDouble("Spawn.yaw"));
            loc.setPitch((float) conf.getDouble("Spawn.pitch"));

            p.teleport(loc);
        } catch (Exception ex){
            p.sendMessage(Main.prefix + " §cDer Spawn wurde noch nicht gesetzt!");
            p.sendMessage(Main.prefix + " §c/setspawn");
        }

    }
}
