package eu.admar.skypvp.skystats;

import eu.admar.skypvp.mysql.MySQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

public class SkyStatsMethod {

    public static boolean isUserExists(UUID uuid) {
        try {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT UUID FROM SkyStats WHERE UUID = ?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();

            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public  static void updateKills(UUID uuid, int amount, boolean remove, String playername) {
        int kills = getKills(uuid);

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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            try {
                PreparedStatement ps = MySQL.getConnection().prepareStatement("INSERT INTO SkyStats (UUID,playername,Kills,Deaths) VALUES (?,?,?,?)");
                ps.setString(1, uuid.toString());
                ps.setString(2, playername);
                ps.setInt(3, 0);
                ps.setInt(4, 0);

                ps.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void updateDeaths(UUID uuid, int amount, boolean remove, String playername) {
        int deaths = getDeaths(uuid);

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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            try {
                PreparedStatement ps = MySQL.getConnection().prepareStatement("INSERT INTO SkyStats (UUID,playername,Kills,Deaths) VALUES (?,?,?,?)");
                ps.setString(1, uuid.toString());
                ps.setString(2, playername);
                ps.setInt(3, 0);
                ps.setInt(4, 0);

                ps.executeUpdate();
            } catch (Exception e) {
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
            } catch (Exception e) {
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
        } catch (Exception e) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    //public static void updateDeats(UUID uniqueId, int i, boolean b, String name) {
    //}
}
