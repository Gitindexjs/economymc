package com.liablecode.economy.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import com.liablecode.economy.main.Main;

public class PlayerPlaceBlock implements Listener {
	@SuppressWarnings("unused")
	private Main plugin;
	public PlayerPlaceBlock(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	@EventHandler
	public void onBreak(BlockPlaceEvent e) {
		if(!e.getPlayer().hasPermission("blocks.place")) {
			e.setCancelled(true);
		}
	}
}
