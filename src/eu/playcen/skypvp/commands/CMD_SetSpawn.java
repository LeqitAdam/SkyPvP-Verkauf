package eu.playcen.skypvp.commands;

import eu.playcen.skypvp.main.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class CMD_SetSpawn implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player){
            File config = new File("plugins/SkyPvP", "config.yml");
            YamlConfiguration conf = YamlConfiguration.loadConfiguration(config);

            String prefix = conf.getString("Prefix");
            prefix = ChatColor.translateAlternateColorCodes('&', prefix);

            Player p = (Player) sender;
            if(p.hasPermission("skypvp.setspawn")){
                if(args.length == 0){
                    conf.set("Spawn.world", p.getLocation().getWorld().getName());
                    conf.set("Spawn.x", p.getLocation().getX());
                    conf.set("Spawn.y", p.getLocation().getY());
                    conf.set("Spawn.z", p.getLocation().getZ());
                    conf.set("Spawn.yaw", p.getLocation().getYaw());
                    conf.set("Spawn.pitch", p.getLocation().getPitch());

                    try {
                        conf.save(config);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    p.sendMessage(prefix + " §7Der Spawn wurde §agesetzt!");
                } else {
                    p.sendMessage(prefix + " §cBitte benutze: §7/setspawn");
                }
            } else p.sendMessage(prefix + Main.noperm);
        } else {

        }
        return false;
    }
}