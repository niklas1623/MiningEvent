package de.niklas1623.miningevent.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import com.mojang.datafixers.util.Pair;

import de.niklas1623.miningevent.Main;
import de.niklas1623.miningevent.util.MiningEventManager;
import de.niklas1623.miningevent.util.Thread_CloseSidebar;

public class ScoreBoardCommand implements CommandExecutor {

	public ScoreBoardCommand(Main main) {
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("mininglist")) {
			if (sender.hasPermission("miningevent.user") || sender.hasPermission("miningevent.*")) {
				if (args.length == 0) {
					updateScoreBoard(p);
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
	
	
	public ArrayList<Thread_CloseSidebar> closed = new ArrayList<Thread_CloseSidebar>();

	public void updateScoreBoard(Player p) {

		Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
		@SuppressWarnings("deprecation")
		Objective obj = board.registerNewObjective("TopPlayer", "dummy");
		ArrayList<Pair<String, Integer>> list = MiningEventManager.getTop();

		obj.setDisplayName("§6§lTOP " + list.size() + " - Liste");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);

		Bukkit.getScheduler().runTaskAsynchronously(Main.getInstace(), new Runnable() {
			public void run() {
				int place = 0;
				for (Pair<String, Integer> top : list) { 
					place = place + 1; 
					Score score = obj.getScore("§7" + place + ". §r" + top.getFirst());
					score.setScore(top.getSecond());

				}
				p.setScoreboard(board);
				Thread_CloseSidebar tcs = new Thread_CloseSidebar(p, DisplaySlot.SIDEBAR, "TopPlayer");
				tcs.runTaskLater(Main.getInstace(), (15 * 20));
				addnewThread(tcs);

			}
		});

	}

	public void addnewThread(Thread_CloseSidebar closed) {
		Thread_CloseSidebar thread = getThread(closed.getPlayer().getName());
		if (thread == null) {
			this.closed.add(closed);
		} else {
			thread.setClosed(true);
			this.closed.add(closed);
		}
	}

	private Thread_CloseSidebar getThread(String Player) {
		for (int i = 0; i < this.closed.size(); i++) {
			if (((Thread_CloseSidebar) this.closed.get(i)).getPlayer().getName().equalsIgnoreCase(Player))
				return this.closed.get(i);
		}
		return null;

	}

}
