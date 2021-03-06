package com.liablecode.economy.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.liablecode.economy.main.Main;
import com.liablecode.economy.utils.Utils;


public class Balance implements CommandExecutor {
	private Main plugin;
	public Balance(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("balance").setExecutor(this);
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Only players may execute this command");
			return true;
		}
		Player player = (Player) sender;
		if(player.getWorld().getName() != plugin.getConfig().getString("kitpvpworld")) 
			return false;
		player.sendMessage("--------------------");
		player.sendMessage(Utils.chat("&6Your current balance is: &a$" + plugin.data.getConfig().getInt("money." + player.getUniqueId().toString())));
		player.sendMessage("--------------------");
		return true;
	}
}
