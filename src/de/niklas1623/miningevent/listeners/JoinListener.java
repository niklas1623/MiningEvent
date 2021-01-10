package de.niklas1623.miningevent.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.niklas1623.miningevent.util.MiningEventManager;

public class JoinListener implements Listener {
	
	@EventHandler
	public static void onJoin(PlayerJoinEvent e) {
		String playername = e.getPlayer().getName();
		String uuid = e.getPlayer().getUniqueId().toString();
		MiningEventManager.insertPlayer(playername, uuid);
	}

}
