package eu.playcen.skypvp.main;

import eu.playcen.skypvp.commands.*;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class Main extends JavaPlugin {

    public static String pre = "§9SkyPvP §8» §7",
    noperm = "§cDiesen Berfehl darfst du nicht benutzen!",
    cmdnotfound = "§cDieser Befehl wurde nicht gefunden!",
    cmdp = "§Diesen Befehl dürfen nur Spieler benutzen!";

    private static Main plugin;

    public ArrayList<String> vanish = new ArrayList<String>();

    @Override
    public void onEnable() {

        //Commands
        getCommand("gamemode").setExecutor(new CMD_Gamemode());
        getCommand("heal").setExecutor(new CMD_Heal());
        getCommand("vanish").setExecutor(new CMD_Vanish(this));
        getCommand("tp").setExecutor(new CMD_Tp(this));
        getCommand("tphere").setExecutor(new CMD_Tphere(this));
        getCommand("tppos").setExecutor(new CMD_Tppos());
        getCommand("giveall").setExecutor(new CMD_Giveall());

        /*
         /fly
         /kit
            /kits
         /tp
         /tphere
         /tppos
         /heal
         /vanish
         /
         */


        //Listener & Events

    }

    public static Main getPlugin() {
        return plugin;
    }
}
