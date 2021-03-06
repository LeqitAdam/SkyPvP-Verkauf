package eu.admar.skypvp.commands;

import eu.admar.skypvp.main.Main;
import eu.admar.skypvp.skystats.StatsMethod;
import eu.admar.skypvp.skystats.SkyStatsMethod;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_Stats implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(p.hasPermission("skypvp.stats") || p.hasPermission("skypvp.*")) {
                if(cmd.getName().equalsIgnoreCase("stats")) {
                        if(args.length == 0) {
                            Double kills = Double.valueOf(SkyStatsMethod.getKills(p.getUniqueId()));
                            Double deaths = Double.valueOf(SkyStatsMethod.getDeaths(p.getUniqueId()));

                            if(deaths == 0) {
                                Double deaths2 = 1.0;
                                Double kd = kills / deaths2;

                                StatsMethod.printStats(p,kills,deaths, kd, p.getName());
                            }else {
                                Double kd = kills / deaths;

                                StatsMethod.printStats(p,kills,deaths, kd, p.getName());
                            }
                        }else if(args.length == 1) {
                            Player target = Bukkit.getPlayer(args[0]);
                            OfflinePlayer offtarget = Bukkit.getOfflinePlayer(args[0]);
                                if(target != null) {
                                    if(SkyStatsMethod.isUserExists(target.getUniqueId())) {
                                        Double kills = Double.valueOf(SkyStatsMethod.getKills(target.getUniqueId()));
                                        Double deaths = Double.valueOf(SkyStatsMethod.getDeaths(target.getUniqueId()));

                                        if(deaths == 0) {
                                            Double deaths2 = 1.0;
                                            Double kd = kills / deaths2;

                                            StatsMethod.printStats(p,kills,deaths, kd, target.getName());
                                        }else {
                                            Double kd = kills / deaths;

                                            StatsMethod.printStats(p,kills,deaths, kd, target.getName());
                                        }
                                    }else
                                        p.sendMessage(Main.prefix + " §7Diser Spieler hat §cnoch keinen §aEintrag §7in der §eDatenbank!");
                                }else if(offtarget != null){
                                    if(SkyStatsMethod.isUserExists(offtarget.getUniqueId())) {
                                        Double kills = Double.valueOf(SkyStatsMethod.getKills(offtarget.getUniqueId()));
                                        Double deaths = Double.valueOf(SkyStatsMethod.getDeaths(offtarget.getUniqueId()));

                                        if(deaths == 0) {
                                            Double deaths2 = 1.0;
                                            Double kd = kills / deaths2;

                                            StatsMethod.printStats(p,kills,deaths, kd, args[0]);
                                        }else {
                                            Double kd = kills / deaths;

                                            StatsMethod.printStats(p,kills,deaths, kd, args[0]);
                                        }
                                    }else
                                        p.sendMessage(Main.prefix + " §7Diser Spieler hat §cnoch keinen §aEintrag §7in der §eDatenbank!");
                            }else
                                p.sendMessage(Main.prefix + " §7Diser Spieler ist §cnicht §7in der §eDatenbank!");
                        }else
                            p.sendMessage(Main.prefix + " §cBitte benutze: §7/stats <Name>");
                }else
                    p.sendMessage(Main.prefix + " §cBitte benutze: §7/stats <Name>");
            }else
                p.sendMessage(Main.prefix + Main.noperm);
        }else
            sender.sendMessage(Main.prefix + " §cNur Spieler können diesen Befehl nutzen!");
        return false;
    }
}
