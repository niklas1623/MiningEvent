package de.niklas1623.miningevent.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;

import de.niklas1623.miningevent.Main;

public class MySQL {

	public static String username;
	public static String password;
	public static String database;
	public static String host;
	public static String port;
	public static Connection con;

	public static void connect() {
		if (!isConnected()) {
			try {
				con = DriverManager.getConnection(
						"jdbc:mysql://" + host + ":" + port + "/" + database + "?autoReconnect=true&useSSL=false&AllowPublicKeyRetrieval=true",
						username, password);
				Bukkit.getConsoleSender().sendMessage(Main.getInstace().prefix + " MySQL Verbindung §aaufgebaut§7!");
			} catch (SQLException ex) {
				ex.printStackTrace();
				Bukkit.getConsoleSender()
						.sendMessage(Main.getInstace().prefix + " MySQL Verbindung §cfehlgeschlagen§7!");
			}
		}
	}

	public static void close() {
		if (isConnected()) {
			try {
				con.close();
				Bukkit.getConsoleSender().sendMessage(Main.getInstace().prefix + " MySQL Verbindung §cgeschlossen§r!");
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static boolean isConnected() {
		return con != null;
	}

	public static void createTable() {
		if (isConnected()) {
			try {
				con.createStatement().executeUpdate(
						"CREATE TABLE IF NOT EXISTS player (PlayerID INT NOT NULL AUTO_INCREMENT , PlayerName TEXT NOT NULL , UUID VARCHAR(100) NOT NULL , PRIMARY KEY (PlayerID), UNIQUE (UUID))");
				con.createStatement().executeUpdate(
						"CREATE TABLE IF NOT EXISTS punkte ( PlayerID INT NOT NULL , Punkte INT NOT NULL, PRIMARY KEY (PlayerID))");
				con.createStatement().executeUpdate(
						"CREATE TABLE IF NOT EXISTS orescount ( OreID INT NOT NULL , PlayerID INT NOT NULL , amount INT NOT NULL, PRIMARY KEY (OreID, PlayerID))");
				con.createStatement().executeUpdate(
						"CREATE TABLE IF NOT EXISTS placedblock ( OreID INT NOT NULL , Location TEXT NOT NULL )");
				con.createStatement().executeUpdate(
						"CREATE TABLE IF NOT EXISTS ores ( OreID INT NOT NULL AUTO_INCREMENT , Material VARCHAR(255) NOT NULL , PRIMARY KEY (OreID), UNIQUE (Material))");

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void update(String qry) {
		if (isConnected()) {
			try {
				con.createStatement().executeUpdate(qry);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static ResultSet getResult(String qry) {
		if (isConnected()) {
			try {
				return con.createStatement().executeQuery(qry);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
