package eu.playcen.skypvp.methods;

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
        File StatsFile = new File("plugins/SkyPvP/users/stats", p.getUniqueId() + ".yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(StatsFile);

        /*String kills = cfg.getString("Kills");
        String tode = cfg.getString("Tode");
        String coins = cfg.getString("Coins");*/
        String kills = "test";
        String tode = "test1";
        String coins = "test2";

        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = board.registerNewObjective("aaa", "bbb");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("§9§lPlaycen§7§l.§c§lnet");
        objective.getScore("  ").setScore(12);
        objective.getScore("§fKills:").setScore(11);

        Team killsteam = board.registerNewTeam("kills");
        killsteam.setPrefix("§7» ");
        killsteam.setSuffix(kills);
        killsteam.addEntry(ChatColor.GREEN.toString());

        objective.getScore(ChatColor.GREEN.toString()).setScore(10);

        objective.getScore(" ").setScore(9);
        objective.getScore("§fTode:").setScore(8);

        Team todeteam = board.registerNewTeam("tode");
        todeteam.setPrefix("§7» ");
        todeteam.setSuffix(tode);
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
        objective.getScore("§7» §9Playcen§7.§cnet").setScore(1);
        objective.getScore("     ").setScore(0);

        boards.put(board, p);

        p.setScoreboard(board);
    }
}