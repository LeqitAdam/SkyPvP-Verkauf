package eu.playcen.skypvp.commands;

import eu.playcen.skypvp.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_Gamemode implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

            Player p = (Player) sender;
            if(p.hasPermission("system.gamemode")) {
                if(cmd.getName().equalsIgnoreCase("gamemode") || cmd.getName().equalsIgnoreCase("gm")) {
                    if(args.length == 1) {
                        if(sender instanceof Player) {
                            String arg = args[0];
                            int i = Integer.parseInt(arg);
                            switch (i) {
                                case 3:
                                    p.setGameMode(GameMode.SPECTATOR);
                                    p.sendMessage(Main.pre + "§7Dein Spielmodus ist nun §7[§aSPECTATOR§7]");
                                case 2:
                                    p.setGameMode(GameMode.ADVENTURE);
                                    p.sendMessage(Main.pre + "§7Dein Spielmodus ist nun §7[§aADVENTURE§7]");
                                case 1:
                                    p.setGameMode(GameMode.CREATIVE);
                                    p.sendMessage(Main.pre + "§7Dein Spielmodus ist nun §7[§aCREATIVE§7]");
                                case 0:
                                    p.setGameMode(GameMode.SURVIVAL);
                                    p.sendMessage(Main.pre + "§7Dein Spielmodus ist nun §7[§aSURVIVAL§7]");
                            }
                        }else sender.sendMessage(Main.pre +  Main.cmdp);
                    }else if(args.length == 2) {
                        Player target = Bukkit.getPlayer(args[1]);
                            if(target != null) {
                                if(!target.hasPermission("system.gamemode.others.bypass")) {
                                    if(sender.hasPermission("system.gamemode.others")) {
                                        String arg = args[0];
                                        int i = Integer.parseInt(arg);
                                        switch (i) {
                                            case 3:
                                                target.setGameMode(GameMode.SPECTATOR);
                                                target.sendMessage(Main.pre + "§7Dein Spielmodus ist nun §7[§aSPECTATOR§7]");
                                                sender.sendMessage(Main.pre + "§7Der Spieler §a" + target.getName() + " §7ist nun im §cSpectator §7Modus!");
                                            case 2:
                                                target.setGameMode(GameMode.ADVENTURE);
                                                target.sendMessage(Main.pre + "§7Dein Spielmodus ist nun §7[§aADVENTURE§7]");
                                                sender.sendMessage(Main.pre + "§7Der Spieler §a" + target.getName() + " §7ist nun im §cAbenteuer §7Modus!");
                                            case 1:
                                                target.setGameMode(GameMode.CREATIVE);
                                                target.sendMessage(Main.pre + "§7Dein Spielmodus ist nun §7[§aCREATIVE§7]");
                                                sender.sendMessage(Main.pre + "§7Der Spieler §a" + target.getName() + " §7ist nun im §cKreativ §7Modus!");
                                            case 0:
                                                target.setGameMode(GameMode.SURVIVAL);
                                                target.sendMessage(Main.pre + "§7Dein Spielmodus ist nun §7[§aSURVIVAL§7]");
                                                sender.sendMessage(Main.pre + "§7Der Spieler §a" + target.getName() + " §7ist nun im §cSurvival §7Modus!");
                                        }
                                    }else p.sendMessage(Main.pre + Main.noperm);
                            }else if(!target.hasPermission("system.gamemode.others.bypass.ignore")) {
                                    if(sender.hasPermission("system.gamemode.others")) {
                                        String arg = args[0];
                                        int i = Integer.parseInt(arg);
                                        switch (i) {
                                            case 3:
                                                target.setGameMode(GameMode.SPECTATOR);
                                                target.sendMessage(Main.pre + "§7Dein Spielmodus ist nun §7[§aSPECTATOR§7]");
                                                sender.sendMessage(Main.pre + "§7Der Spieler §a" + target.getName() + " §7ist nun im §cSpectator §7Modus!");
                                            case 2:
                                                target.setGameMode(GameMode.ADVENTURE);
                                                target.sendMessage(Main.pre + "§7Dein Spielmodus ist nun §7[§aADVENTURE§7]");
                                                sender.sendMessage(Main.pre + "§7Der Spieler §a" + target.getName() + " §7ist nun im §cAbenteuer §7Modus!");
                                            case 1:
                                                target.setGameMode(GameMode.CREATIVE);
                                                target.sendMessage(Main.pre + "§7Dein Spielmodus ist nun §7[§aCREATIVE§7]");
                                                sender.sendMessage(Main.pre + "§7Der Spieler §a" + target.getName() + " §7ist nun im §cKreativ §7Modus!");
                                            case 0:
                                                target.setGameMode(GameMode.SURVIVAL);
                                                target.sendMessage(Main.pre + "§7Dein Spielmodus ist nun §7[§aSURVIVAL§7]");
                                                sender.sendMessage(Main.pre + "§7Der Spieler §a" + target.getName() + " §7ist nun im §cSurvival §7Modus!");
                                        }
                                    }else p.sendMessage(Main.pre + Main.noperm);
                                }else if(!(target.getName().equals("LeqitSweden") || target.getName().equals("NeraxHD"))) {
                                    if(sender.hasPermission("system.gamemode.others")) {
                                        String arg = args[0];
                                        int i = Integer.parseInt(arg);
                                        switch (i) {
                                            case 3:
                                                target.setGameMode(GameMode.SPECTATOR);
                                                target.sendMessage(Main.pre + "§7Dein Spielmodus ist nun §7[§aSPECTATOR§7]");
                                                sender.sendMessage(Main.pre + "§7Der Spieler §a" + target.getName() + " §7ist nun im §cSpectator §7Modus!");
                                            case 2:
                                                target.setGameMode(GameMode.ADVENTURE);
                                                target.sendMessage(Main.pre + "§7Dein Spielmodus ist nun §7[§aADVENTURE§7]");
                                                sender.sendMessage(Main.pre + "§7Der Spieler §a" + target.getName() + " §7ist nun im §cAbenteuer §7Modus!");
                                            case 1:
                                                target.setGameMode(GameMode.CREATIVE);
                                                target.sendMessage(Main.pre + "§7Dein Spielmodus ist nun §7[§aCREATIVE§7]");
                                                sender.sendMessage(Main.pre + "§7Der Spieler §a" + target.getName() + " §7ist nun im §cKreativ §7Modus!");
                                            case 0:
                                                target.setGameMode(GameMode.SURVIVAL);
                                                target.sendMessage(Main.pre + "§7Dein Spielmodus ist nun §7[§aSURVIVAL§7]");
                                                sender.sendMessage(Main.pre + "§7Der Spieler §a" + target.getName() + " §7ist nun im §cSurvival §7Modus!");
                                        }
                                    }else p.sendMessage(Main.pre + Main.noperm);
                                }else if(sender.getName().equals("LeqitSweden")) {
                                        String arg = args[0];
                                        int i = Integer.parseInt(arg);
                                        switch (i) {
                                            case 3:
                                                target.setGameMode(GameMode.SPECTATOR);
                                                target.sendMessage(Main.pre + "§7Dein Spielmodus ist nun §7[§aSPECTATOR§7]");
                                                sender.sendMessage(Main.pre + "§7Der Spieler §a" + target.getName() + " §7ist nun im §cSpectator §7Modus!");
                                            case 2:
                                                target.setGameMode(GameMode.ADVENTURE);
                                                target.sendMessage(Main.pre + "§7Dein Spielmodus ist nun §7[§aADVENTURE§7]");
                                                sender.sendMessage(Main.pre + "§7Der Spieler §a" + target.getName() + " §7ist nun im §cAbenteuer §7Modus!");
                                            case 1:
                                                target.setGameMode(GameMode.CREATIVE);
                                                target.sendMessage(Main.pre + "§7Dein Spielmodus ist nun §7[§aCREATIVE§7]");
                                                sender.sendMessage(Main.pre + "§7Der Spieler §a" + target.getName() + " §7ist nun im §cKreativ §7Modus!");
                                            case 0:
                                                target.setGameMode(GameMode.SURVIVAL);
                                                target.sendMessage(Main.pre + "§7Dein Spielmodus ist nun §7[§aSURVIVAL§7]");
                                                sender.sendMessage(Main.pre + "§7Der Spieler §a" + target.getName() + " §7ist nun im §cSurvival §7Modus!");
                                        }
                                }else sender.sendMessage(Main.pre + "§7Du darfst den Spielmodus von §a" + args[1] + " §cnicht verändern!");
                        }else p.sendMessage(Main.pre + "Der Spieler §a" + args[1] + " §7ist §cnicht online!");
                    }else p.sendMessage(Main.pre + "§cBitte benutze: §7/gm [0, 1, 2, 3]");
                }else p.sendMessage(Main.pre + "§cBitte benutze: §7/gm [0, 1, 2, 3]");
            }else p.sendMessage(Main.pre + Main.noperm);
        return false;
    }
}