package eu.playcen.skypvp.commands;

import eu.playcen.skypvp.main.Main;
import eu.playcen.skypvp.skystats.SkyStatsMethod;
import eu.playcen.skypvp.skystats.StatsMethod;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.math.BigDecimal;

public class CMD_Stats implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        File config = new File("plugins/SkyPvP", "config.yml");
        YamlConfiguration conf = YamlConfiguration.loadConfiguration(config);

        String prefix = conf.getString("Prefix");
        prefix = ChatColor.translateAlternateColorCodes('&', prefix);

        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(p.hasPermission("skypvp.stats") ||p.hasPermission("skypvp.*")) {
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
                            Double kills = Double.valueOf(SkyStatsMethod.getKills(target.getUniqueId()));
                            Double deaths = Double.valueOf(SkyStatsMethod.getDeaths(target.getUniqueId()));

                            if(deaths == 0) {
                                Double deaths2 = 1.0;
                                Double kd = kills / deaths;

                                StatsMethod.printStats(p,kills,deaths, kd, target.getName());
                            }else {
                                Double kd = kills / deaths;

                                StatsMethod.printStats(p,kills,deaths, kd, target.getName());
                            }
                        }else if(offtarget != null){
                            Double kills = Double.valueOf(SkyStatsMethod.getKills(offtarget.getUniqueId()));
                            Double deaths = Double.valueOf(SkyStatsMethod.getDeaths(offtarget.getUniqueId()));

                            if(deaths == 0) {
                                Double deaths2 = 1.0;
                                Double kd = kills / deaths;

                                StatsMethod.printStats(p,kills,deaths, kd, args[0]);
                            }else {
                                Double kd = kills / deaths;

                                StatsMethod.printStats(p,kills,deaths, kd, args[0]);
                            }
                        }else
                            p.sendMessage(prefix + " §cDieser Spieler hat nie das Netzwerk betreten!");
                    }else
                        p.sendMessage(prefix + " §cBitte benutze: §7/stats <Name>");
                }else
                    p.sendMessage(prefix + " §cBitte benutze: §7/stats <Name>");
            }else
                p.sendMessage(prefix + Main.noperm);
        }else
            sender.sendMessage(prefix + " §cNur Spieler können diesen Befehl benutzen!");
        return false;
    }
}
