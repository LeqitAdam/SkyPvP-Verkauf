package eu.playcen.skypvp.commands;

import eu.playcen.skypvp.methods.CreateVillager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_SetVillagerKit implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(args.length == 1){
                if(args[0].equalsIgnoreCase("kits") || args[0].equalsIgnoreCase("kit")){
                    CreateVillager cv = new CreateVillager();
                    cv.setVillager(p, 27, "test", "test");
                }
            }
        }
        return false;
    }
}
