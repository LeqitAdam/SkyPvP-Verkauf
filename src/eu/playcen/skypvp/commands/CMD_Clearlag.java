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
        int itemcount = 0;
        for(World w : Bukkit.getWorlds()){
            for(Entity e : w.getEntities()){
                if(e.getType() == EntityType.DROPPED_ITEM){
                    itemcount += ((Item) e).getItemStack().getAmount();
                    e.remove();
                }
            }
        }

        int mobcount = 0;

        for(World world : Bukkit.getWorlds()){
            for(Entity entity : world.getEntities()){
                if(entity.getType() != EntityType.PLAYER){
                    if(entity.getCustomName() == null){
                        entity.remove();
                        mobcount++;
                    }
                }
            }
        }

        File config = new File("plugins/SkyPvP", "config.yml");
        YamlConfiguration conf = YamlConfiguration.loadConfiguration(config);

        String prefix = conf.getString("Prefix");
        prefix = ChatColor.translateAlternateColorCodes('&', prefix);

        for(Player all : Bukkit.getOnlinePlayers()){
            if(itemcount == 1 && mobcount == 1){
                all.sendMessage(prefix + " §7Es wurden §c" + itemcount + " §7Item und §c" + mobcount + "§7 Mob entfernt");
            }
            if(itemcount == 1 && mobcount != 1) {
                all.sendMessage(prefix + " §7Es wurden §c" + itemcount + " §7Item und §c" + mobcount + "§7 Mobs entfernt");
            }
            if(itemcount != 1 && mobcount == 1) {
                all.sendMessage(prefix + " §7Es wurden §c" + itemcount + " §7Items und §c" + mobcount + "§7 Mob entfernt");
            }
            if(itemcount != 1 && mobcount != 1) {
                all.sendMessage(prefix + " §7Es wurden §c" + itemcount + " §7Items und §c" + mobcount + "§7 Mobs entfernt");
            }
        }
    }
}
