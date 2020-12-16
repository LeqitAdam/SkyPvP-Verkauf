package eu.playcen.skypvp.commands;

import eu.playcen.skypvp.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CMD_Build implements CommandExecutor {

    public static ArrayList<Player> builders = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player){
            Player p = (Player) sender;
            if(args.length == 0){
                if(p.hasPermission("skypvp.build") || p.hasPermission("skypvp.*")){
                    if(!CMD_Build.builders.contains(p)){
                        CMD_Build.builders.add(p);
                        p.sendMessage(Main.prefix + " §aDu bist nun im Build-Modus");
                        p.playSound(p.getLocation(), Sound.ANVIL_LAND, 1, 1);
                        p.setGameMode(GameMode.CREATIVE);
                    } else{
                        CMD_Build.builders.remove(p);
                        p.sendMessage(Main.prefix + " §6Du bist nun nicht mehr im Build-Modus");
                        p.playSound(p.getLocation(), Sound.ANVIL_BREAK, 1, 1);
                        p.setGameMode(GameMode.SURVIVAL);
                    }
                    return true;
                } else
                    p.sendMessage(Main.prefix + Main.noperm);
            }
            if(args.length == 1){
                if(p.hasPermission("skypvp.build.other") || p.hasPermission("skypvp.*")){
                    Player target = Bukkit.getServer().getPlayer(args[0]);
                    if(target != null){
                        if(!CMD_Build.builders.contains(target)){
                            CMD_Build.builders.add(target);
                            p.sendMessage(Main.prefix + " §7Der Spieler §r" + target.getDisplayName() + " §7ist nun im Build-Modus");
                            target.sendMessage(Main.prefix + " §aDu bist nun im Build-Modus");
                            target.playSound(target.getLocation(), Sound.ANVIL_LAND, 1, 1);
                            target.setGameMode(GameMode.CREATIVE);
                        } else {
                            CMD_Build.builders.remove(target);
                            p.sendMessage(Main.prefix + " §6Der Spieler §r" + target.getDisplayName() + " §6ist nun nicht mehr im Build-Modus");
                            p.sendMessage(Main.prefix + " §6Du bist nun nicht mehr im Build-Modus");
                            target.playSound(target.getLocation(), Sound.ANVIL_BREAK, 1, 1);
                            target.setGameMode(GameMode.SURVIVAL);
                        }
                    } else {
                        p.sendMessage(Main.prefix + " §cDieser Spieler ist nicht online");
                    }
                } else
                    p.sendMessage(Main.prefix + Main.noperm);

            } else{
                p.sendMessage(Main.prefix + " §7Bitte benutze §c/build <Player>");
            }

        }else
            sender.sendMessage(Main.prefix + " §cNur Spieler können diesen Befehl nutzen!");
        return false;
    }
}
