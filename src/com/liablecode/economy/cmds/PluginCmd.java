package com.liablecode.economy.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import com.liablecode.economy.main.Main;
import com.liablecode.economy.utils.Utils;

public class PluginCmd implements CommandExecutor {
	@SuppressWarnings("unused")
	private Main plugin;
	public PluginCmd(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("plugins").setExecutor(this);
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Plugin[] plugins = Bukkit.getPluginManager().getPlugins();
		if(sender.hasPermission("plugins.read")) {
			String result = "";
			for(Plugin _plugin : plugins) {
				result += "&a" + _plugin.getName() + "&f, ";
			}
			sender.sendMessage(Utils.chat("plugins (" + plugins.length + "): " + result));
		} else {
			sender.sendMessage(Utils.chat("&cYou do not have permission to execute this command."));
		}
		return false;
	}

}
