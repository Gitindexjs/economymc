package com.liablecode.economy.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.DisplaySlot;

import com.liablecode.economy.main.Main;
import com.liablecode.economy.utils.ScoreboardController;
import com.liablecode.economy.utils.Utils;

public class togglescoreboard implements CommandExecutor {
	@SuppressWarnings("unused")
	private Main plugin;
	private static Plugin pluginA = Main.getPlugin(Main.class);
	public togglescoreboard(Main plugin){
		this.plugin = plugin;
		plugin.getCommand("togglescoreboard").setExecutor(this);
	}
	
	private static boolean toggled = pluginA.getConfig().getBoolean("startWithScoreboard");
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!sender.hasPermission("scoreboard.toggle")) {
			sender.sendMessage(Utils.chat("&cYou do not have permission to execute this command."));
			return false;
		}
		if(toggled) {
			toggled = false;
			if(Bukkit.getOnlinePlayers() == null) return false;
	        if(Bukkit.getOnlinePlayers().size() == 0) return false;
			for(Player p : Bukkit.getOnlinePlayers()) {
				p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
			}
			sender.sendMessage(Utils.chat("&aSuccessfully removed scoreboard"));
			return true;
		} else {
			toggled = true;
			for(Player p : Bukkit.getOnlinePlayers()) {
				p.setScoreboard(ScoreboardController.getBoard(p));
			}
			sender.sendMessage(Utils.chat("&aSuccessfully activated scoreboard"));
			return true;
		}
	}
	public static boolean getToggled() {
		return toggled;
	}

}
