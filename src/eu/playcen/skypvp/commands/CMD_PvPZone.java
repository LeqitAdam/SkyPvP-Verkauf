package eu.playcen.skypvp.commands;

import eu.playcen.skypvp.main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class CMD_PvPZone implements CommandExecutor {
    private String use = "§cBitte benutze: §7/setpvphöhe - §eSetzt die Höhe; §cunterhalb §edieser kann man Kämpfen!";

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(p.hasPermission("skypvp.setpvpzone")) {
                if(cmd.getName().equalsIgnoreCase("setpvphöhe")) {
                    File file = new File("plugins/SkyPvP", "config.yml");
                    YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
                    if(args.length == 0) {
                        int loc = p.getLocation().getBlockY();

                        cfg.set("PvPHöhe", loc);

                        try {
                            cfg.save(file);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        p.sendMessage(Main.prefix + " §7Die PvPHöhe wurde §agesetzt!");
                    }else
                        p.sendMessage(Main.prefix + use);
                }else
                    p.sendMessage(Main.prefix + use);
            }else
                p.sendMessage(Main.prefix + Main.noperm);
        }else
            sender.sendMessage(Main.prefix + " §cNur Spieler können diesen Befehl nutzen!");
        return false;
    }
}
