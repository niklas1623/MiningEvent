package de.niklas1623.miningevent.util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mojang.datafixers.util.Pair;

import de.niklas1623.miningevent.Main;
import de.niklas1623.miningevent.database.MySQL;

public class MiningEventManager {

	public static void insertPlayer(String playername, String uuid) {
		String insertPlayer = "INSERT INTO player (PlayerName, UUID) VALUES (?, ?)";
		try {
			PreparedStatement ps_insertPlayer = MySQL.con.prepareStatement(insertPlayer);
			ps_insertPlayer.setString(1, playername);
			ps_insertPlayer.setString(2, uuid);

			ps_insertPlayer.executeUpdate();
			ps_insertPlayer.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static int getPlayerID(String uuid) {
		String getPlayerID = "SELECT PlayerID FROM player WHERE UUID = ?";
		try {
			PreparedStatement ps = MySQL.con.prepareStatement(getPlayerID);
			ps.setString(1, uuid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getInt("PlayerID");
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static int getOreID(String mat) {
		String getOreID = "SELECT OreID FROM ores WHERE Material = ?";
		try {
			PreparedStatement ps = MySQL.con.prepareStatement(getOreID);
			ps.setString(1, mat);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getInt("OreID");
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public static void setOreID(String mat) {
		String setOreID = "INSERT INTO ores (Material) VALUES (?)";
		try {
			PreparedStatement ps = MySQL.con.prepareStatement(setOreID);
			ps.setString(1, mat);
			ps.executeUpdate();

			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void setOreAmount(int PlayerID, int OreID, int amount) {
		String setOreAmount = "INSERT INTO orescount (PlayerID, OreID, amount) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE amount=amount+1";
		try {
			PreparedStatement ps = MySQL.con.prepareStatement(setOreAmount);
			ps.setInt(1, PlayerID);
			ps.setInt(2, OreID);
			ps.setInt(3, amount);

			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void placedBlock(int OreID, String Location) {
		String placedBlock = "INSERT INTO placedblock (OreID, Location) VALUES (?, ?)";
		try {
			PreparedStatement ps = MySQL.con.prepareStatement(placedBlock);
			ps.setInt(1, OreID);
			ps.setString(2, Location);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static boolean checkPlacedBlock(int OreID, String Location) {
		String checkPlacedBlock = "SELECT * FROM placedblock WHERE OreID = ? AND Location = ?";
		try {
			PreparedStatement ps = MySQL.con.prepareStatement(checkPlacedBlock);
			ps.setInt(1, OreID);
			ps.setString(2, Location);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return true;
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public static void setPunkte(int PlayerID, int Punkte) {
		String setPunkte = "INSERT INTO punkte (PlayerID, Punkte) VALUES (?, ?) ON DUPLICATE KEY UPDATE Punkte=Punkte+"
				+ Punkte;
		try {
			PreparedStatement ps = MySQL.con.prepareStatement(setPunkte);
			ps.setInt(1, PlayerID);
			ps.setInt(2, Punkte);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static int getPunkte(int PlayerID) {
		String getPunkte = "SELECT Punkte FROM punkte WHERE PlayerID = ?";
		try {
			PreparedStatement ps = MySQL.con.prepareStatement(getPunkte);
			ps.setInt(1, PlayerID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getInt("Punkte");
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static ArrayList<Pair<String, Integer>> getTop() {
		ArrayList<Pair<String, Integer>> list = new ArrayList<>();
		try {
			String getTop = "SELECT PlayerName, Punkte FROM punkte join player using(PlayerID) ORDER BY Punkte DESC LIMIT "
					+ Main.getInstace().maxTopPlayer;
			PreparedStatement ps_getTop = MySQL.con.prepareStatement(getTop);
			ResultSet rs = ps_getTop.executeQuery();
			while (rs.next()) {
				int Punkte = rs.getInt("Punkte");
				String Playername = rs.getString("Playername");
				list.add(new Pair<String, Integer>(Playername, Punkte));

			}
			rs.close();
			ps_getTop.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public static void clearDataBase(String name) {
		String clear = "TRUNCATE TABLE " + name;
		try {
			PreparedStatement ps = MySQL.con.prepareStatement(clear);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void updatePlayer(String playername, String uuid) {
		String updatePlayer = "UPDATE player SET PlayerName = ? WHERE UUID = ?";
		try {
			PreparedStatement ps = MySQL.con.prepareStatement(updatePlayer);
			ps.setString(1, playername);
			ps.setString(2, uuid);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
