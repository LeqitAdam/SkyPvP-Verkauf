package eu.admar.skypvp.commands;

import eu.admar.skypvp.main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class CMD_Discord implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
            File file = new File("plugins/SkyPvP", "config.yml");
            YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
            sender.sendMessage(Main.prefix + " §7Discord: §e" + cfg.get("Discord"));
        return false;
    }
}
