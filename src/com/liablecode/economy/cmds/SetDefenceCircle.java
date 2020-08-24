package com.liablecode.economy.cmds;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.liablecode.economy.main.Main;
import com.liablecode.economy.utils.Utils;

public class SetDefenceCircle implements CommandExecutor {
	private Main plugin;
	public SetDefenceCircle(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("nodamagezone").setExecutor(this);
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!sender.hasPermission("defence.set.zone")) {
			sender.sendMessage(Utils.chat("&cYou do not have permission to execute this command."));
			return false;
		}
		if(!(sender instanceof Player)) {
			sender.sendMessage(Utils.chat("&cOnly players may issue this command."));
			return false;
		}
		if(args.length == 0) {
			sender.sendMessage(Utils.chat("&ePlease enter a radius for the no damage zone."));
			return false;
		}
		if(args[0] == "") {
			sender.sendMessage(Utils.chat("&ePlease enter a radius for the no damage zone."));
			return false;
		}
		if(!Utils.isConvertable(args[0])) {
			sender.sendMessage(Utils.chat("&cCan not convert " + args[0] + " to a number."));
			return false;
		}
		Player player = (Player) sender;
		Location location = player.getLocation();
		plugin.getConfig().set("nodamageX", location.getX());
		plugin.getConfig().set("nodamageY", location.getY());
		plugin.getConfig().set("nodamageZ", location.getZ());
		plugin.getConfig().set("nodamageR", Integer.parseInt(args[0]));
		plugin.saveConfig();
		player.sendMessage(Utils.chat("&aSet the &cno damage zone &ato be on &b" + (int) location.getX() + "&7, &b" + (int) location.getY() + "&7, &b" + (int) location.getZ() + " &ewith a radius of &a" + Integer.parseInt(args[0])));
		return true;
	}

}
