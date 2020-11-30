package eu.playcen.skypvp.commands;

import eu.playcen.skypvp.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.io.File;

public class CMD_Vanish implements CommandExecutor {

    private Main plugin;

    public CMD_Vanish(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        File config = new File("plugins/SkyPvP", "config.yml");
        YamlConfiguration conf = YamlConfiguration.loadConfiguration(config);

        String prefix = conf.getString("Prefix");
        prefix = ChatColor.translateAlternateColorCodes('&', prefix);

        Player p = (Player) sender;
        if (p.hasPermission("skypvp.vanish") || p.hasPermission("skypvp.*")) {
            if(cmd.getName().equalsIgnoreCase("vanish") || cmd.getName().equalsIgnoreCase("v")) {
                if (args.length == 0) {
                    if (plugin.vanish.contains(p.getName())) {
                        plugin.vanish.remove(p.getName());
                        p.sendMessage(prefix + " §7Du bist nun für §aalle §7Spieler §asichtbar!");
                        p.removePotionEffect(PotionEffectType.INVISIBILITY);
                        for (Player all : Bukkit.getOnlinePlayers()) {
                            all.showPlayer(p);
                        }
                    } else {
                        for (Player all : Bukkit.getOnlinePlayers()) {
                            if (!all.hasPermission("skypvp.vanish.see")) {
                                all.hidePlayer(p);
                            }
                        }

                        plugin.vanish.add(p.getName());
                        p.sendMessage(prefix + " §7Du bist nun für §aalle normalen §7Spieler §cunsichtbar!");
                        p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999, 1));

                    }
                } else if (args.length == 1) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target != null) {
                        if (p.hasPermission("skypvp.vanish.others") || p.hasPermission("skypvp.*")) {
                            if (!(target.hasPermission("skypvp.vanish.others.ignore") || target.hasPermission("skypvp.*"))) {
                                if (plugin.vanish.contains(target.getName())) {
                                    plugin.vanish.remove(target.getName());
                                    target.sendMessage(prefix + " §7Du bist nun für §aalle §7Spieler §asichtbar!");
                                    target.removePotionEffect(PotionEffectType.INVISIBILITY);
                                    p.sendMessage(prefix + " §7Du hast den Spieler §a" + target.getName()
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
                                    plugin.vanish.add(target.getName());
                                    target.sendMessage(
                                            prefix + " §7Du bist nun für §aalle normalen §7Spieler §cunsichtbar!");
                                    p.sendMessage(prefix + " §7Du hast den Spieler §a" + target.getName()
                                            + " §7in den §cVanish §7gesetzt");
                                    target.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999, 1));
                                }
                            } else if (p.hasPermission("prefix.owner") || p.hasPermission("skypvp.*")) {
                                if (plugin.vanish.contains(target.getName())) {
                                    plugin.vanish.remove(target.getName());
                                    target.sendMessage(prefix + "§7Du bist nun für §aalle §7Spieler §asichtbar!");
                                    target.removePotionEffect(PotionEffectType.INVISIBILITY);
                                    p.sendMessage(prefix + " §7Du hast den Spieler §a" + target.getName()
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
                                    plugin.vanish.add(target.getName());
                                    target.sendMessage(
                                            prefix + " §7Du bist nun für §aalle normalen §7Spieler §cunsichtbar!");
                                    p.sendMessage(
                                            prefix + " §7Du hast §a" + target.getName() + " §7in den §cVanish §7gesetzt");
                                    target.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999, 1));
                                }
                            } else
                                p.sendMessage(prefix + " §7Diesen Spieler darfst du §cnicht vanishen!");
                        } else
                            p.sendMessage(prefix + " §cBitte benutze: §7/vanish");
                    } else
                        sender.sendMessage(prefix + " §7Der Spieler §a" + args[0] + " §7ist §cnicht online!");
                } else
                    p.sendMessage(prefix + " §cBitte benutze: §7/vanish [Name]");
            }else
                p.sendMessage(prefix + " §cBitte benutze: §7/vanish [Name]");
        } else
            p.sendMessage(prefix + Main.noperm);

        return false;
    }
}
