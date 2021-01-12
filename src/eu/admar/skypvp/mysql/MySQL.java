package eu.admar.skypvp.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
                con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?autoReconnect=true", username, password);
            } catch (Exception ignored) { }
        }
    }

    public static void  disconnect() {
        if(isConnected()) {
            try {
                con.close();
                System.out.println("[SkyPvP] [MySQL] Verbindung getrennt!");
            } catch (SQLException ignored) {

            }
        }
    }

    public static boolean isConnected() {
        return (con != null);
    }

    public static Connection getConnection() {
        return con;
    }
}
