package eu.playcen.skypvp.methods;

import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

public class CreateVillager {

    public void setVillager(Player p, String displayName){
        Location loc = new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ());
        Float yaw = p.getLocation().getYaw();
        Float pitch = p.getLocation().getPitch();

        loc.setYaw(yaw);
        loc.setPitch(pitch);
        loc.setDirection(p.getLocation().getDirection());


        Villager shop = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);


        shop.teleport(loc);
        shop.setCustomName(displayName);
        shop.setCanPickupItems(false);
        shop.setRemoveWhenFarAway(false);
        shop.setProfession(Villager.Profession.FARMER);
        removeAI(shop);
        shop.setAdult();
        shop.setCanPickupItems(false);
    }

    public static void removeAI(LivingEntity entity) {
        net.minecraft.server.v1_8_R3.Entity nmsEn = ((CraftEntity) entity).getHandle();
        NBTTagCompound compound = new NBTTagCompound();
        nmsEn.c(compound);
        compound.setByte("NoAI", (byte) 1);
        nmsEn.f(compound);
    }
}
