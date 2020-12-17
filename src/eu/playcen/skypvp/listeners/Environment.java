package eu.playcen.skypvp.listeners;

import eu.playcen.skypvp.commands.CMD_Build;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerAchievementAwardedEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class Environment implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent e){
        if(!CMD_Build.builders.contains(e.getPlayer()))
            e.setCancelled(true);
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e){
        if(!CMD_Build.builders.contains(e.getPlayer()))
            e.setCancelled(true);
    }

    @EventHandler
    public void onBlockExplode(EntityExplodeEvent e){
        if(e.getEntityType().equals(EntityType.CREEPER))
            e.setCancelled(true);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        if(!CMD_Build.builders.contains(e.getPlayer())) {
            if(e.getClickedBlock() != null){
                if (!e.getClickedBlock().getType().equals(Material.ENDER_CHEST)) {
                    e.setCancelled(true);
                }
            }

        }
    }

    @EventHandler
    public void onAchieve(PlayerAchievementAwardedEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void entityDeathEvent(EntityDeathEvent e){
        if(e.getEntity().getType() != EntityType.PLAYER)
            e.getDrops().clear();
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent e){
        e.setCancelled(true);
    }

    /*@EventHandler
    public void onGenerate(ChunkLoadEvent event) {

        Chunk chunk = event.getChunk();
        if(LocalDate.now().getMonth() == Month.DECEMBER || LocalDate.now().getMonth() == Month.JANUARY){

            for(int x = 0 ; x < 16; x++) {
                for(int z = 0 ; z < 16; z++) {
                    final Block block = chunk.getBlock(x, 0, z);
                    block.setBiome(Biome.COLD_TAIGA);
                }
            }
        } else {
            for(int x = 0 ; x < 16; x++) {
                for(int z = 0 ; z < 16; z++) {
                    final Block block = chunk.getBlock(x, 0, z);
                    block.setBiome(Biome.PLAINS);
                }
            }
        }

    }*/

}
