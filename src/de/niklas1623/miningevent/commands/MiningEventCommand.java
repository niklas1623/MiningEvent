package de.niklas1623.miningevent.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import de.niklas1623.miningevent.Main;
import de.niklas1623.miningevent.util.ConfigManager;
import de.niklas1623.miningevent.util.MiningEventManager;

public class MiningEventCommand implements CommandExecutor {

	public MiningEventCommand(Main main) {
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("miningevent")) {
			if (sender.hasPermission("miningevent.reload") || sender.hasPermission("miningevent.*")) {
				if (args.length == 1) {
					if (args[0].equalsIgnoreCase("reload")) {
						Main.getInstace().reloadConfig();
						ConfigManager.readConfig();
						sender.sendMessage(Main.getInstace().prefix + " §7Die Config wurde §aneugeladen!");
					} else {
						if (args[0].equalsIgnoreCase("start")) {
							if (ConfigManager.getEventState() == true) {
								sender.sendMessage(
										Main.getInstace().prefix + " §7Das Event wurde bereits §agestartet§7!");
								sender.sendMessage(Main.getInstace().prefix
										+ " §7Nutze §e/miningevent stop §7um das Event zu stoppen!");
							} else {
								ConfigManager.setEventState(true);
								sender.sendMessage(Main.getInstace().prefix + " §7Das Event wurde §agestartet§7!");
							}
						} else {
							if (args[0].equalsIgnoreCase("stop")) {
								if (ConfigManager.getEventState() == false) {
									sender.sendMessage(
											Main.getInstace().prefix + " §7Das Event ist bereits §cgestoppt§7!");
									sender.sendMessage(Main.getInstace().prefix
											+ " §7Nutze §e/miningevent start §7um das Event zu starten!");
								} else {
									ConfigManager.setEventState(false);
									sender.sendMessage(Main.getInstace().prefix + " §7Das Event wurde §cgestoppt§7!");
								}
							} else {
								if (args[0].equalsIgnoreCase("clear")) {
									sender.sendMessage(
											Main.getInstace().prefix + " §7Nutze /miningevent clear punkte/orescount");
								} else {
									sender.sendMessage(
											Main.getInstace().prefix + " §7Nutze /miningevent reload/start/stop");
								}
							}
						}
					}
				} else {
					if (args.length == 2) {
						if (args[0].equalsIgnoreCase("clear")) {
							if (args[1].equals("punkte")) {
								MiningEventManager.clearDataBase(args[1].toString().toLowerCase());
								sender.sendMessage(Main.getInstace().prefix + " §7Du hast erfolgreich die Tabelle §e"
										+ args[1].toString() + " §7geleert!");
							} else {
								if (args[1].equals("orescount")) {
									MiningEventManager.clearDataBase(args[1].toString().toLowerCase());
									sender.sendMessage(
											Main.getInstace().prefix + " §7Du hast erfolgreich die Tabelle §e"
													+ args[1].toString() + " §7geleert!");
								} else {
									sender.sendMessage(
											Main.getInstace().prefix + " §7Nutze /miningevent clear punkte/orescount");
								}

							}

						}
					} else {
						sender.sendMessage(Main.getInstace().prefix + " §7Nutze /miningevent clear punkte/orescount");
						sender.sendMessage(Main.getInstace().prefix + " §7Nutze /miningevent reload/start/stop");
					}
				}

			} else {
				sender.sendMessage(Main.getInstace().prefix + " §cDazu hast du keine Rechte!");
			}
		}

		return false;
	}

}