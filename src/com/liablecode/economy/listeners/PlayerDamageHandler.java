package com.liablecode.economy.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import com.liablecode.economy.main.Main;
import com.liablecode.economy.utils.Utils;

public class PlayerDamageHandler implements Listener {
	private Main plugin;
	public PlayerDamageHandler(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	@EventHandler
	public void onPlayerDamageByPlayer(EntityDamageByEntityEvent e) {
		if(e.getEntity() instanceof Player && Utils.isOnCircle((Player) e.getEntity(), new Location((World) e.getEntity().getWorld(), plugin.getConfig().getInt("nodamageX"), plugin.getConfig().getInt("nodamageY"), plugin.getConfig().getInt("nodamageZ")), (double) plugin.getConfig().getDouble("nodamageR"))) {
			e.setCancelled(true);
			e.getDamager().sendMessage(Utils.chat("&cCan not damage players here"));
		}
	}
	@EventHandler
	public void onPlayerDamage(EntityDamageEvent e) {
		if(e.getEntity() instanceof Player && Utils.isOnCircle((Player) e.getEntity(), new Location((World) e.getEntity().getWorld(), plugin.getConfig().getInt("nodamageX"), plugin.getConfig().getInt("nodamageY"), plugin.getConfig().getInt("nodamageZ")), (double) plugin.getConfig().getDouble("nodamageR"))) {
			e.setCancelled(true);
		}
	}
}
