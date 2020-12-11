package eu.playcen.skypvp.main;

import eu.playcen.skypvp.commands.*;
import eu.playcen.skypvp.listeners.*;
import eu.playcen.skypvp.methods.PerksMethod;
import eu.playcen.skypvp.mysql.MySQL;
import eu.playcen.skypvp.mysql.MySQLFile;
import eu.playcen.skypvp.skinchanger.CMD_Skin;
import eu.playcen.skypvp.skystats.AddDefaultValuesMySQL;
import eu.playcen.skypvp.skystats.AddKillDeathMySQL;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main extends JavaPlugin {

    public static String
    noperm = " §cDiesen Berfehl darfst du nicht benutzen!",
    prefix = "";


    public static Main plugin;

    public static ArrayList<String> vanish = new ArrayList<>();

    @Override
    public void onEnable() {
        plugin = this;

        //MySQL
        MySQLFile file = new MySQLFile();
        if(!file.getFile().exists())
            file.setStandard();
        file.readData();

        try {
            MySQL.connect();
            PreparedStatement ps = MySQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS SkyStats (UUID VARCHAR(100),playername VARCHAR(100),Kills INT(100),Deaths INT(100))");
            ps.executeUpdate();
            Bukkit.getConsoleSender().sendMessage("§c[Skypvp] §aMySQL wurde verbunden!");
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage("§c[Skypvp] §cMySQL konnte keine Verbindung herstellen");
        }

        //Config
        createConfig();
        //Commands
        registerCommands();
        //Listener & Events
        registerEvents();

        loadConfig();

        startClearLag();
        anounceClearLag();


        Bukkit.getConsoleSender().sendMessage("§c[Skypvp] §7Plugin wurde aktiviert");
    }


    private void createConfig() {

        File file = new File("plugins/SkyPvP", "config.yml");

        if(file.exists()) {
            Bukkit.getConsoleSender().sendMessage("§c[Skypvp] §7Config ist bereits erstellt");
            return;
        }
        Bukkit.getConsoleSender().sendMessage("§c[Skypvp] §7Config existiert noch nicht");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        cfg.set("Prefix", "&9SkyPvP &7»");
        cfg.set("menutitle", "&9SkyPvP &8- &eKits");
        cfg.set("Teamspeak", "deinserver.net");
        cfg.set("Discord", "discord.gg/deinserver");
        cfg.set("ScoreBoard", "&9SkyPvP");

        try {
            cfg.save(file);
            Bukkit.getConsoleSender().sendMessage("§c[Skypvp] §7Config wurde ohne Fehler erstellt");
        } catch (IOException e) {
            Bukkit.getConsoleSender().sendMessage("§c[Skypvp] §4Config konnte nicht erstellt werden");
            e.printStackTrace();
        }
        Bukkit.getConsoleSender().sendMessage("§c[Skypvp] §7Config wurde geladen");

    }

    public void loadConfig() {
        File config = new File("plugins/SkyPvP", "config.yml");
        YamlConfiguration conf = YamlConfiguration.loadConfiguration(config);

        String prefix = conf.getString("Prefix");
        prefix = ChatColor.translateAlternateColorCodes('&', prefix);

        Main.prefix = prefix;
    }

    public void registerCommands() {
        getCommand("kit").setExecutor(new CMD_Kit());
        getCommand("gamemode").setExecutor(new CMD_Gamemode());
        getCommand("heal").setExecutor(new CMD_Heal());
        getCommand("vanish").setExecutor(new CMD_Vanish(this));
        getCommand("tp").setExecutor(new CMD_Tp(this));
        getCommand("tphere").setExecutor(new CMD_Tphere(this));
        getCommand("tppos").setExecutor(new CMD_Tppos());
        getCommand("giveall").setExecutor(new CMD_Giveall());
        getCommand("fly").setExecutor(new CMD_Fly());
        getCommand("chatclear").setExecutor(new CMD_Chatclear());
        getCommand("craft").setExecutor(new CMD_Craft());
        getCommand("sun").setExecutor(new CMD_Sun());
        getCommand("globalmute").setExecutor(new CMD_Globalmute());
        getCommand("setspawn").setExecutor(new CMD_SetSpawn());
        //getCommand("Itemlist").setExecutor(new CMD_ItemList());
        getCommand("teamspeak").setExecutor(new CMD_Teamspeak());
        getCommand("discord").setExecutor(new CMD_Discord());
        getCommand("cvillager").setExecutor(new CMD_SetVillagerKit());
        getCommand("perks").setExecutor(new CMD_Perks());
        getCommand("clear").setExecutor(new CMD_Clear());
        getCommand("ec").setExecutor(new CMD_Ec());
        getCommand("invsee").setExecutor(new CMD_Invsee());
        getCommand("clearlag").setExecutor(new CMD_Clearlag());
        getCommand("ping").setExecutor(new CMD_Ping());
        getCommand("rename").setExecutor(new CMD_Rename());
        getCommand("anvil").setExecutor(new CMD_Anvil());
        getCommand("sign").setExecutor(new CMD_Sign());
        getCommand("skin").setExecutor(new CMD_Skin());
        getCommand("stats").setExecutor(new CMD_Stats());
        getCommand("statsreset").setExecutor(new CMD_StatsReset());
        getCommand("build").setExecutor(new CMD_Build());
        getCommand("broadcast").setExecutor(new CMD_Broadcast());
        //getCommand("enchanter").setExecutor(new CMD_Enchanter());
        Bukkit.getConsoleSender().sendMessage("§c[Skypvp] §7Commands wurden aktiviert");
    }

    public void registerEvents() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new JoinListener(), this);
        pm.registerEvents(new InventoryClickListener(), this);
        pm.registerEvents(new KitInventoryListener(), this);
        pm.registerEvents(new ChatListener(), this);
        pm.registerEvents(new RespawnListener(), this);
        pm.registerEvents(new SignClick(), this);
        pm.registerEvents(new ColorSigns(), this);
        pm.registerEvents(new HandleVillager(), this);
        pm.registerEvents(new PerksInvListener(), this);
        pm.registerEvents(new PerksMethod(), this);
        pm.registerEvents(new PerksOnJoin(), this);
        pm.registerEvents(new AddDefaultValuesMySQL(), this);
        pm.registerEvents(new AddKillDeathMySQL(), this);
        pm.registerEvents(new CommandBlockListener(), this);
        pm.registerEvents(new SkyPvPListener(), this);
        pm.registerEvents(new Environment(), this);
        Bukkit.getConsoleSender().sendMessage("§c[Skypvp] §7Events wurden aktiviert");
    }

    public static Main getPlugin() {
        return plugin;
    }

    public static boolean FlyPerkPS;
    public static int use;
    static {
        Main.FlyPerkPS = false;
        Main.use = 0;
    }

    @Override
    public void onDisable() {
        MySQL.disconnect();
        Bukkit.getConsoleSender().sendMessage("§c[Skypvp] §7Plugin wurde deaktiviert");
        for(Player all : Bukkit.getOnlinePlayers()){
            if(CMD_Build.builders.contains(all))
                all.sendMessage(Main.prefix + " §6Du bist nun nicht mehr im Build-Modus");
        }
    }

    private void startClearLag(){
        Bukkit.getScheduler().runTaskTimerAsynchronously(this, () -> {
            CMD_Clearlag cl = new CMD_Clearlag();
            cl.clearlag();
            anounceClearLag();
        }, 20*60*4, 20*60*4);
    }

    private void anounceClearLag(){
        Bukkit.getScheduler().runTaskLater(this, () -> {
            for(Player all : Bukkit.getOnlinePlayers()){
                all.sendMessage(Main.prefix + " §aIn 2 Minuten werden allen am Boden liegenden Items gelöscht.");
            }
        }, 20*60*2);

    }

}
