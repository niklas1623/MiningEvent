package de.niklas1623.miningevent.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.niklas1623.miningevent.Main;
import de.niklas1623.miningevent.util.MiningEventManager;

public class JoinListener implements Listener {
	
	@EventHandler
	public static void onJoin(PlayerJoinEvent e) {
		String playername = e.getPlayer().getName();
		String uuid = e.getPlayer().getUniqueId().toString();
		
		Bukkit.getScheduler().runTaskAsynchronously(Main.getInstace(), new Runnable() {
			
			@Override
			public void run() {
				if(MiningEventManager.getPlayerID(uuid) == 0) {
					MiningEventManager.insertPlayer(playername, uuid);
				}else {
					MiningEventManager.updatePlayer(playername, uuid);
				}
				
			}
		});
	}

}
