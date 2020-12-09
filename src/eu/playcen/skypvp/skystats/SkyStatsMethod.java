package eu.playcen.skypvp.skystats;

import eu.playcen.skypvp.mysql.MySQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class SkyStatsMethod {

    public static boolean isUserExists(UUID uuid) {
        try {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT UUID FROM SkyStats WHERE UUID = ?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();

            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public  static void updateKills(UUID uuid, int amount, boolean remove, String playername) {
        int kills = getKills(uuid);
        int deaths = getDeaths(uuid);

        if(remove) {
            amount-=kills;
        }else {
            amount+=kills;
        }

        if(isUserExists(uuid)) {
            try {
                PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE SkyStats SET Kills = ? WHERE UUID = ?");
                ps.setInt(1, amount);
                ps.setString(2, uuid.toString());
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            try {
                PreparedStatement ps = MySQL.getConnection().prepareStatement("INSERT INTO SkyStats (UUID,playername,Kills,Deaths) VALUES (?,?,?)");
                ps.setString(1, uuid.toString());
                ps.setString(2, playername);
                ps.setInt(3, kills);
                ps.setInt(4, deaths);

                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void updateDeats(UUID uuid, int amount, boolean remove, String playername) {
        int deaths = getDeaths(uuid);
        int kills = getKills(uuid);

        if(remove) {
            amount-=deaths;
        }else {
            amount+=deaths;
        }

        if(isUserExists(uuid)) {
            try {
                PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE SkyStats SET Deaths = ? WHERE UUID = ?");
                ps.setInt(1, amount);
                ps.setString(2, uuid.toString());
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            try {
                PreparedStatement ps = MySQL.getConnection().prepareStatement("INSERT INTO SkyStats (UUID,playername,Kills,Deaths) VALUES (?,?,?,?)");
                ps.setString(1, uuid.toString());
                ps.setString(2, playername);
                ps.setInt(3, kills);
                ps.setInt(4, deaths);

                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void delete(UUID uuid) {
        if(isUserExists(uuid)) {
            try {
                PreparedStatement ps = MySQL.getConnection().prepareStatement("DELETE * FROM SkyStats WHERE UUID = ?");
                ps.setString(1, uuid.toString());
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }else {
            System.err.println("[SkyPvP] [MySQL] Der Spieler" + uuid.toString() + " ist nicht in der Datenbank eingetragen!");
        }
    }

    public static Integer getKills(UUID uuid) {
        try {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT Kills FROM SkyStats WHERE UUID = ?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                return rs.getInt("Kills");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static Integer getDeaths(UUID uuid) {
        try {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT Deaths FROM SkyStats WHERE UUID = ?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                return rs.getInt("Deaths");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
