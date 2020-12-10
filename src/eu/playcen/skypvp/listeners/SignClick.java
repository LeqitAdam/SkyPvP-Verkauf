package eu.playcen.skypvp.listeners;

import eu.playcen.skypvp.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class SignClick implements Listener {

    @EventHandler
    public void onSignClick(PlayerInteractEvent e){
        Player p = e.getPlayer();
        if(e.getClickedBlock() != null){
            if(e.getClickedBlock().getType() == Material.SIGN || e.getClickedBlock().getType() == Material.SIGN_POST || e.getClickedBlock().getType() == Material.WALL_SIGN){
                if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
                    Sign sign = (Sign) e.getClickedBlock().getState();
                    if(sign.getLine(0).equals("§aSkyPvP")){
                        try{
                            String line1 = sign.getLine(1);
                            int id = 0;
                            int subid = 0;
                            if (line1.contains(":")) {
                                String[] parts = line1.split(":");
                                id = Integer.parseInt(parts[0]);
                                subid = Integer.parseInt(parts[1]);
                            } else {
                                id = Integer.parseInt(line1);
                            }
                            ItemStack i = new ItemStack(id, Integer.parseInt(sign.getLine(2)), (short) subid);
                            openSignInv(p, i);
                        } catch (Exception exception){
                            e.getClickedBlock().setType(Material.AIR);
                            p.sendMessage(Main.prefix + " §cFalsche Angabe");
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void changeSign(SignChangeEvent e){
        if(e.getLine(0).equalsIgnoreCase("[skypvp]")){
            if(!e.getPlayer().hasPermission("skypvp.sign.place")){
                e.getBlock().setType(Material.AIR);
                e.getPlayer().sendMessage(Main.prefix + Main.noperm);
            } else{
                e.setLine(0, "§aSkyPvP");
                e.setLine(1, e.getLine(1).toUpperCase());
                e.setLine(3, "§2[FREE]");
            }
        }
    }

    public void openSignInv(Player p, ItemStack i){
        final Inventory inv = Bukkit.createInventory(null, 9, "§aFree-Sign");
        for(int j = 0; j < inv.getSize(); j++){
            inv.setItem(j, i);
        }
        p.openInventory(inv);
    }
}
