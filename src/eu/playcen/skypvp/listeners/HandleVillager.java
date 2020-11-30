package eu.playcen.skypvp.listeners;

import eu.playcen.skypvp.commands.CMD_Kit;
import org.bukkit.Material;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

public class HandleVillager implements Listener {

    @EventHandler
    public void handleShopInteract(PlayerInteractEntityEvent e){
        if(!(e.getRightClicked() instanceof Villager)) return;
        Villager v = (Villager) e.getRightClicked();
        if(v.getCustomName().equals("test")){
            e.setCancelled(true);
            if(!e.getPlayer().getInventory().getItemInHand().equals(new ItemStack(Material.LAVA_BUCKET))){
                CMD_Kit.kitinventory(e.getPlayer());
            }
        }
    }

    @EventHandler
    public void handleShopDamage(EntityDamageEvent e){
        if(!(e.getEntity() instanceof Villager)) return;

        Villager v = (Villager) e.getEntity();
        if(v.getCustomName().equals("test"))
            e.setCancelled(true);

    }

    @EventHandler
    public void handleShopRemove(PlayerInteractEntityEvent e){
        if(!(e.getRightClicked() instanceof Villager)) return;
        Villager v = (Villager) e.getRightClicked();
        if(v.getCustomName().equals("test")){
            if(e.getPlayer().getInventory().getItemInHand().equals(new ItemStack(Material.LAVA_BUCKET))){
                v.remove();
            }
        }
    }

}
