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

        Player p = (Player) sender;
        if (p.hasPermission("system.tphere")) {
            if (args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);
                if (target != null) {
                    if (!(plugin.vanish.contains(target.getName()))) {
                        target.teleport(p);
                        p.sendMessage(Main.pre + "§7Du hast §a" + target.getName() + " §7zu dir Teleportiert!");
                    } else if ((p.isOp() || p.hasPermission("system.tp.bypass"))) {
                        target.teleport(p);
                        p.sendMessage(Main.pre + "§7Du hast §a" + target.getName() + " §7zu dir Teleportiert!");
                    } else if(target.hasPermission("system.tphere.owner") && (!p.hasPermission("system.tphere.owner.bypass"))) {
                        p.sendMessage(Main.pre + "§7Den Spieler §a" + args[0] + " §7darfst du §cnicht §7teleportieren!");
                        return true;
                    } else if(p.hasPermission("system.tphere.owner.bypass")) {
                        target.teleport(p);
                        p.sendMessage("§7Der Spieler §a" + target.getName() + " §7wurde zu §a" + p.getName()
                                + " §7teleportiert!");
                        return true;
                    } else {
                        p.sendMessage(Main.pre + "§7Dieser Spieler ist derzeit §cnicht online!");
                    }

                } else
                    p.sendMessage(Main.pre + "§7Dieser Spieler ist §cnicht online!");
            } else
                p.sendMessage(Main.pre + "§cBitte benutze: §7/tphere [Name]");
        } else
            p.sendMessage(Main.pre + Main.noperm);

        return false;
    }

}
