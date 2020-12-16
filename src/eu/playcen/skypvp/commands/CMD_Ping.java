package eu.playcen.skypvp.commands;

import eu.playcen.skypvp.main.Main;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class CMD_Ping implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(p.hasPermission("skypvp.ping") || p.hasPermission("skypvp.*")){
                if(args.length == 0){
                    p.sendMessage(Main.prefix + " §7Dein Ping beträgt §a" + getPing(p) + " §7ms");
                    return true;
                }
                if(args.length == 1){
                    Player target = Bukkit.getServer().getPlayer(args[0]);
                    if(target != null){
                        p.sendMessage(Main.prefix + " §7Der Ping von " + target.getPlayer().getDisplayName() + " §r§7beträgt §a" + getPing(target) + " §7ms");
                        return true;
                    } else{
                        p.sendMessage(Main.prefix + " §cDieser Spieler ist nicht online");
                        return true;
                    }

                } else{
                    p.sendMessage(Main.prefix + " §cBitte benutze: §7/ping <Player>");
                }
            } else
                p.sendMessage(Main.prefix + Main.noperm);
        }else
            sender.sendMessage(Main.prefix + " §cNur Spieler können diesen Befehl nutzen!");
        return false;
    }

    public int getPing(Player p){
        CraftPlayer craftPlayer = (CraftPlayer) p;
        EntityPlayer entityPlayer = craftPlayer.getHandle();
        return entityPlayer.ping;
    }
}
