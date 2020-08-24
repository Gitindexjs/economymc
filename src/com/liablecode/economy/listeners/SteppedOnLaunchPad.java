package com.liablecode.economy.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import com.liablecode.economy.main.Main;

public class SteppedOnLaunchPad implements Listener {
	private Main plugin;
	public SteppedOnLaunchPad(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	@EventHandler
	public void onStepped(PlayerMoveEvent e) {
		Material LaunchBlock = Material.valueOf(plugin.getConfig().getString("LaunchPadBlock"));
		Material LaunchPad = Material.valueOf(plugin.getConfig().getString("LaunchPadPressurePlate"));
		Player player = e.getPlayer();
		Location blockUnder = player.getLocation();
		blockUnder.setY(blockUnder.getY() - 1);
		if(player.getLocation().getBlock().getType().equals(LaunchPad) && blockUnder.getBlock().getType() == LaunchBlock) {
			player.setVelocity(new Vector(plugin.getConfig().getInt("LaunchPadX"), plugin.getConfig().getInt("LaunchPadY"), plugin.getConfig().getInt("LaunchPadZ")).multiply(plugin.getConfig().getInt("LaunchPadMult")).setY(plugin.getConfig().getInt("LaunchPadYMov")));
		}
		
	}
}
