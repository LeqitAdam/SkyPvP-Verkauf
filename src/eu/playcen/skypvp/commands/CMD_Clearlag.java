package eu.playcen.skypvp.commands;

import eu.playcen.skypvp.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;

import java.io.File;

public class CMD_Clearlag implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        File config = new File("plugins/SkyPvP", "config.yml");
        YamlConfiguration conf = YamlConfiguration.loadConfiguration(config);

        String prefix = conf.getString("Prefix");
        prefix = ChatColor.translateAlternateColorCodes('&', prefix);

        if(sender instanceof Player){
            Player p = (Player) sender;
            if(p.hasPermission("skypvp.clearlag") || p.hasPermission("skypvp.*")){
                if(args.length == 0){
                    clearlag();
                } else
                    p.sendMessage(prefix + " §cBitte benutze: §7/clearlag");
            } else
                p.sendMessage(prefix + Main.noperm);
        } else
            clearlag();
        return false;
    }

    private void clearlag(){
        int count = 0;
        for(World w : Bukkit.getWorlds()){
            for(Entity e : w.getEntities()){
                if(e.getType() == EntityType.DROPPED_ITEM){
                    count += ((Item) e).getItemStack().getAmount();
                    e.remove();
                }
            }
        }

        File config = new File("plugins/SkyPvP", "config.yml");
        YamlConfiguration conf = YamlConfiguration.loadConfiguration(config);

        String prefix = conf.getString("Prefix");
        prefix = ChatColor.translateAlternateColorCodes('&', prefix);

        for(Player all : Bukkit.getOnlinePlayers()){
            if(count == 1)
                all.sendMessage(prefix + " §7Es wurde §c" + count + " §7Item entfernt");
            else
                all.sendMessage(prefix + " §7Es wurden §c" + count + " §7Items entfernt");
        }
    }
}
