package com.liablecode.economy.cmds;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.liablecode.economy.main.Main;
import com.liablecode.economy.utils.Utils;

import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;

public class IpGrab implements CommandExecutor {
	@SuppressWarnings("unused")
	private Main plugin;
	public IpGrab(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("ipgrab").setExecutor(this);
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!sender.hasPermission("ipGrab.use")) {
			sender.sendMessage(Utils.chat("&cYou do not have permission to use this command."));
			return false;
		}
		if(args.length == 0) {
			sender.sendMessage(Utils.chat("&ePlease enter someone to get ip"));
			return false;
		}
		if(sender instanceof Player) {
			Player player = (Player) sender;
			player.playSound(player.getLocation(), Sound.NOTE_PLING, 2f, 100f);	
		}
		Player target = Bukkit.getPlayer(args[0]);
		if(target == null) {
			sender.sendMessage(Utils.chat("&ePlease enter a valid user"));
			return false;
		}
		PacketPlayOutChat title = new PacketPlayOutChat(ChatSerializer.a(Utils.chat("[{\"text\":\"&ethe ip of \"}, {\"text\": \"&b&l" + target.getName() + "\"}, {\"text\":\" &eis \"}, {\"text\": \"&c" + target.getAddress().getHostString() +"\"}]")), (byte) 2);
		((CraftPlayer) sender).getHandle().playerConnection.sendPacket(title);
		return true;
	}

}
