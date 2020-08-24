package com.liablecode.economy.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.liablecode.economy.main.Main;
import com.liablecode.economy.utils.Utils;

public class LaunchPadVector implements CommandExecutor {
	private Main plugin;
	public LaunchPadVector(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("LaunchPadVector").setExecutor(this);
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!sender.hasPermission("LaunchPad.vector.change")) {
			sender.sendMessage(Utils.chat("&cYou do not have permission to use this command."));
			return false;
		}
		if(args.length < 5) {
			sender.sendMessage(Utils.chat("&ePlease enter the <x>, <y>, <z>, <mult>, <ymov>"));
			return false;
		}
		for(int i = 0; i < args.length; i++) {
			if(!Utils.isRealNumber(args[i])) {
				sender.sendMessage(Utils.chat("&cCould not convert 1 or more of the arguments to numbers!"));
				return false;
			}
		}
		plugin.getConfig().set("LaunchPadX",    Integer.parseInt(args[0]));
		plugin.getConfig().set("LaunchPadY",    Integer.parseInt(args[1]));
		plugin.getConfig().set("LaunchPadZ",    Integer.parseInt(args[2]));
		plugin.getConfig().set("LaunchPadMult", Integer.parseInt(args[3]));
		plugin.getConfig().set("LaunchPadYMov", Integer.parseInt(args[4]));
		plugin.saveConfig();
		sender.sendMessage(Utils.chat("&bSuccessfully set the vector of the launch pad to x: &e" + 
				args[0] + "&7, &e" + 
				args[1] + "&7, &e" + 
				args[2] + "&7, &e" + 
				args[3] + "&7, &e" + 
				args[4]));
		return false;
	}
	
}
