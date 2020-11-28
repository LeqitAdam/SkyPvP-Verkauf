package eu.playcen.skypvp.commands;

import eu.playcen.skypvp.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CMD_Giveall implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(Main.pre + "§7Du musst ein Spieler sein, um den Befehl zu nutzen!");
        }
        Player p = (Player) sender;
        if (!p.hasPermission(Main.pre + "system.giveall")) {
            p.sendMessage(Main.pre + "§7Diesen §cBefehl §7gibt es nicht!");
            return true;
        }
        ItemStack hand = p.getItemInHand();
        if ((hand == null) || (hand.getType() == Material.AIR)) {
            p.sendMessage(Main.pre + "§7Du musst ein §c§lItem §7in der Hand haben!");
            return true;
        }
        int amount = hand.getAmount();
        String name = hand.getItemMeta() == null ? hand.getType().name()
                : hand.getItemMeta().getDisplayName() == null ? hand.getType().name()
                : hand.getItemMeta().getDisplayName();
        for (Player all : Bukkit.getOnlinePlayers()) {
            all.sendMessage(
                    Main.pre + "§7Jeder Spieler hat §e" + amount + "x §7mal das Item §e" + name + " §7erhalten!");
            if (all != p) {
                if (all.getInventory().firstEmpty() == -1) {
                    all.getWorld().dropItemNaturally(all.getLocation(), hand);
                } else {
                    all.getInventory().addItem(new ItemStack[] { hand });
                }
            }
        }
        return true;
    }
}
