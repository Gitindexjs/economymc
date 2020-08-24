package com.liablecode.economy.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import com.liablecode.economy.main.Main;

public class PlayerBreakBlock implements Listener {
	@SuppressWarnings("unused")
	private Main plugin;
	public PlayerBreakBlock(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		if(!e.getPlayer().hasPermission("blocks.break")) {
			e.setCancelled(true);
		}
	}
}
