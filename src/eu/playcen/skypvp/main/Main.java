package eu.playcen.skypvp.main;

import eu.playcen.skypvp.commands.CMD_Gamemode;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static String pre = "§9Playcen§fSkyPvP §8» §7",
    noperm = "§cDiesen Berfehl darfst du nicht benutzen!",
    cmdnotfound = "§cDieser Befehl wurde nicht gefunden!",
    cmdp = "§Diesen Befehl dürfen nur Spieler benutzen!";

    @Override
    public void onEnable() {

        //Commands
        getCommand("gamemode").setExecutor(new CMD_Gamemode());


        //Listener & Events

    }
}
