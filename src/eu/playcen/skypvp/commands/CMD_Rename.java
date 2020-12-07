package eu.playcen.skypvp.commands;

import eu.playcen.skypvp.main.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;

public class CMD_Rename implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;

            File config = new File("plugins/SkyPvP", "config.yml");
            YamlConfiguration conf = YamlConfiguration.loadConfiguration(config);
            String prefix = conf.getString("Prefix");
            prefix = ChatColor.translateAlternateColorCodes('&', prefix);

            if(p.hasPermission("skypvp.rename") || p.hasPermission("skypvp.*")){
                if(args.length >= 1){
                    //
                    if(p.getItemInHand().getAmount() != 0){
                        final StringBuilder stringBuilder = new StringBuilder();
                        for (final String arg : args) {
                            stringBuilder.append(arg).append(" ");
                        }
                        ItemStack itemStack = p.getItemInHand();
                        ItemMeta itemMeta = itemStack.getItemMeta();
                        String name = stringBuilder.toString();
                        name = ChatColor.translateAlternateColorCodes('&', name);
                        itemMeta.setDisplayName(name);
                        itemStack.setItemMeta(itemMeta);
                        p.setItemInHand(itemStack);
                        p.sendMessage(prefix + " §7Dein Item heißt jetzt: " + name);
                    } else
                        p.sendMessage(prefix + " §cBitte halte ein Item in deiner Hand!");

                    return true;
                } else
                    p.sendMessage(prefix + " §7Bitte benutze /rename <Name>");
            } else
                p.sendMessage(prefix + Main.noperm);
        }
        return false;
    }
}
