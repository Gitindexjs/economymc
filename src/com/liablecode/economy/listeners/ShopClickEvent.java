package com.liablecode.economy.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.liablecode.economy.cmds.togglescoreboard;
import com.liablecode.economy.main.Main;
import com.liablecode.economy.utils.NBTWrapper;
import com.liablecode.economy.utils.ScoreboardController;
import com.liablecode.economy.utils.Utils;

public class ShopClickEvent implements Listener {
	private Main plugin;
	public ShopClickEvent(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	@EventHandler
	public void onShopClick(InventoryClickEvent e) {
		ItemStack item = e.getCurrentItem();
		if(!ChatColor.stripColor(e.getInventory().getName()).startsWith("Shop")) {
			return;
		}
		e.setCancelled(true);
		if(item.getType() == Material.BARRIER) {
			e.getWhoClicked().closeInventory();
			return;
		}
		if(!NBTWrapper.hasNBT(item) || !NBTWrapper.hasNBT(item, "cost")) 
			return;
		Player player = (Player) e.getWhoClicked();
		int beforeMoney = plugin.data.getConfig().getInt("money." + player.getUniqueId().toString());
		if(beforeMoney < NBTWrapper.getIntNBT("cost", CraftItemStack.asNMSCopy(item))) {
			player.sendMessage(Utils.chat("&cYou do not have enough money to pay the item."));
			return;
		}
		plugin.data.getConfig().set("money." + player.getUniqueId().toString(), beforeMoney - NBTWrapper.getIntNBT("cost", CraftItemStack.asNMSCopy(item)));
		plugin.data.saveConfig();
		ItemStack finalItem = new ItemStack(item.getType());
		ItemMeta finalMeta = finalItem.getItemMeta();
		finalMeta.setDisplayName(item.getItemMeta().getDisplayName());
		finalItem.setItemMeta(finalMeta);
		for ( Enchantment key : item.getEnchantments().keySet() ) {
		    finalItem.addUnsafeEnchantment(key, item.getEnchantments().get(key));
		}
		player.getInventory().addItem(finalItem);
		if(togglescoreboard.getToggled()) 
			player.setScoreboard(ScoreboardController.getBoard(player));
		player.playSound(player.getLocation(), Sound.NOTE_PIANO, 10, 5);
	}
}
