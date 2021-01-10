package de.niklas1623.miningevent.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.niklas1623.miningevent.Main;
import de.niklas1623.miningevent.scoresoard.TopPlayerScoreBoard;

public class ScoreBoardCommand implements CommandExecutor {

	public ScoreBoardCommand(Main main) {
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("mininglist")) {
			if (sender.hasPermission("miningevent.user") || sender.hasPermission("miningevent.*")) {
				if (args.length == 0) {
					TopPlayerScoreBoard.updateScoreBoard(p);
				} else {
					sender.sendMessage(Main.getInstace().prefix + " §7Nutze /mininglist");
					return true;
				}
			} else {
				sender.sendMessage(Main.getInstace().prefix + " §cDazu hast du keine Rechte!");
				return true;
			}
		}

		return false;
	}

}
