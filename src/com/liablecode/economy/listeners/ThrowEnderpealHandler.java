package com.liablecode.economy.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.liablecode.economy.cooldowns.CooldownManager;
import com.liablecode.economy.main.Main;
import com.liablecode.economy.utils.Utils;

public class ThrowEnderpealHandler implements Listener {
	private Main plugin;
	public ThrowEnderpealHandler(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	@EventHandler
    public void onPlayerInteract(PlayerInteractEvent e)
    {
		final CooldownManager cooldownManager = new CooldownManager();
        Player player = e.getPlayer();
        if(e.getAction() == Action.PHYSICAL && e.getClickedBlock().getType() == Material.SOIL){
            e.setCancelled(true);
        }
        if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)
        {	
        	if(player.getInventory().getItemInHand().getType().equals(Material.ENDER_PEARL))
            {
        		
        		e.setCancelled(true);
        		int timeLeft = cooldownManager.getCooldown(player.getUniqueId());
        		if(player.hasPermission("cooldown.pass")) {
        			e.setCancelled(false);
        			return;
        		}
	            if(timeLeft == 0){
	            	player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
		            	new BukkitRunnable() {
		                    @Override
		                    public void run() {
		                        int timeLeft = cooldownManager.getCooldown(player.getUniqueId());
		                        cooldownManager.setCooldown(player.getUniqueId(), --timeLeft);
		                        if(timeLeft == 0){
		                            this.cancel();
		                        }
		                    }
		                }.runTaskTimer(plugin, 20, 20);
	                	cooldownManager.setCooldown(player.getUniqueId(), cooldownManager.DEFAULT_COOLDOWN);
	                    player.launchProjectile(EnderPearl.class);
	            } else {
	                player.sendMessage(Utils.chat("&cYou need to wait " + timeLeft + " seconds for you to be able to shoot another."));
	            }
            }
        }
    }
}
