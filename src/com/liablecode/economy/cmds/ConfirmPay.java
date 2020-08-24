package com.liablecode.economy.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.liablecode.economy.main.Main;
import com.liablecode.economy.utils.ScoreboardController;
import com.liablecode.economy.utils.Utils;

public class ConfirmPay implements CommandExecutor {
	private Main plugin;
	public ConfirmPay(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("confirmpay").setExecutor(this);
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		if(!sender.hasPermission("pay.use")) 
			return false;
		Player player = (Player) sender;
		Player target = Bukkit.getServer().getPlayer(args[0]);
		if(plugin.data.getConfig().getInt("money." + player.getUniqueId().toString()) < Integer.parseInt(args[1])) {
			return false;
		}
		plugin.data.getConfig().set("money." + player.getUniqueId().toString(), plugin.data.getConfig().getInt("money." + player.getUniqueId().toString()) - Integer.parseInt(args[1]));
		plugin.data.getConfig().set("money." + target.getUniqueId().toString(), plugin.data.getConfig().getInt("money." + target.getUniqueId().toString()) + Integer.parseInt(args[1]));
		player.sendMessage(Utils.chat("&eSuccessfully payed " + target.getDisplayName() + " &a$" + args[1]));
		target.sendMessage(Utils.chat("&aRecieved $" + args[1] + " from " + player.getDisplayName()));
		player.setScoreboard(ScoreboardController.getBoard(player));
		target.setScoreboard(ScoreboardController.getBoard(target));
		return true;
		
	}
}
