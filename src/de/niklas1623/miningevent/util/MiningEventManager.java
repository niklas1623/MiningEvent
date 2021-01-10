package de.niklas1623.miningevent.util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mojang.datafixers.util.Pair;

import de.niklas1623.miningevent.Main;
import de.niklas1623.miningevent.database.MySQL;

public class MiningEventManager {

//	public static void BlockBreak(String uuid, String playername, String mat, int anzahl) {
//		try {
//			String blockbreak = "INSERT INTO miningevent_ores(spielername, uuid, blockname, anzahl) VALUES (?, ?, ?, ?) ON DUPLICATE KEY UPDATE anzahl=anzahl+1";
//			PreparedStatement ps_blockbreak = MySQL.con.prepareStatement(blockbreak);
//			ps_blockbreak.setString(1, playername);
//			ps_blockbreak.setString(2, uuid);
//			ps_blockbreak.setString(3, mat);
//			ps_blockbreak.setInt(4, anzahl);
//
//			ps_blockbreak.execute();
//			ps_blockbreak.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public static int getAnzahl(String uuid) {
//		try {
//			String getAnzahl = "SELECT SUM(anzahl) FROM miningevent_ores WHERE uuid = ?";
//			PreparedStatement ps_getAnzahl = MySQL.con.prepareStatement(getAnzahl);
//			ps_getAnzahl.setString(1, uuid);
//			ResultSet rs = ps_getAnzahl.executeQuery();
//			while (rs.next()) {
//				return rs.getInt(1);
//			}
//			rs.close();
//			ps_getAnzahl.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return 0;
//
//	}
//
//	/**
//	 * MySQL Datenbank Tabelle Pluginname_placedblock.
//	 * 
//	 */
//	public static void BlockPlace(String location, String mat) {
//		try {
//			String blockplace = "INSERT INTO miningevent_placedblock(location, blockname) VALUES (?, ?)";
//			PreparedStatement ps_blockplace = MySQL.con.prepareStatement(blockplace);
//			ps_blockplace.setString(1, location);
//			ps_blockplace.setString(2, mat);
//
//			ps_blockplace.execute();
//			ps_blockplace.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public static boolean checkBlock(String location, String mat) {
//		try {
//			String checkBlock = "SELECT * FROM miningevent_placedblock WHERE location = ? AND blockname = ?";
//			PreparedStatement ps_checkBlock = MySQL.con.prepareStatement(checkBlock);
//			ps_checkBlock.setString(1, location);
//			ps_checkBlock.setString(2, mat);
//			ResultSet rs = ps_checkBlock.executeQuery();
//
//			while (rs.next()) {
//				return rs.getBoolean(0);
//			}
//			rs.close();
//			ps_checkBlock.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return false;
//	}
//
//	public static void removeBlock(String location, String mat) {
//		try {
//			String removeBlock = "DELETE FROM miningevent_placedblock WHERE location = ? AND blockname = ?";
//			PreparedStatement ps_removeBlock = MySQL.con.prepareStatement(removeBlock);
//			ps_removeBlock.setString(1, location);
//			ps_removeBlock.setString(2, mat);
//
//			ps_removeBlock.execute();
//			ps_removeBlock.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * 
//	 * Punkte Datenbank!
//	 * 
//	 */
//
//	public static void setPunkte(String playername, String uuid, int punkte) {
//		try {
//			String setPunkte = "INSERT INTO miningevent_punkte(spielername, uuid, punkteanzahl) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE punkteanzahl=punkteanzahl+"
//					+ Main.getInstace().punkte;
//			PreparedStatement ps_setPunkte = MySQL.con.prepareStatement(setPunkte);
//			ps_setPunkte.setString(1, playername);
//			ps_setPunkte.setString(2, uuid);
//			ps_setPunkte.setInt(3, punkte);
//
//			ps_setPunkte.execute();
//			ps_setPunkte.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public static int getPunkte(String uuid) {
//		try {
//			String getPunkte = "SELECT punkteanzahl FROM miningevent_punkte WHERE uuid = ?";
//			PreparedStatement ps_getPunkte = MySQL.con.prepareStatement(getPunkte);
//			ps_getPunkte.setString(1, uuid);
//			ResultSet rs = ps_getPunkte.executeQuery();
//			while (rs.next()) {
//				return rs.getInt("punkteanzahl");
//			}
//			rs.close();
//			ps_getPunkte.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return 0;
//	}
//
//	public static List<String> getTop() {
//		List<String> list = new ArrayList<String>();
//		try {
//			String getTop = "SELECT spielername, punkteanzahl FROM miningevent_punkte ORDER BY punkteanzahl DESC LIMIT "
//					+ Main.getInstace().maxTopPlayer;
//			PreparedStatement ps_getTop = MySQL.con.prepareStatement(getTop);
//			ResultSet rs = ps_getTop.executeQuery();
//			while (rs.next()) {
//				list.add(rs.getString("spielername"));
//			}
//			rs.close();
//			ps_getTop.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return list;
//	}

	/**
	 * 
	 * Neue MySQL Commands
	 * 
	 */

	public static void insertPlayer(String playername, String uuid) {
		String insertPlayer = "INSERT INTO player (PlayerName, UUID) VALUES (?, ?) ON DUPLICATE KEY UPDATE PlayerName = ?";
		try {
			PreparedStatement ps_insertPlayer = MySQL.con.prepareStatement(insertPlayer);
			ps_insertPlayer.setString(1, playername);
			ps_insertPlayer.setString(2, uuid);
			ps_insertPlayer.setString(3, playername);

			ps_insertPlayer.execute();
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
		return -1;
	}

	public static void setOreID(String mat) {
		String setOreID = "INSERT INTO ores SET Material = ?";
		try {
			PreparedStatement ps = MySQL.con.prepareStatement(setOreID);
			ps.setString(1, mat);
			ps.execute();

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

			ps.execute();
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
			ps.execute();
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

	public static void setPunkte(int PlayerID, int Punkte, String mat) {
		String setPunkte = "INSERT INTO punkte (PlayerID, Punkte) VALUES (?, ?) ON DUPLICATE KEY UPDATE Punkte=Punkte+"
				+ ConfigManager.getBlockPunkte(mat);
		try {
			PreparedStatement ps = MySQL.con.prepareStatement(setPunkte);
			ps.setInt(1, PlayerID);
			ps.setInt(2, Punkte);
			ps.execute();
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
			while(rs.next()) {
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
		String getTop = "SELECT PlayerName, Punkte FROM punkte join Player using(PlayerID) ORDER BY Punkte DESC LIMIT "
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
}
