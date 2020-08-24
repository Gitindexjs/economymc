package com.liablecode.economy.utils;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Utils {
	public static String chat (String s ) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
	public static boolean isConvertable(String s) {
		if(s.isEmpty()) return false;
		if(s.matches("[0-9]+")) 
			return true;
		else return false;
	}
	public static boolean isRealNumber(String s) {
		if(s.isEmpty()) return false;
		if(s.matches("-?[0-9]+")) 
			return true;
		else return false;
	}
	public static boolean isOnCircle(Player entity, Location location, double radius) {
	    if (entity != null && location != null) {
	        final Location locationPlayer = entity.getLocation();
	     
	        return locationPlayer.distance(location) < radius;
	    } else {
	        return false;
	    }
	}
}
