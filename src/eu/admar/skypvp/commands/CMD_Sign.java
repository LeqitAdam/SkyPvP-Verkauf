package eu.admar.skypvp.commands;

import eu.admar.skypvp.main.Main;
import eu.admar.skypvp.methods.Manager_Sign;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_Sign implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Main.prefix + " §cNur Spieler können diesen Befehl nutzen!");
            return false;
        }
        final Player p = (Player) sender;
        if (p.hasPermission("skypvp.sign.use") || p.hasPermission("skypvp.*")) {
            if (args.length >= 1) {
                if (p.getItemInHand().getType() != Material.AIR) {
                    if (p.getItemInHand().getAmount() == 1) {
                        final Manager_Sign manager_sign = new Manager_Sign(p.getItemInHand());
                        if (args[0].equalsIgnoreCase("del")) {
                            if(p.hasPermission("skypvp.sign.remove") || p.hasPermission("skypvp.*")) {
                                if (manager_sign.isSigned()) {
                                    p.setItemInHand(manager_sign.unSign());
                                    p.sendMessage(Main.prefix + " §7Du hast §aerfolgreich §7die §cSignatur entfernt!");
                                } else
                                    p.sendMessage(Main.prefix + " §7Das §aItem §7wurde noch §cnicht signiert!");
                            }else
                                p.sendMessage(Main.prefix + Main.noperm);
                        } else {
                            final StringBuilder stringBuilder = new StringBuilder();
                            for (final String arg : args) {
                                stringBuilder.append(arg).append(" ");
                            }
                            if (!manager_sign.isSigned()) {
                                p.setItemInHand(manager_sign.sign(p.getName(), stringBuilder.toString()));
                                p.sendMessage(Main.prefix + " §7Item §aerfolgreich §csigniert!");
                            } else 
                                p.sendMessage(Main.prefix + " §7Das §aItem §7wurde breits §csigniert!");
                        }
                    } else 
                        p.sendMessage(Main.prefix + " §7Du darfst nur ein §aItem §7gleichzeitig §csignieren!");
                } else 
                    p.sendMessage(Main.prefix + " §7Du §cmusst §7ein §aItem §7in der Hand halten!");
            } else {
                p.sendMessage(Main.prefix + " §cBitte benutze: §7/sign <Nachricht>");
                p.sendMessage(Main.prefix + " §cBitte benutze: §7/sign del");
            }
        } else
            p.sendMessage(Main.prefix + Main.noperm);
        return false;
    }
}
