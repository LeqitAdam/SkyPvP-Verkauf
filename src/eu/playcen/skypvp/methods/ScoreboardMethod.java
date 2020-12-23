package eu.playcen.skypvp.methods;

import eu.playcen.coins.api.CoinsAPI;
import eu.playcen.skypvp.skystats.SkyStatsMethod;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.io.File;
import java.util.HashMap;

public class ScoreboardMethod {


    private static HashMap<Scoreboard, Player> boards = new HashMap<>();

    public static void setScoreBoard(Player p) {
        File config = new File("plugins/SkyPvP", "config.yml");
        YamlConfiguration conf = YamlConfiguration.loadConfiguration(config);

        String name = conf.getString("ScoreBoard");
        name = org.bukkit.ChatColor.translateAlternateColorCodes('&', name);
        String ts = conf.getString("Teamspeak");

        Integer kills = SkyStatsMethod.getKills(p.getUniqueId());
        Integer tode = SkyStatsMethod.getDeaths(p.getUniqueId());
        String coins = String.valueOf(CoinsAPI.getBalance(p.getUniqueId()));

        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = board.registerNewObjective("aaa", "bbb");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(name);
        objective.getScore("  ").setScore(12);
        objective.getScore("§fKills:").setScore(11);

        Team killsteam = board.registerNewTeam("kills");
        killsteam.setPrefix("§7» ");
        killsteam.setSuffix(kills.toString());
        killsteam.addEntry(ChatColor.GREEN.toString());

        objective.getScore(ChatColor.GREEN.toString()).setScore(10);

        objective.getScore(" ").setScore(9);
        objective.getScore("§fTode:").setScore(8);

        Team todeteam = board.registerNewTeam("tode");
        todeteam.setPrefix("§7» ");
        todeteam.setSuffix(tode.toString());
        todeteam.addEntry(ChatColor.RED.toString());

        objective.getScore(ChatColor.RED.toString()).setScore(7);

        objective.getScore("      ").setScore(6);
        objective.getScore("§fCoins").setScore(5);

        Team coinsteam = board.registerNewTeam("coins");
        coinsteam.setPrefix("§7» ");
        coinsteam.setSuffix(coins);
        coinsteam.addEntry(ChatColor.GOLD.toString());

        objective.getScore(ChatColor.GOLD.toString()).setScore(4);

        objective.getScore("   ").setScore(3);
        objective.getScore("§fTeamspeak:").setScore(2);
        objective.getScore("§7» §r" + ts).setScore(1);
        objective.getScore("     ").setScore(0);

        boards.put(board, p);

        p.setScoreboard(board);
    }
}
