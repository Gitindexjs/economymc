package com.liablecode.economy.listeners;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import com.liablecode.economy.cmds.togglescoreboard;
import com.liablecode.economy.main.Main;
import com.liablecode.economy.utils.ScoreboardController;
import com.liablecode.economy.utils.Utils;

public class KillEventHandler implements Listener {
	private Main plugin;
	public KillEventHandler(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	@EventHandler
	public void onDeathPlayer(PlayerDeathEvent e) {
//		e.getEntity().getLastDamageCause() == DamageCause.;
		Player killer = e.getEntity().getKiller();
		// messages part
//		switch(e.getEntity().getLastDamageCause().getCause()) {
//			case BLOCK_EXPLOSION:
//			
//			break;
//			case CONTACT:
//			break;
//			case CUSTOM:
//			break;
//			case DROWNING:
//			break;
//			case ENTITY_ATTACK:
//			break;
//			case ENTITY_EXPLOSION:
//			break;
//			case FALL:
//			break;
//			case FALLING_BLOCK:
//			break;
//			case FIRE:
//			break;
//			case FIRE_TICK:
//			break;
//			case LAVA:
//			break;
//			case LIGHTNING:
//			break;
//			case MAGIC:
//			break;
//			case MELTING:
//			break;
//			case POISON:
//			break;
//			case PROJECTILE:
//			break;
//			case STARVATION:
//			break;
//			case SUFFOCATION:
//			break;
//			case SUICIDE:
//			break;
//			case THORNS:
//			break;
//			case VOID:
//			break;
//			case WITHER:
//			break;
//			default:
//			break;
//		}
		if(e.getEntity().getKiller() == e.getEntity()) {
        	List<String> listOfPhrases = plugin.getConfig().getStringList("phrases.suicide");
			String[] phrases = listOfPhrases.toArray(new String[0]);
			e.setDeathMessage(Utils.chat(phrases[(int) Math.floor(Math.random() * phrases.length)].replace("%player%", e.getEntity().getName())));
			plugin.data.getConfig().set("deaths." + e.getEntity().getUniqueId().toString(), plugin.data.getConfig().getInt("deaths." + e.getEntity().getUniqueId().toString()) + 1);
			if(togglescoreboard.getToggled()) {
				e.getEntity().setScoreboard(ScoreboardController.getBoard(e.getEntity()));
			}
        } else if(!(e.getEntity().getKiller() instanceof Player)) {
			List<String> listOfPhrases = plugin.getConfig().getStringList("phrases.onemandeath");
			String[] phrases = listOfPhrases.toArray(new String[0]);
			e.setDeathMessage(Utils.chat(phrases[(int) Math.floor(Math.random() * phrases.length)].replace("%player%", e.getEntity().getName())));
			
			plugin.data.getConfig().set("deaths." + e.getEntity().getUniqueId().toString(), plugin.data.getConfig().getInt("deaths." + e.getEntity().getUniqueId().toString()) + 1);
			if(togglescoreboard.getToggled()) {
				e.getEntity().setScoreboard(ScoreboardController.getBoard(e.getEntity()));
			}
		} else {
			List<String> listOfPhrases = plugin.getConfig().getStringList("phrases.playerkilledplayer");
			String[] phrases = listOfPhrases.toArray(new String[0]);
			e.setDeathMessage(Utils.chat(phrases[(int) Math.floor(Math.random() * phrases.length)].replace("%player%", e.getEntity().getName()).replace("%killer%", e.getEntity().getKiller().getName())));
			// money part
			int beforeMoney = plugin.data.getConfig().getInt("money." + killer.getUniqueId().toString());
	        Player victim = (Player) e.getEntity();
        	int kills = plugin.data.getConfig().getInt("kills." + killer.getUniqueId().toString());
        	int deaths = plugin.data.getConfig().getInt("deaths." + victim.getUniqueId().toString());
        	plugin.data.getConfig().set("deaths." + victim.getUniqueId().toString(), deaths + 1);
        	plugin.data.getConfig().set("kills." + killer.getUniqueId().toString(), kills + 1);
        	
        	// kd
        	// victim
        	boolean victimDeathsEqZero = deaths == 0;
    	    int victimKills = plugin.data.getConfig().getInt("kills." + victim.getUniqueId().toString());
    	    float KdVictimUnrounded = (float)victimKills / (float)plugin.data.getConfig().getInt("deaths." + victim.getUniqueId().toString());
    		float kdvictim = (float)Math.round(
    				victimDeathsEqZero ? 
            					victimKills * 100 : 
            					KdVictimUnrounded * 100
            		) / 100f;
    		// killer
    		int killerDeaths = plugin.data.getConfig().getInt("deaths." + killer.getUniqueId().toString());
    		boolean killerDeathsEqZero = killerDeaths == 0;
    	    float KdKillerUnrounded = (float)plugin.data.getConfig().getInt("kills." + killer.getUniqueId().toString()) / (float)killerDeaths;
        	float kdkiller = (float)Math.round(
        			killerDeathsEqZero ? 
        					plugin.data.getConfig().getInt("kills." + killer.getUniqueId().toString()) * 100: 
        						KdKillerUnrounded * 100
        		) / 100f;
        	System.out.println(kdkiller + ", " + kdvictim);
        	plugin.data.getConfig().set("money." + killer.getUniqueId().toString(),  beforeMoney + Math.floor(Math.random() * 50));
        	plugin.data.getConfig().set("kd." + victim.getUniqueId().toString(), kdvictim);
        	plugin.data.getConfig().set("kd." + killer.getUniqueId().toString(), kdkiller);
            plugin.data.saveConfig();
            if(Math.floor( beforeMoney / 1000) < Math.floor(plugin.data.getConfig().getInt("money." + killer.getUniqueId().toString()) / 1000)) {
            	killer.playSound(killer.getLocation(), Sound.NOTE_PIANO, 1f, 1f);
            	killer.sendMessage("----------------------------------------");
            	killer.sendMessage(Utils.chat("&ereached &a$" + (Math.floor(plugin.data.getConfig().getInt("money." + killer.getUniqueId().toString())) / 1000) * 1000));
            	killer.sendMessage("----------------------------------------");
            }
            if(togglescoreboard.getToggled()) {
            	killer.setScoreboard(ScoreboardController.getBoard(killer));
	            victim.setScoreboard(ScoreboardController.getBoard(victim));
            }
	        
		}
		
		
	}
}
