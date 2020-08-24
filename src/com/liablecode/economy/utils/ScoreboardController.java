package com.liablecode.economy.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import com.liablecode.economy.main.Main;

public class ScoreboardController {
	private static Plugin plugin = Main.getPlugin(Main.class);
	private static Main dataP = Main.getPlugin(Main.class);
	private static Main getdatap() {
		return dataP;
	}
	public static Scoreboard getBoard(Player player) {
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective o = board.registerNewObjective("board", "dummy");
        o.setDisplayName(Utils.chat(plugin.getConfig().getString("scoreboardDisplayName")));
        o.setDisplaySlot(DisplaySlot.SIDEBAR);
        Score topCover = o.getScore(Utils.chat("&7&m--------------------&7"));
        Score info = o.getScore(Utils.chat("&b&lInfo:"));
        Score username = o.getScore("__");
        if(Utils.chat("    &6Username: &6" + player.getDisplayName()).length() < 40) {
        	username = o.getScore(Utils.chat("    &6Username: &6" + player.getDisplayName()));
        } else {
        	username = o.getScore(Utils.chat("    &6Username: &6" + player.getName()));
        }
        
        Score balance = o.getScore(Utils.chat("    &fBalance: &a$" + getdatap().data.getConfig().getInt("money." + player.getUniqueId().toString())));
        Score online = o.getScore(Utils.chat("    &bOnline: &a" + Bukkit.getServer().getOnlinePlayers().size()));
        Score rank = o.getScore(Utils.chat("    &eRank: &a" + getdatap().api.getUserManager().getUser(player.getUniqueId()).getPrimaryGroup()));
        Score middleCover1 = o.getScore("   ");
        Score store = o.getScore(Utils.chat(plugin.getConfig().getString("storepage")));
        Score middleCover2 = o.getScore("    ");
        Score website = o.getScore(Utils.chat(plugin.getConfig().getString("website")));
        Score bottomCover = o.getScore(Utils.chat("&7&m-------------------"));
        Score stats = o.getScore(Utils.chat("&d&lStats:"));
        Score kills = o.getScore(Utils.chat("    Kills: &a" + getdatap().data.getConfig().getInt("kills." + player.getUniqueId().toString())));
        Score deaths = o.getScore(Utils.chat("    Deaths: &c" + getdatap().data.getConfig().getInt("deaths." + player.getUniqueId().toString())));
        Score kd = o.getScore(Utils.chat("    Kdr: &e" + (float) getdatap().data.getConfig().getDouble("kd." + player.getUniqueId().toString())));
        topCover.setScore(15);
        info.setScore(14);
        username.setScore(13);
        balance.setScore(12);
        online.setScore(11);
        rank.setScore(10);
        stats.setScore(9);
        kills.setScore(8);
        deaths.setScore(7);
        kd.setScore(6);
        middleCover1.setScore(5);
        store.setScore(4);
        middleCover2.setScore(3);
        website.setScore(2);
        bottomCover.setScore(1);
        return board;
    }
}
