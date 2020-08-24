package com.liablecode.economy.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.liablecode.economy.main.Main;
import com.liablecode.economy.utils.Utils;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class Pay implements CommandExecutor {
	private Main plugin;
	public Pay(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("pay").setExecutor(this);
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Only players may execute this command");
			return false;
		}
		if(!sender.hasPermission("pay.use")) {
			sender.sendMessage(Utils.chat("&cYou do not have permission to use this command!"));
			return false;
		}
		if(args.length < 2) {
			sender.sendMessage(Utils.chat("&eUsage: &6/pay <Player> <Amount>"));
			return false;
		}
		Player player = (Player) sender;
		Player target = Bukkit.getServer().getPlayer(args[0]);
		if(target == null) {
			player.sendMessage(Utils.chat("&cCould not find a user with the name of &7" + args[0]));
			return false;
		}
		if(!Utils.isConvertable(args[1])) {
			player.sendMessage(Utils.chat("&cEnter a number in the amount to pay"));
			return false;
		}
		if(plugin.data.getConfig().getInt("money." + player.getUniqueId().toString()) < Integer.parseInt(args[1])) {
			player.sendMessage("&eYou do not have enough money to pay " + args[0] + "!");
			return false;
		}
		TextComponent message = new TextComponent(Utils.chat("Your current balance is: &a$" + plugin.data.getConfig().getInt("money." + player.getUniqueId().toString()) + " would you like to pay &e" + target.getDisplayName() + " &a$" + args[1]));
		message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/confirmpay " + target.getName() + " " + args[1]));
		message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Utils.chat("&eClick to pay " + target.getName())).create()));
		player.spigot().sendMessage(message);
		return true;
	}
}
