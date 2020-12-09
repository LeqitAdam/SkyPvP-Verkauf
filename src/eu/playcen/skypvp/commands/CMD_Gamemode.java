package eu.playcen.skypvp.commands;

import eu.playcen.skypvp.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class CMD_Gamemode implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        File config = new File("plugins/SkyPvP", "config.yml");
        YamlConfiguration conf = YamlConfiguration.loadConfiguration(config);

        String prefix = conf.getString("Prefix");
        prefix = ChatColor.translateAlternateColorCodes('&', prefix);
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission("skypvp.gamemode") || p.hasPermission("skypvp.*")) {
                if (cmd.getName().equalsIgnoreCase("gamemode") || cmd.getName().equalsIgnoreCase("gm")) {
                    if (args.length == 1) {
                        String arg = args[0];
                        try{
                            int i = Integer.parseInt(arg);
                        } catch (Exception e){
                            p.sendMessage(prefix + " §cUnbekannter Spielmodus");
                            return false;
                        }

                        int i = Integer.parseInt(arg);

                        switch (i) {
                            case 3:
                                p.setGameMode(GameMode.SPECTATOR);
                                p.sendMessage(prefix + " §7Dein Spielmodus ist nun §7[§aSPECTATOR§7]");
                                return false;
                            case 2:
                                p.setGameMode(GameMode.ADVENTURE);
                                p.sendMessage(prefix + " §7Dein Spielmodus ist nun §7[§aADVENTURE§7]");
                                return false;
                            case 1:
                                p.setGameMode(GameMode.CREATIVE);
                                p.sendMessage(prefix + " §7Dein Spielmodus ist nun §7[§aCREATIVE§7]");
                                return false;
                            case 0:
                                p.setGameMode(GameMode.SURVIVAL);
                                p.sendMessage(prefix + " §7Dein Spielmodus ist nun §7[§aSURVIVAL§7]");
                                return false;
                            default:
                                p.sendMessage(prefix + " §cUnbekannter Spielmodus");
                                return false;
                        }
                    } else if (args.length == 2) {
                        Player target = Bukkit.getPlayer(args[1]);
                        if (target != null) {
                            if (!target.hasPermission("skypvp.gamemode.others.bypass")) {
                                if (sender.hasPermission("skypvp.gamemode.others") || sender.hasPermission("skypvp.*")) {
                                    String arg = args[0];
                                    try{
                                        int i = Integer.parseInt(arg);
                                    } catch (Exception e){
                                        p.sendMessage(prefix + " §cUnbekannter Spielmodus");
                                        return false;
                                    }

                                    int i = Integer.parseInt(arg);
                                    switch (i) {
                                        case 3:
                                            target.setGameMode(GameMode.SPECTATOR);
                                            target.sendMessage(prefix + " §7Dein Spielmodus ist nun §7[§aSPECTATOR§7]");
                                            sender.sendMessage(prefix + " §7Der Spieler §a" + target.getName() + " §7ist nun im §cSpectator §7Modus!");
                                            return false;
                                        case 2:
                                            target.setGameMode(GameMode.ADVENTURE);
                                            target.sendMessage(prefix + " §7Dein Spielmodus ist nun §7[§aADVENTURE§7]");
                                            sender.sendMessage(prefix + " §7Der Spieler §a" + target.getName() + " §7ist nun im §cAbenteuer §7Modus!");
                                            return false;
                                        case 1:
                                            target.setGameMode(GameMode.CREATIVE);
                                            target.sendMessage(prefix + " §7Dein Spielmodus ist nun §7[§aCREATIVE§7]");
                                            sender.sendMessage(prefix + " §7Der Spieler §a" + target.getName() + " §7ist nun im §cKreativ §7Modus!");
                                            return false;
                                        case 0:
                                            target.setGameMode(GameMode.SURVIVAL);
                                            target.sendMessage(prefix + " §7Dein Spielmodus ist nun §7[§aSURVIVAL§7]");
                                            sender.sendMessage(prefix + " §7Der Spieler §a" + target.getName() + " §7ist nun im §cSurvival §7Modus!");
                                            return false;
                                        default:
                                            p.sendMessage(prefix + " §cUnbekannter Spielmodus");
                                            return false;
                                    }
                                } else p.sendMessage(prefix + Main.noperm);
                            } else if (!target.hasPermission("skypvp.gamemode.others.bypass.ignore")) {
                                if (sender.hasPermission("skypvp.gamemode.others") || sender.hasPermission("skypvp.*")) {
                                    String arg = args[0];
                                    try{
                                        int i = Integer.parseInt(arg);
                                    } catch (Exception e){
                                        p.sendMessage(prefix + " §cUnbekannter Spielmodus");
                                        return false;
                                    }

                                    int i = Integer.parseInt(arg);
                                    switch (i) {
                                        case 3:
                                            target.setGameMode(GameMode.SPECTATOR);
                                            target.sendMessage(prefix + " §7Dein Spielmodus ist nun §7[§aSPECTATOR§7]");
                                            sender.sendMessage(prefix + " §7Der Spieler §a" + target.getName() + " §7ist nun im §cSpectator §7Modus!");
                                            return false;
                                        case 2:
                                            target.setGameMode(GameMode.ADVENTURE);
                                            target.sendMessage(prefix + " §7Dein Spielmodus ist nun §7[§aADVENTURE§7]");
                                            sender.sendMessage(prefix + " §7Der Spieler §a" + target.getName() + " §7ist nun im §cAbenteuer §7Modus!");
                                            return false;
                                        case 1:
                                            target.setGameMode(GameMode.CREATIVE);
                                            target.sendMessage(prefix + " §7Dein Spielmodus ist nun §7[§aCREATIVE§7]");
                                            sender.sendMessage(prefix + " §7Der Spieler §a" + target.getName() + " §7ist nun im §cKreativ §7Modus!");
                                            return false;
                                        case 0:
                                            target.setGameMode(GameMode.SURVIVAL);
                                            target.sendMessage(prefix + " §7Dein Spielmodus ist nun §7[§aSURVIVAL§7]");
                                            sender.sendMessage(prefix + " §7Der Spieler §a" + target.getName() + " §7ist nun im §cSurvival §7Modus!");
                                            return false;
                                        default:
                                            p.sendMessage(prefix + " §cUnbekannter Spielmodus");
                                            return false;
                                    }
                                } else p.sendMessage(prefix + Main.noperm);
                            } else if (!(target.getName().equals("LeqitSweden") || target.getName().equals("NeraxHD"))) {
                                if (sender.hasPermission("skypvp.gamemode.others") || sender.hasPermission("skypvp.*")) {
                                    String arg = args[0];
                                    try{
                                        int i = Integer.parseInt(arg);
                                    } catch (Exception e){
                                        p.sendMessage(prefix + " §cUnbekannter Spielmodus");
                                        return false;
                                    }

                                    int i = Integer.parseInt(arg);
                                    switch (i) {
                                        case 3:
                                            target.setGameMode(GameMode.SPECTATOR);
                                            target.sendMessage(prefix + " §7Dein Spielmodus ist nun §7[§aSPECTATOR§7]");
                                            sender.sendMessage(prefix + " §7Der Spieler §a" + target.getName() + " §7ist nun im §cSpectator §7Modus!");
                                            return false;
                                        case 2:
                                            target.setGameMode(GameMode.ADVENTURE);
                                            target.sendMessage(prefix + " §7Dein Spielmodus ist nun §7[§aADVENTURE§7]");
                                            sender.sendMessage(prefix + " §7Der Spieler §a" + target.getName() + " §7ist nun im §cAbenteuer §7Modus!");
                                            return false;
                                        case 1:
                                            target.setGameMode(GameMode.CREATIVE);
                                            target.sendMessage(prefix + " §7Dein Spielmodus ist nun §7[§aCREATIVE§7]");
                                            sender.sendMessage(prefix + " §7Der Spieler §a" + target.getName() + " §7ist nun im §cKreativ §7Modus!");
                                            return false;
                                        case 0:
                                            target.setGameMode(GameMode.SURVIVAL);
                                            target.sendMessage(prefix + " §7Dein Spielmodus ist nun §7[§aSURVIVAL§7]");
                                            sender.sendMessage(prefix + " §7Der Spieler §a" + target.getName() + " §7ist nun im §cSurvival §7Modus!");
                                            return false;
                                        default:
                                            p.sendMessage(prefix + " §cUnbekannter Spielmodus");
                                            return false;
                                    }
                                } else p.sendMessage(prefix + Main.noperm);
                            } else if (sender.getName().equals("LeqitSweden")) {
                                String arg = args[0];
                                try{
                                    int i = Integer.parseInt(arg);
                                } catch (Exception e){
                                    p.sendMessage(prefix + " §cUnbekannter Spielmodus");
                                    return false;
                                }

                                int i = Integer.parseInt(arg);
                                switch (i) {
                                    case 3:
                                        target.setGameMode(GameMode.SPECTATOR);
                                        target.sendMessage(prefix + " §7Dein Spielmodus ist nun §7[§aSPECTATOR§7]");
                                        sender.sendMessage(prefix + " §7Der Spieler §a" + target.getName() + " §7ist nun im §cSpectator §7Modus!");
                                        return false;
                                    case 2:
                                        target.setGameMode(GameMode.ADVENTURE);
                                        target.sendMessage(prefix + " §7Dein Spielmodus ist nun §7[§aADVENTURE§7]");
                                        sender.sendMessage(prefix + " §7Der Spieler §a" + target.getName() + " §7ist nun im §cAbenteuer §7Modus!");
                                        return false;
                                    case 1:
                                        target.setGameMode(GameMode.CREATIVE);
                                        target.sendMessage(prefix + " §7Dein Spielmodus ist nun §7[§aCREATIVE§7]");
                                        sender.sendMessage(prefix + " §7Der Spieler §a" + target.getName() + " §7ist nun im §cKreativ §7Modus!");
                                        return false;
                                    case 0:
                                        target.setGameMode(GameMode.SURVIVAL);
                                        target.sendMessage(prefix + " §7Dein Spielmodus ist nun §7[§aSURVIVAL§7]");
                                        sender.sendMessage(prefix + " §7Der Spieler §a" + target.getName() + " §7ist nun im §cSurvival §7Modus!");
                                        return false;
                                    default:
                                        p.sendMessage(prefix + " §cUnbekannter Spielmodus");
                                        return false;
                                }
                            } else
                                sender.sendMessage(prefix + " §7Du darfst den Spielmodus von §a" + args[1] + " §cnicht verändern!");
                        } else p.sendMessage(prefix + " §7Der Spieler §a" + args[1] + " §7ist §cnicht online!");
                    } else p.sendMessage(prefix + " §cBitte benutze: §7/gm <0, 1, 2, 3>");
                } else p.sendMessage(prefix + " §cBitte benutze: §7/gm <0, 1, 2, 3>");
            } else p.sendMessage(prefix + Main.noperm);
        }
        return false;
    }
}
