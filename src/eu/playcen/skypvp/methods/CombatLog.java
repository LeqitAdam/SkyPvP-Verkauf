package eu.playcen.skypvp.methods;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CombatLog {

    public static Map<UUID, Integer> combatlog = new HashMap<UUID, Integer>();

    public static void sendActionbar(Player p,String msg) {
        IChatBaseComponent cbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + msg + "\"}");
        PacketPlayOutChat packet = new PacketPlayOutChat(cbc, (byte) 2);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
    }
}
