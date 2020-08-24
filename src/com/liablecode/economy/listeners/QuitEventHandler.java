package com.liablecode.economy.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.liablecode.economy.cmds.togglescoreboard;
import com.liablecode.economy.main.Main;
import com.liablecode.economy.utils.ScoreboardController;

public class QuitEventHandler implements Listener {
	@SuppressWarnings("unused")
	private Main plugin;
	public QuitEventHandler(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		if(Bukkit.getOnlinePlayers() == null) return;
		if(Bukkit.getOnlinePlayers().size() == 0) return;
		if(togglescoreboard.getToggled()) {
			for(Player p : Bukkit.getOnlinePlayers()){
	            p.setScoreboard(ScoreboardController.getBoard(p));
	        }
		}
	}
}
