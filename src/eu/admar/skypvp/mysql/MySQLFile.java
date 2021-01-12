package eu.admar.skypvp.mysql;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class MySQLFile {

    public void setStandard() {

        FileConfiguration cfg = getFileConfiguration();

        cfg.options().copyDefaults(true);
        cfg.addDefault("host", "localhost");
        cfg.addDefault("port", "3306");
        cfg.addDefault("database", "datenbank");
        cfg.addDefault("username", "benutzer");
        cfg.addDefault("password", "passwort");

        try {
            cfg.save(getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File getFile() {
        return new File("plugins/SkyPvP", "mysql.yml");
    }

    private FileConfiguration getFileConfiguration() {
        return YamlConfiguration.loadConfiguration(getFile());
    }

    public void readData() {
        FileConfiguration cfg = getFileConfiguration();

        MySQL.host = cfg.getString("host");
        MySQL.port = cfg.getString("port");
        MySQL.database = cfg.getString("database");
        MySQL.username = cfg.getString("username");
        MySQL.password = cfg.getString("password");
    }
}
