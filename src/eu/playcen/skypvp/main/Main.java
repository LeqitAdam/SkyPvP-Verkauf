package eu.playcen.skypvp.main;

import eu.playcen.skypvp.commands.CMD_Gamemode;
import eu.playcen.skypvp.commands.CMD_Heal;
import eu.playcen.skypvp.commands.CMD_Vanish;
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
