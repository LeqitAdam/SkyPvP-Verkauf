package eu.admar.skypvp.methods;

import com.google.common.collect.Lists;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.SimpleDateFormat;
import java.util.List;

public class Manager_Sign {

    private ItemStack itemStack;

    public Manager_Sign(final ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public ItemStack sign(final String name, String message) {

        message = ChatColor.translateAlternateColorCodes('&', message);
        if (!this.isSigned()) {
            this.setSigned(true);
        }
        final ItemMeta itemMeta = this.itemStack.getItemMeta();
        List<String> lore;
        if (itemMeta.getLore() == null) {
            lore = Lists.newArrayList();
        } else {
            lore = (List<String>) itemMeta.getLore();
        }
        lore.add("§7§m-----------------------------------");
        lore.add("§7" + message);
        lore.add("§7Signiert von §c" + name + " §7am §c" + this.fortmatTime(System.currentTimeMillis()));
        itemMeta.setLore(lore);
        this.itemStack.setItemMeta(itemMeta);
        return this.itemStack;
    }

    public ItemStack unSign() {
        if (this.isSigned()) {
            this.setSigned(false);
        }
        final ItemMeta itemMeta = this.itemStack.getItemMeta();
        final List<String> lore = (List<String>) itemMeta.getLore();
        for (int i = 0; i < 3; ++i) {
            lore.remove(lore.size() - 1);
        }
        itemMeta.setLore(lore);
        this.itemStack.setItemMeta(itemMeta);
        return this.itemStack;
    }

    public boolean isSigned() {
        final net.minecraft.server.v1_8_R3.ItemStack nms = CraftItemStack.asNMSCopy(this.itemStack);
        NBTTagCompound nbtTagCompound;
        if (nms.getTag() != null) {
            nbtTagCompound = nms.getTag();
        } else {
            nbtTagCompound = new NBTTagCompound();
            this.setSigned(false);
        }
        return nbtTagCompound.getBoolean("signed");
    }

    public void setSigned(final boolean signed) {
        final net.minecraft.server.v1_8_R3.ItemStack nms = CraftItemStack.asNMSCopy(this.itemStack);
        NBTTagCompound nbtTagCompound;
        if (nms.getTag() != null) {
            nbtTagCompound = nms.getTag();
        } else {
            nbtTagCompound = new NBTTagCompound();
        }
        nbtTagCompound.setBoolean("signed", signed);
        nms.setTag(nbtTagCompound);
        this.itemStack = (ItemStack) CraftItemStack.asCraftMirror(nms);
    }

    private String fortmatTime(final Long millis) {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        return simpleDateFormat.format(millis);
    }
}
