package eu.playcen.skypvp.commands;

import eu.playcen.skypvp.main.Main;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.io.File;

public class CMD_Ping implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            File config = new File("plugins/SkyPvP", "config.yml");
            YamlConfiguration conf = YamlConfiguration.loadConfiguration(config);

            String prefix = conf.getString("Prefix");
            prefix = ChatColor.translateAlternateColorCodes('&', prefix);

            if(p.hasPermission("skypvp.ping") || p.hasPermission("skypvp.*")){
                if(args.length == 0){
                    p.sendMessage(prefix + " §7Dein Ping beträgt §a" + getPing(p) + " §7ms");
                    return true;
                }
                if(args.length == 1){
                    Player target = Bukkit.getServer().getPlayer(args[0]);
                    if(target != null){
                        p.sendMessage(prefix + " §7Der Ping von " + target.getPlayer().getDisplayName() + " §rbeträgt §a" + getPing(target) + " §7ms");
                        return true;
                    } else{
                        p.sendMessage(prefix + " §cDieser Spieler ist nicht online");
                        return true;
                    }

                } else{
                    p.sendMessage(prefix + " §cBitte benutze: §7/ping <Player>");
                }
            } else
                p.sendMessage(prefix + Main.noperm);
        }
        return false;
    }

    public int getPing(Player p){
        CraftPlayer craftPlayer = (CraftPlayer) p;
        EntityPlayer entityPlayer = craftPlayer.getHandle();
        return entityPlayer.ping;
    }
}
