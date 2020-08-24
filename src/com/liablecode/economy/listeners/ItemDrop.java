package com.liablecode.economy.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import com.liablecode.economy.main.Main;



public class ItemDrop implements Listener {
	@SuppressWarnings("unused")
	private Main plugin;
	public ItemDrop(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	@EventHandler
	public void onPlayerItemDrop(PlayerDropItemEvent e) {
		switch(e.getItemDrop().getItemStack().getType()) {
			case GLASS_BOTTLE:
				e.getItemDrop().remove();
			break;
			default:
				if(!e.getPlayer().hasPermission("Items.drop")) {
					e.setCancelled(true);
				}
			break;
		}
	}
}
