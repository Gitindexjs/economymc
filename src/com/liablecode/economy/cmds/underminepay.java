package com.liablecode.economy.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.liablecode.economy.main.Main;
import com.liablecode.economy.utils.Utils;

public class underminepay implements CommandExecutor {
	@SuppressWarnings("unused")
	private Main plugin;
	public underminepay(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("underminepay").setExecutor(this);
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("Only players can execute this command");
			return false;
		}
		sender.sendMessage(Utils.chat("&4This payment was successfully canceled!"));
		return false;
	}
}
