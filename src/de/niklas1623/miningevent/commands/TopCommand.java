package de.niklas1623.miningevent.commands;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.mojang.datafixers.util.Pair;

import de.niklas1623.miningevent.Main;
import de.niklas1623.miningevent.util.MiningEventManager;

public class TopCommand implements CommandExecutor {

	private String prefix = Main.getInstace().prefix;

	public TopCommand(Main main) {
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		ArrayList<Pair<String, Integer>> list = MiningEventManager.getTop();
		int place = 0;
		int listsize = list.size();
		if (cmd.getName().equalsIgnoreCase("miningtop")) {
			if (sender.hasPermission("miningevent.user") || sender.hasPermission("miningevent.*")) {
				if (args.length == 0) {
					if (listsize == 0) {
						sender.sendMessage(prefix + " §7Es hat noch keiner gefarmt!");
						return true;
					} else {
						if (listsize < Main.getInstace().maxTopPlayer) {
							sender.sendMessage(prefix + " §7---------- §6§lTOP " + listsize + " - Liste §7----------");
							for (Pair<String, Integer> top : list) {
								place = place + 1;
								sender.sendMessage(Main.getInstace().TopPlayerMessage.replaceAll("%PREFIX%", prefix)
										.replaceAll("%PLACE%", place + "").replaceAll("%PLAYER%", top.getFirst())
										.replaceAll("%PUNKTE%", top.getSecond() + ""));
							}
						} else {
							sender.sendMessage(prefix + " §7---------- §6§lTOP " + Main.getInstace().maxTopPlayer
									+ " - Liste §7----------");
							for (Pair<String, Integer> top : list) {
								place = place + 1;
								sender.sendMessage(Main.getInstace().TopPlayerMessage.replaceAll("%PREFIX%", prefix)
										.replaceAll("%PLACE%", place + "").replaceAll("%PLAYER%", top.getFirst())
										.replaceAll("%PUNKTE%", top.getSecond() + ""));

							}
						}
					}

				} else {
					sender.sendMessage(Main.getInstace().prefix + " §7Nutze /miningtop");
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
