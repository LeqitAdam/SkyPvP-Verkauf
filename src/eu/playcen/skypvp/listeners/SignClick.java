package eu.playcen.skypvp.listeners;

import eu.playcen.skypvp.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.File;

public class SignClick implements Listener {

    @EventHandler
    public void onSignClick(PlayerInteractEvent e){
        File config = new File("plugins/SkyPvP", "config.yml");
        YamlConfiguration conf = YamlConfiguration.loadConfiguration(config);

        String prefix = conf.getString("Prefix");
        prefix = ChatColor.translateAlternateColorCodes('&', prefix);

        Player p = e.getPlayer();
        if(e.getClickedBlock() != null){
            if(e.getClickedBlock().getType() == Material.SIGN || e.getClickedBlock().getType() == Material.SIGN_POST || e.getClickedBlock().getType() == Material.WALL_SIGN){
                if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
                    Sign sign = (Sign) e.getClickedBlock().getState();
                    if(sign.getLine(3).equalsIgnoreCase("[FREE]")){
                        try{
                            ItemStack i = new ItemStack(Material.valueOf(sign.getLine(1).toUpperCase()));
                            openSignInv(p, i);
                        } catch (Exception exception){
                            e.getClickedBlock().setType(Material.AIR);
                            p.sendMessage(prefix + "§cFalsches Item Angegeben");
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void changeSign(SignChangeEvent e){
        File config = new File("plugins/SkyPvP", "config.yml");
        YamlConfiguration conf = YamlConfiguration.loadConfiguration(config);

        String prefix = conf.getString("Prefix");
        prefix = ChatColor.translateAlternateColorCodes('&', prefix);

        if(e.getLine(0).equalsIgnoreCase("[skypvp]")){
            if(!e.getPlayer().hasPermission("skypvp.sign.place")){
                e.getBlock().setType(Material.AIR);
                e.getPlayer().sendMessage(prefix + Main.noperm);
            }
        }
    }

    public void openSignInv(Player p, ItemStack i){
        final Inventory inv = Bukkit.createInventory(null, 3*9, "§bSign");
        i.setAmount(i.getMaxStackSize());
        for(int j = 0; j < inv.getSize(); j++){
            inv.setItem(j, i);
        }
        p.openInventory(inv);
    }
}
