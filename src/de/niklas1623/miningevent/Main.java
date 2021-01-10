package de.niklas1623.miningevent;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import de.niklas1623.miningevent.commands.MiningEventCommand;
import de.niklas1623.miningevent.commands.OwnPointsCommand;
import de.niklas1623.miningevent.commands.ScoreBoardCommand;
import de.niklas1623.miningevent.commands.TopCommand;
import de.niklas1623.miningevent.database.MySQL;
import de.niklas1623.miningevent.listeners.BlockBreak;
import de.niklas1623.miningevent.listeners.BlockPlace;
import de.niklas1623.miningevent.listeners.JoinListener;
import de.niklas1623.miningevent.util.ConfigManager;

public class Main extends JavaPlugin {

	@Override
	public void onEnable() {
		instance = this;
		saveDefaultConfig();
		ConfigManager.setDefaultStateConfig();
		ConfigManager.readConfig();
		ConfigManager.readStateConfig();
		registerEvents();
		registerCommands();
		MySQL.connect();
		MySQL.createTable();
		Bukkit.getConsoleSender().sendMessage(prefix + " Plugin wurde §ageladen§r!");

	}

	@Override
	public void onDisable() {
		MySQL.close();
		Bukkit.getConsoleSender().sendMessage(prefix + " §cPlugin wurde gestoppt!");
	}

	private void registerEvents() {
		this.getServer().getPluginManager().registerEvents(new BlockBreak(), this);
		this.getServer().getPluginManager().registerEvents(new BlockPlace(), this);
		this.getServer().getPluginManager().registerEvents(new JoinListener(), this);
	}

	private void registerCommands() {
		OwnPointsCommand opCMD = new OwnPointsCommand(this);
		getCommand("punkte").setExecutor(opCMD);
		TopCommand tCMD = new TopCommand(this);
		getCommand("miningtop").setExecutor(tCMD);
		MiningEventCommand meCMD = new MiningEventCommand(this);
		getCommand("miningevent").setExecutor(meCMD);
		ScoreBoardCommand sbCMD = new ScoreBoardCommand(this);
		getCommand("mininglist").setExecutor(sbCMD);
	}

	public static Main instance;
	public boolean eventState;
	public String prefix;
	public String playerpoints;
	public int maxTopPlayer;
	public String keinepunkte;
	public String TopPlayerMessage;

	public static Main getInstace() {
		return instance;

	}

}
