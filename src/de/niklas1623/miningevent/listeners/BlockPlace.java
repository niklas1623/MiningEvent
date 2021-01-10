package de.niklas1623.miningevent.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import de.niklas1623.miningevent.Main;
import de.niklas1623.miningevent.util.ConfigManager;
import de.niklas1623.miningevent.util.MiningEventManager;

public class BlockPlace implements Listener {

	@EventHandler
	public static void onBlockPlace(BlockPlaceEvent e) {
		Material mat = e.getBlock().getType();
		String world = e.getBlock().getWorld().getName();
		int x = e.getBlock().getX();
		int y = e.getBlock().getY();
		int z = e.getBlock().getZ();
		String location = world + ":" + x + ":" + y + ":" + z;
		String block = mat.name().toString();

		Bukkit.getScheduler().runTaskAsynchronously(Main.getInstace(), new Runnable() {

			@Override
			public void run() {
				if (!(ConfigManager.getEventState() == false)) {
					if (ConfigManager.getConfigFileConfiguration().getString("Settings.Ore." + block) == null) {
						return;
					}
					if (MiningEventManager.getOreID(block) == 0) {
						MiningEventManager.setOreID(block);
						MiningEventManager.placedBlock(MiningEventManager.getOreID(block), location);
					} else {
						int OreID = MiningEventManager.getOreID(block);
						MiningEventManager.placedBlock(OreID, location);
					}

				}
				return;

			}

		});

	}

}
