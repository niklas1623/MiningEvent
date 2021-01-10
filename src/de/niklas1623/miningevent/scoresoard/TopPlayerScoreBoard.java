package de.niklas1623.miningevent.scoresoard;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import com.mojang.datafixers.util.Pair;

import de.niklas1623.miningevent.Main;
import de.niklas1623.miningevent.util.MiningEventManager;
import de.niklas1623.miningevent.util.Thread_CloseSidebar;

public class TopPlayerScoreBoard {

	public static ArrayList<Thread_CloseSidebar> closed = new ArrayList<Thread_CloseSidebar>();

	public static void updateScoreBoard(Player p) {

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
					Score score = obj.getScore("§7" + place + ". &e" + top.getFirst());
					score.setScore(top.getSecond());

				}
				p.setScoreboard(board);
				Thread_CloseSidebar tcs = new Thread_CloseSidebar(p, DisplaySlot.SIDEBAR, "TopPlayer");
				tcs.runTaskLater(Main.getInstace(), (15 * 20));
				addnewThread(tcs);
			}
		});

	}

	@SuppressWarnings("unchecked")
	public static void addnewThread(Thread_CloseSidebar closed) {
		Thread_CloseSidebar thread = getThread(closed.getPlayer().getName());
		if (thread == null) {
			((List<Thread_CloseSidebar>) closed).add(closed);
		} else {
			thread.setClosed(true);
			((List<Thread_CloseSidebar>) closed).add(closed);
		}
	}

	public static Thread_CloseSidebar getThread(String Player) {
		for (int i = 0; i < closed.size(); i++) {
			if (((Thread_CloseSidebar) closed.get(i)).getPlayer().getName().equalsIgnoreCase(Player))
				return closed.get(i);
		}
		return null;

	}

}
