package de.niklas1623.miningevent.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import de.niklas1623.miningevent.Main;
import de.niklas1623.miningevent.util.ConfigManager;
import de.niklas1623.miningevent.util.MiningEventManager;

public class BlockBreak implements Listener {

	private String block;

	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {

		Player player = e.getPlayer();
		String uuid = player.getUniqueId().toString();

		Material mat = e.getBlock().getType();
		block = mat.name().toString();

		String world = e.getBlock().getWorld().getName();
		int x = e.getBlock().getX();
		int y = e.getBlock().getY();
		int z = e.getBlock().getZ();
		String location = world + ":" + x + ":" + y + ":" + z;

		Bukkit.getScheduler().runTaskAsynchronously(Main.getInstace(), new Runnable() {

			@Override
			public void run() {

				if (ConfigManager.getConfigFileConfiguration().getString("Settings.Ore." + block) == null) {
					return;
				} else {
					if (MiningEventManager.getOreID(block) == -1) {
						MiningEventManager.setOreID(block);
						if (MiningEventManager.checkPlacedBlock(MiningEventManager.getOreID(block), location) == true) {
							return;
						} else {
							int PlayerID = MiningEventManager.getPlayerID(uuid);
							int OreID = MiningEventManager.getOreID(block);
							MiningEventManager.setOreAmount(PlayerID, OreID, 1);
							MiningEventManager.setPunkte(PlayerID, ConfigManager.getBlockPunkte(block), block);
						}
					} else {
						int OreID = MiningEventManager.getOreID(block);
						if (MiningEventManager.checkPlacedBlock(OreID, location) == true) {
							return;
						} else {
							int PlayerID = MiningEventManager.getPlayerID(uuid);
							MiningEventManager.setOreAmount(PlayerID, OreID, 1);
							MiningEventManager.setPunkte(PlayerID, ConfigManager.getBlockPunkte(block), block);
						}
					}

				}

			}
		});

	}
}
