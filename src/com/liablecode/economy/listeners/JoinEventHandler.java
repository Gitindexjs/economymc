package com.liablecode.economy.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.liablecode.economy.cmds.togglescoreboard;
import com.liablecode.economy.main.Main;
import com.liablecode.economy.utils.ScoreboardController;

public class JoinEventHandler implements Listener {
	private Main plugin;
	public JoinEventHandler(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	@EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        if (!plugin.data.getConfig().isSet("money." + player.getUniqueId().toString())) {
            plugin.data.getConfig().set("money."+player.getUniqueId().toString(), 100);
            plugin.data.saveConfig();
        }
        if(!plugin.data.getConfig().isSet("kills." + player.getUniqueId().toString())) {
        	plugin.data.getConfig().set("kills."+player.getUniqueId().toString(), 0);
            plugin.data.saveConfig();
        }
        if(!plugin.data.getConfig().isSet("deaths." + player.getUniqueId().toString())) {
        	plugin.data.getConfig().set("deaths."+player.getUniqueId().toString(), 0);
            plugin.data.saveConfig();
        }
        if(!plugin.data.getConfig().isSet("kd." + player.getUniqueId().toString())) {
        	plugin.data.getConfig().set("kd."+player.getUniqueId().toString(), 0);
            plugin.data.saveConfig();
        }
        if(Bukkit.getOnlinePlayers() == null) return;
        if(Bukkit.getOnlinePlayers().size() == 0) return;
        if(togglescoreboard.getToggled()) {
        	for(Player p : Bukkit.getOnlinePlayers()){
                p.setScoreboard(ScoreboardController.getBoard(p));
            }
        }
    }
	
	
}
