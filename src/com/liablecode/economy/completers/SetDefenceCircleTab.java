package com.liablecode.economy.completers;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import com.liablecode.economy.main.Main;

public class SetDefenceCircleTab implements TabCompleter {
	List<String> arguments = new ArrayList<String>();
	@SuppressWarnings("unused")
	private Main plugin;
	public SetDefenceCircleTab(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("nodamagezone").setTabCompleter(this);
	}
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		if(arguments.isEmpty()) {
			arguments.add("50");
		}
		List<String> result = new ArrayList<String>();
		if(args.length == 1) {
			for(String a : arguments) {
				if(a.toLowerCase().startsWith(args[0].toLowerCase())) {
					result.add(a);
				}
			}
			return result;
		}
		return null;
	}

}
