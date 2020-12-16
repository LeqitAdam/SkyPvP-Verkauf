package eu.playcen.skypvp.commands;

import eu.playcen.skypvp.main.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_RespondMsg implements CommandExecutor {
    private String use = " §cBitte benutze: §7/r <Spieler> <Nachricht>";

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(p.hasPermission("skypvp.msg") || p.hasPermission("skypvp.*")) {
                if(cmd.getName().equalsIgnoreCase("r") || cmd.getName().equalsIgnoreCase("respond")) {
                    if(CMD_Msg.respond.get(p) != null) {
                        Player t = CMD_Msg.respond.get(p);
                        if (args.length >= 1) {
                            String msg = "";
                            for (int i = 0; i < args.length; ++i) {
                                msg = msg + args[i] + " ";
                            }
                            msg = ChatColor.translateAlternateColorCodes('&', msg);
                            CMD_Msg.sendMSG(p, t, msg);
                        } else
                            p.sendMessage(Main.prefix + use);
                    }else
                        p.sendMessage(Main.prefix + " §cDu hast keine Konversation offen!");
                }else
                    p.sendMessage(Main.prefix + use);
            }else
                p.sendMessage(Main.prefix + Main.noperm);
        }else
            sender.sendMessage(Main.prefix + " §cNur Spieler können diesen Befehl nutzen!");
        return false;
    }
}
