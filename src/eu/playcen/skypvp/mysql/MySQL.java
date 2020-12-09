package eu.playcen.skypvp.mysql;

import java.sql.*;

public class MySQL {

    public static String host;
    public static String port;
    public static String database;
    public static String username;
    public static String password;
    public static Connection con;

    public static void connect() {
        if(!isConnected()) {
            try {
                con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
                System.out.println("[SkyPvP] [MySQL] Verbindung hergestellt!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void  disconnect() {
        if(isConnected()) {
            try {
                con.close();
                System.out.println("[SkyPvP] [MySQL] Verbindung getrennt!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean isConnected() {
        return (con == null ? false : true);
    }

    public static void update(String qry) {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(qry);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static ResultSet getResult(String qry) {
        try {
            PreparedStatement ps = con.prepareStatement(qry);
            return ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
