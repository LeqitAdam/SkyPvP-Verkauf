package eu.playcen.skypvp.main;

import eu.playcen.skypvp.commands.*;
import eu.playcen.skypvp.listeners.InventoryClickListener;
import eu.playcen.skypvp.listeners.JoinListener;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Main extends JavaPlugin {

    public static String
    noperm = " §cDiesen Berfehl darfst du nicht benutzen!",
    cmdnotfound = " §cDieser Befehl wurde nicht gefunden!",
    cmdp = " §Diesen Befehl dürfen nur Spieler benutzen!";

    private static Main plugin;

    public ArrayList<String> vanish = new ArrayList<String>();

    @Override
    public void onEnable() {

        createConfig();
        plugin = this;

        //Commands
        getCommand("gamemode").setExecutor(new CMD_Gamemode());
        getCommand("heal").setExecutor(new CMD_Heal());
        getCommand("vanish").setExecutor(new CMD_Vanish(this));
        getCommand("tp").setExecutor(new CMD_Tp(this));
        getCommand("tphere").setExecutor(new CMD_Tphere(this));
        getCommand("tppos").setExecutor(new CMD_Tppos());
        getCommand("giveall").setExecutor(new CMD_Giveall());
        getCommand("fly").setExecutor(new CMD_Fly());
        getCommand("chatclear").setExecutor(new CMD_Chatclear());

        //Kit - Menu
        getCommand("kit").setExecutor(new CMD_Kit());

        /*
         /fly
         /kit
            /kits
         /tp
         /tphere
         /tppos
         /heal
         /vanish
         /chatclear
         /
         */


        //Listener & Events
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new JoinListener(), this);
        pm.registerEvents(new InventoryClickListener(), this);

    }

    private void createConfig() {

        File file = new File("plugins/SkyPvP", "config.yml");

        if(file.exists()) return;

        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        cfg.set("Prefix", "&9SkyPvP &7»");
        cfg.set("menutitle", "&9SkyPvP §8- §eKits");

        try {
            cfg.save(file);
            System.out.println("[SkyPvP] Config wurde erstellt!");
        } catch (IOException e) {
            System.out.println("[SkyPvP] Config wurde nicht erstellt");
            e.printStackTrace();
        }

    }

    public static Main getPlugin() {
        return plugin;
    }

}
