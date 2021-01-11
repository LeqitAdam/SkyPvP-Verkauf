package eu.playcen.skypvp.methods;

import eu.playcen.skypvp.commands.CMD_Perks;
import eu.playcen.skypvp.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Perks implements Listener {

    private static ArrayList<String> keinhunger = new ArrayList<>();
    private static ArrayList<String> keinfallschaden = new ArrayList<>();


    public static void flyperk(Player p) {
        File perks = new File("plugins/SkyPvP/Perks", p.getUniqueId() + ".yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(perks);

            if(cfg.getBoolean("Fly") == false) {
                p.setAllowFlight(true);
                p.sendMessage(Main.prefix + " §ePerk §7wurde §aaktiviert!");
                cfg.set("Fly", true);

                try {
                    cfg.save(perks);
                    Bukkit.getConsoleSender().sendMessage("§c[Skypvp] §7Config wurde ohne Fehler erstellt");
                } catch (IOException e) {
                    Bukkit.getConsoleSender().sendMessage("§c[Skypvp] §4Config konnte nicht erstellt werden");
                    e.printStackTrace();
                }
                CMD_Perks.openPerksInv(p);
            }else {
                p.setAllowFlight(false);
                p.sendMessage(Main.prefix + " §ePerk §7wurde §cdeaktiviert!");
                cfg.set("Fly", false);

                CMD_Perks.openPerksInv(p);
                try {
                    cfg.save(perks);
                    Bukkit.getConsoleSender().sendMessage("§c[Skypvp] §7Config wurde ohne Fehler erstellt");
                } catch (IOException e) {
                    Bukkit.getConsoleSender().sendMessage("§c[Skypvp] §4Config konnte nicht erstellt werden");
                    e.printStackTrace();
                }
                CMD_Perks.openPerksInv(p);
            }
    }

    public static void jumpboostPerk(Player p) {
        File perks = new File("plugins/SkyPvP/Perks", p.getUniqueId() + ".yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(perks);

        if(cfg.getBoolean("JumpBoost") == false) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 999999, 1));
            p.sendMessage(Main.prefix + " §ePerk §7wurde §aaktiviert!");
            cfg.set("JumpBoost", true);

            try {
                cfg.save(perks);
                Bukkit.getConsoleSender().sendMessage("§c[Skypvp] §7Config wurde ohne Fehler erstellt");
            } catch (IOException e) {
                Bukkit.getConsoleSender().sendMessage("§c[Skypvp] §4Config konnte nicht erstellt werden");
                e.printStackTrace();
            }
            CMD_Perks.openPerksInv(p);
        }else {
            p.removePotionEffect(PotionEffectType.JUMP);
            p.sendMessage(Main.prefix + " §ePerk §7wurde §cdeaktiviert!");
            cfg.set("JumpBoost", false);

            CMD_Perks.openPerksInv(p);
            try {
                cfg.save(perks);
                Bukkit.getConsoleSender().sendMessage("§c[Skypvp] §7Config wurde ohne Fehler erstellt");
            } catch (IOException e) {
                Bukkit.getConsoleSender().sendMessage("§c[Skypvp] §4Config konnte nicht erstellt werden");
                e.printStackTrace();
            }
            CMD_Perks.openPerksInv(p);
        }
    }

    public static void speedIPerk(Player p) {
        File perks = new File("plugins/SkyPvP/Perks", p.getUniqueId() + ".yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(perks);

        if(cfg.getBoolean("Speed") == false) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 999999, 0));
            p.sendMessage(Main.prefix + " §ePerk §7wurde §aaktiviert!");
            cfg.set("Speed", true);

            try {
                cfg.save(perks);
                Bukkit.getConsoleSender().sendMessage("§c[Skypvp] §7Config wurde ohne Fehler erstellt");
            } catch (IOException e) {
                Bukkit.getConsoleSender().sendMessage("§c[Skypvp] §4Config konnte nicht erstellt werden");
                e.printStackTrace();
            }
            CMD_Perks.openPerksInv(p);
        }else {
            p.removePotionEffect(PotionEffectType.SPEED);
            p.sendMessage(Main.prefix + " §ePerk §7wurde §cdeaktiviert!");
            cfg.set("Speed", false);

            CMD_Perks.openPerksInv(p);
            try {
                cfg.save(perks);
                Bukkit.getConsoleSender().sendMessage("§c[Skypvp] §7Config wurde ohne Fehler erstellt");
            } catch (IOException e) {
                Bukkit.getConsoleSender().sendMessage("§c[Skypvp] §4Config konnte nicht erstellt werden");
                e.printStackTrace();
            }
            CMD_Perks.openPerksInv(p);
        }
    }


    public static void keinHunger(Player p) {
        File perks = new File("plugins/SkyPvP/Perks", p.getUniqueId() + ".yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(perks);


        if(cfg.getBoolean("KeinHunger") == false) {
            keinhunger.add(p.getName());
            p.sendMessage(Main.prefix + " §ePerk §7wurde §aaktiviert!");
            cfg.set("KeinHunger", true);

            try {
                cfg.save(perks);
                Bukkit.getConsoleSender().sendMessage("§c[Skypvp] §7Config wurde ohne Fehler erstellt");
            } catch (IOException e) {
                Bukkit.getConsoleSender().sendMessage("§c[Skypvp] §4Config konnte nicht erstellt werden");
                e.printStackTrace();
            }
            CMD_Perks.openPerksInv(p);
        }else {
            keinhunger.remove(p.getName());
            p.sendMessage(Main.prefix + " §ePerk §7wurde §cdeaktiviert!");
            cfg.set("KeinHunger", false);

            CMD_Perks.openPerksInv(p);
            try {
                cfg.save(perks);
                Bukkit.getConsoleSender().sendMessage("§c[Skypvp] §7Config wurde ohne Fehler erstellt");
            } catch (IOException e) {
                Bukkit.getConsoleSender().sendMessage("§c[Skypvp] §4Config konnte nicht erstellt werden");
                e.printStackTrace();
            }
            CMD_Perks.openPerksInv(p);
        }
    }

    public static void keinFallschaden(Player p) {
        File perks = new File("plugins/SkyPvP/Perks", p.getUniqueId() + ".yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(perks);


        if(cfg.getBoolean("KeinFallschaden") == false) {
           keinfallschaden.add(p.getName());
            p.sendMessage(Main.prefix + " §ePerk §7wurde §aaktiviert!");
            cfg.set("KeinFallschaden", true);

            try {
                cfg.save(perks);
                Bukkit.getConsoleSender().sendMessage("§c[Skypvp] §7Config wurde ohne Fehler erstellt");
            } catch (IOException e) {
                Bukkit.getConsoleSender().sendMessage("§c[Skypvp] §4Config konnte nicht erstellt werden");
                e.printStackTrace();
            }
            CMD_Perks.openPerksInv(p);
        }else {
            keinfallschaden.remove(p.getName());
            p.sendMessage(Main.prefix + " §ePerk §7wurde §cdeaktiviert!");
            cfg.set("KeinFallschaden", false);

            CMD_Perks.openPerksInv(p);
            try {
                cfg.save(perks);
                Bukkit.getConsoleSender().sendMessage("§c[Skypvp] §7Config wurde ohne Fehler erstellt");
            } catch (IOException e) {
                Bukkit.getConsoleSender().sendMessage("§c[Skypvp] §4Config konnte nicht erstellt werden");
                e.printStackTrace();
            }
            CMD_Perks.openPerksInv(p);
        }
    }

    @EventHandler
    public static void noHunger(FoodLevelChangeEvent event) {
        Player p = (Player) event.getEntity();
        File perks = new File("plugins/SkyPvP/Perks", p.getUniqueId() + ".yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(perks);
        if(cfg.getBoolean("KeinHunger") == true) {
            event.setCancelled(true);
        }else if(cfg.getBoolean("KeinHunger") == false)
            event.setCancelled(false);
    }

    @EventHandler
    public static void noFallDamage(EntityDamageEvent event) {
        if(event.getEntity() instanceof Player){
            Player p = (Player) event.getEntity();
            if(event.getEntity().getType() == EntityType.PLAYER && event.getCause() == EntityDamageEvent.DamageCause.FALL){
                File perks = new File("plugins/SkyPvP/Perks", p.getUniqueId() + ".yml");
                YamlConfiguration cfg = YamlConfiguration.loadConfiguration(perks);
                if(cfg.getBoolean("KeinFallschaden") == true) {
                    event.setCancelled(true);
                }else if(cfg.getBoolean("KeinFallschaden") == false)
                    event.setCancelled(false);
            }
        }
    }
}
