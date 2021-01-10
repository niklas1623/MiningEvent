package de.niklas1623.miningevent.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.niklas1623.miningevent.Main;
import de.niklas1623.miningevent.util.MiningEventManager;

public class OwnPointsCommand implements CommandExecutor {

	private String prefix = Main.getInstace().prefix;

	public OwnPointsCommand(Main main) {
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("punkte")) {
			if (sender.hasPermission("miningevent.user") || sender.hasPermission("miningevent.*")) {
				if (args.length == 1) {
					String playername = args[0];
					int punkte = MiningEventManager.getPunkte(MiningEventManager.getPlayerID(getUUID(playername)));
					if (punkte == 0) {
						sender.sendMessage(prefix + " §cDieser Spieler hat noch nicht gefarmt!");
					} else {
						sender.sendMessage(Main.getInstace().playerpoints.replaceAll("%PREFIX%", prefix)
								.replaceAll("%PLAYER%", playername).replaceAll("%PUNKTE%", "" + punkte));
					}
				} else {
					if (!(args.length == 0)) {
						sender.sendMessage(Main.getInstace().prefix + " §7Nutze /punkte (<Spielername>)");
					} else {
						int punkte = MiningEventManager.getPunkte(MiningEventManager.getPlayerID(player.getUniqueId().toString()));
						if(punkte == 0) {
							sender.sendMessage(Main.getInstace().prefix+ " §7Du hast §ckeine §7Punkte!");
						}else {
							sender.sendMessage(Main.getInstace().playerpoints.replaceAll("%PREFIX%", prefix)
									.replaceAll("%PLAYER%", player.getName()).replaceAll("%PUNKTE%", "" + punkte));
						}
					}
				}
			} else {
				sender.sendMessage(Main.getInstace().prefix + " §cDazu hast du keine Rechte!");
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings("deprecation")
	private String getUUID(String playername) {
		return Bukkit.getOfflinePlayer(playername).getUniqueId().toString();
	}

}
