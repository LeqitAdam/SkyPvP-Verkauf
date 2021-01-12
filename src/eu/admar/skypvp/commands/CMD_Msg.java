package eu.admar.skypvp.commands;

import eu.admar.skypvp.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.HashMap;

public class CMD_Msg implements CommandExecutor {
    private String use = " §cBitte benutze: §7/msg <Spieler> <Nachricht>";
    public static HashMap<Player, Player> respond = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(p.hasPermission("skypvp.msg") || p.hasPermission("skypvp.*")) {
                if(cmd.getName().equalsIgnoreCase("msg")) {
                    if(args.length >= 2) {
                        final Player target = Bukkit.getPlayer(args[0]);
                        if(target != null) {
                            String msg = "";
                            for (int i = 1; i < args.length; ++i) {
                                msg = msg + args[i] + " ";
                            }
                            msg = ChatColor.translateAlternateColorCodes('&', msg);
                            sendMSG(p, target, msg);
                        }else
                            p.sendMessage(Main.prefix + " §cDieser Spieler ist nicht online!");
                    }else
                        p.sendMessage(Main.prefix + use);
                }else
                    p.sendMessage(Main.prefix + use);
            }else
                p.sendMessage(Main.prefix + Main.noperm);
        }else
            sender.sendMessage(Main.prefix + " §cNur Spieler können diesen Befehl nutzen!");

        return false;
    }

    public static void sendMSG(Player p, Player target, String msg) {
        File player = new File("plugins/SkyPvP/Spieler", target.getUniqueId() + ".yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(player);
        if(!(cfg.getBoolean("MSG") == false)) {
            target.sendMessage("§6MSG §7» §8[§a" + p.getDisplayName() + "§8] §7>> §8[§cmir§8] §7» " + msg);
            p.sendMessage("§6MSG §7» §8[§cDu§8] §7>> §8[§a" + target.getDisplayName() + "§8] §7» " + msg);
            respond.put(target, p);
            respond.put(p, target);
        }else if(p.hasPermission("skypvp.*")) {
            target.sendMessage("§6MSG §7» §8[§a" + p.getDisplayName() + "§8] §7>> §8[§cmir§8] §7» " + msg);
            p.sendMessage("§6MSG §7» §8[§cDu§8] §7>> §8[§a" + target.getDisplayName() + "§8] §7» " + msg);
            respond.put(target, p);
            respond.put(p, target);
        }else
            p.sendMessage(Main.prefix + " §7Der Spieler §a" + target.getDisplayName() + " §7empfängt §ckeine §eNachrichten!");
    }
}
