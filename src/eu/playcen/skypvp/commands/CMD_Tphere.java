package eu.playcen.skypvp.commands;

import eu.playcen.skypvp.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_Tphere implements CommandExecutor {

    private Main plugin;

    public CMD_Tphere(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(Main.prefix + " §cNur Spieler können diesen Befehl nutzen!");
            return true;
        }
        Player p = (Player) sender;
        if (p.hasPermission("skypvp.tphere") || p.hasPermission("skypvp.*")) {
            if (args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);
                if (target != null) {
                    if (!(plugin.vanish.contains(target.getName()))) {
                        target.teleport(p);
                        p.sendMessage(Main.prefix + " §7Du hast §a" + target.getName() + " §7zu dir Teleportiert!");
                    } else if ((p.isOp() || p.hasPermission("skypvp.tp.bypass") || p.hasPermission("skypvp.*"))) {
                        target.teleport(p);
                        p.sendMessage(Main.prefix + " §7Du hast §a" + target.getName() + " §7zu dir Teleportiert!");
                    } else if(target.hasPermission("skypvp.tphere.owner") && (!(p.hasPermission("skypvp.tphere.owner.bypass") || p.hasPermission("skypvp.*")))) {
                        p.sendMessage(Main.prefix + " §7Den Spieler §a" + args[0] + " §7darfst du §cnicht §7teleportieren!");
                        return true;
                    } else if(p.hasPermission("skypvp.tphere.owner.bypass") || p.hasPermission("skypvp.*")) {
                        target.teleport(p);
                        p.sendMessage(Main.prefix + " §7Der Spieler §a" + target.getName() + " §7wurde zu §a" + p.getName()
                                + " §7teleportiert!");
                        return true;
                    } else {
                        p.sendMessage(Main.prefix + " §7Dieser Spieler ist derzeit §cnicht online!");
                    }

                } else
                    p.sendMessage(Main.prefix + " §7Dieser Spieler ist §cnicht online!");
            } else
                p.sendMessage(Main.prefix + " §cBitte benutze: §7/tphere [Name]");
        } else
            p.sendMessage(Main.prefix + Main.noperm);

        return false;
    }

}
