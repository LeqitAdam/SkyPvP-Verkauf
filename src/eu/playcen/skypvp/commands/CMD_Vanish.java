package eu.playcen.skypvp.commands;

import eu.playcen.skypvp.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_Vanish implements CommandExecutor {

    private Main plugin;

    public CMD_Vanish(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player))
            return true;

        Player p = (Player) sender;
        if (p.hasPermission("skypvp.vanish") || p.hasPermission("skypvp.*")) {
            if(cmd.getName().equalsIgnoreCase("vanish") || cmd.getName().equalsIgnoreCase("v")) {
                if (args.length == 0) {
                    if (Main.vanish.contains(p.getName())) {
                        Main.vanish.remove(p.getName());
                        p.sendMessage(Main.prefix + " §7Du bist nun für §aalle §7Spieler §asichtbar!");
                        for (Player all : Bukkit.getOnlinePlayers()) {
                            all.showPlayer(p);
                        }
                    } else {
                        for (Player all : Bukkit.getOnlinePlayers()) {
                            if (!all.hasPermission("skypvp.vanish.see")) {
                                all.hidePlayer(p);
                            }
                        }

                        Main.vanish.add(p.getName());
                        p.sendMessage(Main.prefix + " §7Du bist nun für §aalle normalen §7Spieler §cunsichtbar!");

                    }
                } else if (args.length == 1) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target != null) {
                        if (p.hasPermission("skypvp.vanish.others") || p.hasPermission("skypvp.*")) {
                            if (!(target.hasPermission("skypvp.vanish.others.ignore") || target.hasPermission("skypvp.*"))) {
                                if (Main.vanish.contains(target.getName())) {
                                    Main.vanish.remove(target.getName());
                                    target.sendMessage(Main.prefix + " §7Du bist nun für §aalle §7Spieler §asichtbar!");
                                    p.sendMessage(Main.prefix + " §7Du hast den Spieler §a" + target.getName()
                                            + " §7aus dem §cVanish §7entfernt");
                                    for (Player all : Bukkit.getOnlinePlayers()) {
                                        all.showPlayer(target);
                                    }

                                } else {
                                    for (Player all : Bukkit.getOnlinePlayers()) {
                                        if (!all.hasPermission("skypvp.vanish.see")) {
                                            all.hidePlayer(target);
                                        }

                                    }
                                    Main.vanish.add(target.getName());
                                    target.sendMessage(
                                            Main.prefix + " §7Du bist nun für §aalle normalen §7Spieler §cunsichtbar!");
                                    p.sendMessage(Main.prefix + " §7Du hast den Spieler §a" + target.getName()
                                            + " §7in den §cVanish §7gesetzt");
                                }
                            } else if (p.hasPermission("Main.prefix.owner") || p.hasPermission("skypvp.*")) {
                                if (Main.vanish.contains(target.getName())) {
                                    Main.vanish.remove(target.getName());
                                    target.sendMessage(Main.prefix + "§7Du bist nun für §aalle §7Spieler §asichtbar!");
                                    p.sendMessage(Main.prefix + " §7Du hast den Spieler §a" + target.getName()
                                            + " §7aus dem §cVanish §7entfernt");
                                    for (Player all : Bukkit.getOnlinePlayers()) {
                                        all.showPlayer(target);
                                    }

                                } else {
                                    for (Player all : Bukkit.getOnlinePlayers()) {
                                        if (!(all.hasPermission("skypvp.vanish.see") || all.hasPermission("skypvp.*"))) {
                                            all.hidePlayer(target);
                                        }

                                    }
                                    Main.vanish.add(target.getName());
                                    target.sendMessage(
                                            Main.prefix + " §7Du bist nun für §aalle normalen §7Spieler §cunsichtbar!");
                                    p.sendMessage(
                                            Main.prefix + " §7Du hast §a" + target.getName() + " §7in den §cVanish §7gesetzt");
                                }
                            } else
                                p.sendMessage(Main.prefix + " §7Diesen Spieler darfst du §cnicht vanishen!");
                        } else
                            p.sendMessage(Main.prefix + " §cBitte benutze: §7/vanish");
                    } else
                        sender.sendMessage(Main.prefix + " §7Der Spieler §a" + args[0] + " §7ist §cnicht online!");
                } else
                    p.sendMessage(Main.prefix + " §cBitte benutze: §7/vanish [Name]");
            }else
                p.sendMessage(Main.prefix + " §cBitte benutze: §7/vanish [Name]");
        } else
            p.sendMessage(Main.prefix + Main.noperm);

        return false;
    }
}
