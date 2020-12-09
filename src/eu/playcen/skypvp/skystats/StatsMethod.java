package eu.playcen.skypvp.skystats;

import eu.playcen.skypvp.methods.ScoreboardMethod;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class StatsMethod {

    public static void printStats(Player p, Double kills, Double deaths, Double kd, String name) {
        File config = new File("plugins/SkyPvP", "config.yml");
        YamlConfiguration conf = YamlConfiguration.loadConfiguration(config);

        String prefix = conf.getString("Prefix");
        prefix = ChatColor.translateAlternateColorCodes('&', prefix);

        kd = Math.floor(kd*100)/100.0;

            p.sendMessage(prefix + "      §9Sky§fStats §8- §7von §a" + name);
            p.sendMessage(prefix + "§0");
            p.sendMessage(prefix + "  §7Kills: §e" + Math.round(kills));
            p.sendMessage(prefix + "  §7Deaths: §e" + Math.round(deaths));
            p.sendMessage(prefix + "  §7KD: §e" + kd);
            p.sendMessage(prefix + "§0");
    }

    public static void statsReset(Player p) {
        int kills = SkyStatsMethod.getKills(p.getUniqueId());
        int deaths = SkyStatsMethod.getDeaths(p.getUniqueId());

        SkyStatsMethod.updateKills(p.getUniqueId(), kills, true, p.getName());
        SkyStatsMethod.updateDeaths(p.getUniqueId(), deaths, true, p.getName());
        ScoreboardMethod.setScoreBoard(p);
    }

    public static  void statsResetOff(OfflinePlayer offp) {
        int kills = SkyStatsMethod.getKills(offp.getUniqueId());
        int deaths = SkyStatsMethod.getDeaths(offp.getUniqueId());

        SkyStatsMethod.updateKills(offp.getUniqueId(), kills, true, offp.getName());
        SkyStatsMethod.updateDeaths(offp.getUniqueId(), deaths, true, offp.getName());
    }
}
