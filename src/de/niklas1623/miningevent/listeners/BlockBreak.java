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
				if (!(ConfigManager.getEventState() == false)) {
					if (ConfigManager.YamlConfig.getString("Settings.Ore." + block) == null) {
						return;
					}
					if (MiningEventManager.getOreID(block) == 0) {
						MiningEventManager.setOreID(block);
					}
					int OreID = MiningEventManager.getOreID(block);
					if (MiningEventManager.checkPlacedBlock(OreID, location)) {
						return;
					}
					int PlayerID = MiningEventManager.getPlayerID(uuid);
					MiningEventManager.setOreAmount(PlayerID, OreID, 1);
					MiningEventManager.setPunkte(PlayerID, ConfigManager.getBlockPunkte(block));

				}
				return;

			}
		});

	}
}
