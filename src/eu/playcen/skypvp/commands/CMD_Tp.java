package eu.playcen.skypvp.commands;

import eu.playcen.skypvp.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class CMD_Tp implements CommandExecutor {

    private Main plugin;

    public CMD_Tp(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        File config = new File("plugins/SkyPvP", "config.yml");
        YamlConfiguration conf = YamlConfiguration.loadConfiguration(config);

        String prefix = conf.getString("Prefix");
        prefix = ChatColor.translateAlternateColorCodes('&', prefix);

        Player p = (Player) sender;
        if (p.hasPermission("skypvp.tp") || p.hasPermission("skypvp.*")) {
            if (args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);
                if (target != null) {
                    if (!(plugin.vanish.contains(target.getName()))) {
                        p.teleport(target);
                        p.sendMessage(prefix + " §7Du wurdest zu §a" + target.getName() + " §7teleportiert!");
                    } else if ((p.isOp() || p.hasPermission("system.tp.bypass"))) {
                        p.teleport(target);
                        p.sendMessage(prefix + " §7Du wurdest zu §a" + target.getName() + " §7teleportiert!");
                    } else {
                        p.sendMessage(prefix + " §7Der Spieler §a" + args[0] + " §7ist §cnicht online!");
                    }
                } else if(!p.hasPermission("system.tp.offline")){
                    p.sendMessage(prefix + " §7Der Spieler §a" + args[0] + " §7ist §cnicht online!");
                }else {
                    p.teleport(target);
                    p.sendMessage(prefix + " §7Du wurdest zu §a" + target.getName() + " §7teleportiert! (§cVanish ist §aaktiv!§7)");
                }

            } else if (args.length == 2) {
                Player target = Bukkit.getPlayer(args[0]);
                Player player = Bukkit.getPlayer(args[1]);
                if (player != null && target != null) {
                    if(!(target.hasPermission("skypvp.tp.owner") || p.hasPermission("skypvp.*"))) {
                        target.teleport(player);
                        p.sendMessage(prefix + " §7Der Spieler §a" + target.getName() + " §7wurde zu §a" + player.getName()
                                + " §7teleportiert!");
                        return true;
                    } else if(target.hasPermission("skypvp.tp.owner") && !(p.hasPermission("system.tp.owner.bypass"))) {
                        p.sendMessage(prefix + " §7Den Spieler §a" + args[0] + " §7darfst du §cnicht §7teleportieren!");
                        return true;
                    } else if(p.hasPermission("skypvp.tp.owner.bypass") || p.hasPermission("skypvp.*")) {
                        target.teleport(player);
                        p.sendMessage(prefix + " §7Der Spieler §a" + target.getName() + " §7wurde zu §a" + player.getName()
                                + " §7teleportiert!");
                        return true;
                    } else if (!(plugin.vanish.contains(target.getName()))) {
                        target.teleport(player);
                        p.sendMessage(prefix + " §7Der Spieler §a" + target.getName() + " §7wurde zu §a" + player.getName()
                                + " §7teleportiert!");
                        return true;
                    } else if ((p.isOp() || p.hasPermission("skypvp.tp.bypass") || p.hasPermission("skypvp.*"))) {
                        target.teleport(p);
                        p.sendMessage(prefix + " §7Der Spieler §a" + target.getName() + " §7wurde zu §a" + player.getName()
                                + " §7Teleportiert!");
                        return true;
                    } else if (!(p.hasPermission("skypvp.tp.offline") || p.hasPermission("skypvp.*"))){
                        p.sendMessage(prefix + " §7Der Spieler §a" + args[0] + " §7ist §cnicht online!");
                        return true;
                    }else {
                        p.teleport(target);
                        p.sendMessage(prefix + " §7Der Spieler §a" + target.getName() + " §7wurde zu §a" + player.getName()
                                + " §7teleportiert! (§cVanish ist §aaktiv!§7)");
                        return true;
                    }
                } else if (player == null && target != null) {
                    p.sendMessage(prefix + " §7Der Spieler §a" + args[0] + " §7konnte §cnicht §7zu dem Spieler §a"
                            + args[1] + " §7teleportiert werden!");
                    p.sendMessage(prefix + " §7Der Spieler §a" + args[1] + " §7ist §cnicht online!");
                } else if (target == null && player != null) {
                    p.sendMessage(prefix + " §7Der Spieler §a" + args[0] + " §7konnte §cnicht §7zu dem Spieler §a"
                            + args[1] + " §7teleportiert werden!");
                    p.sendMessage(prefix + " §7Der Spieler §a" + args[0] + " §7ist §cnicht online!");
                } else {
                    p.sendMessage(prefix + " §7Beide Spieler sind §coffline!");
                }

            } else {
                p.sendMessage(prefix + " §7Bitte benutze: /tp [Name] [Name]");
            }
        } else
            p.sendMessage(prefix + Main.noperm);

        return false;
    }
}
