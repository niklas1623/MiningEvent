package de.niklas1623.miningevent.util;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import de.niklas1623.miningevent.Main;
import de.niklas1623.miningevent.database.MySQL;

public class ConfigManager {

	public static File getConfigFile() {
		return new File("plugins/MiningEvent", "config.yml");

	}

	public static FileConfiguration getConfigFileConfiguration() { 
		return YamlConfiguration.loadConfiguration(getConfigFile() );
	}                                                              
                                                                   
	public static File getStateFile() {                            
		return new File("plugins/MiningEvent", "EventState.yml");  
	}                                                              
                                                                   
	public static FileConfiguration getStateFileConfiguration() {  
		return YamlConfiguration.loadConfiguration(getStateFile());
	}

	public static void readConfig() {
		FileConfiguration cfg = getConfigFileConfiguration();
		Main.getInstace().prefix = ChatColor.translateAlternateColorCodes('&', cfg.getString("Prefix") + "§r");
		Main.getInstace().playerpoints = ChatColor.translateAlternateColorCodes('&',
				cfg.getString("Settings.Messages.PlayerPoints"));
		Main.getInstace().TopPlayerMessage = ChatColor.translateAlternateColorCodes('&',
				cfg.getString("Settings.Messages.TopPlayerMessage"));
		Main.getInstace().maxTopPlayer = cfg.getInt("Settings.MaxTopPlayer");
		MySQL.username = cfg.getString("Database.username");
		MySQL.password = cfg.getString("Database.password");
		MySQL.database = cfg.getString("Database.database");
		MySQL.host = cfg.getString("Database.host");
		MySQL.port = cfg.getString("Database.port");

	}

	public static void readStateConfig() {
		FileConfiguration cfg = getStateFileConfiguration();
		Main.getInstace().eventState = cfg.getBoolean("Started");
	}
	
	public static void setDefaultStateConfig() {
		FileConfiguration cfg = getStateFileConfiguration();
		cfg.addDefault("Started", false);
		cfg.options().copyDefaults(true);
		try {
			cfg.save(getStateFile());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static int getBlockPunkte(String mat) {
		FileConfiguration cfg = getConfigFileConfiguration();
		return cfg.getInt("Settings.Ore." + mat);
	}

	public static void setEventState(Boolean state) {
		FileConfiguration setState = getStateFileConfiguration();
		setState.set("Started", state);
		try {
			setState.save(getStateFile());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Boolean getEventState() {
		FileConfiguration getState = getStateFileConfiguration();
		return getState.getBoolean("Started");
	}

}
