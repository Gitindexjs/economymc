package com.liablecode.economy.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.liablecode.economy.main.Main;
import com.liablecode.economy.utils.Utils;

public class Ping implements CommandExecutor {
	@SuppressWarnings("unused")
	private Main plugin;
	public Ping(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("ping").setExecutor(this);
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!sender.hasPermission("ping.use")) {
			sender.sendMessage(Utils.chat("&cYou do not have permission to use this command."));
			return false;
		}
		if(args.length == 0) {
			if(sender instanceof Player) {
				Player player = (Player) sender;
				sender.sendMessage(Utils.chat("&aPing: &e" + ((CraftPlayer) player).getHandle().ping));
				return true;
			} else {
				sender.sendMessage(Utils.chat("&aPing: &e0"));
				return true;
			}
		}
		Player target = Bukkit.getPlayer(args[0]);
		if(target == null) {
			sender.sendMessage(Utils.chat("&cCould not find a user iwth the name of " + args[0]));
			return false;
		} else {
			sender.sendMessage(Utils.chat("&aPing: &e" + ((CraftPlayer) target).getHandle().ping));
			return true;
		}
	}

}
