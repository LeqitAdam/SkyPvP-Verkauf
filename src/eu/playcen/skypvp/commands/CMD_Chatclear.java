package eu.playcen.skypvp.commands;

import eu.playcen.skypvp.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_Chatclear implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (p.hasPermission("system.chatclear")) {

                for (int i = 0; i < 200; i++) {
                    for (Player t : Bukkit.getOnlinePlayers()) {

                        // ab hier sind die Bypass - Rechte

                        if (t.hasPermission("system.chatclear.bypass")) {
                            continue;
                        }
                        t.sendMessage("");
                    }
                }
                Bukkit.broadcastMessage(Main.pre + "§7Der Chat wurde von §a" + p.getName() + " §7geleert!");
            } else {
                p.sendMessage(Main.pre + Main.noperm);
            }
        } else {
            System.out.println(Main.pre + "Nur Spieler können diesen Befehl nutzen!");
        }
        return true;
    }
}
