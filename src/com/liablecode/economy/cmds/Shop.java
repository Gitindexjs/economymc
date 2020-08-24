package com.liablecode.economy.cmds;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.liablecode.economy.main.Main;
import com.liablecode.economy.utils.NBTWrapper;
import com.liablecode.economy.utils.Utils;

public class Shop implements CommandExecutor {
	@SuppressWarnings("unused")
	private Main plugin;
	public Shop(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("shop").setExecutor(this);
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!sender.hasPermission("shop.access")) {
			sender.sendMessage(Utils.chat("&cYou do not have permission to use this command."));
			return false;
		}
		if(!(sender instanceof Player)) {
			sender.sendMessage(Utils.chat("&eOnly players may execute this command."));
			return false;
		}
		Player player = (Player) sender;
		openGUI(player);
		return true;
	}
	private void openGUI(Player player) {
		Inventory inv = Bukkit.createInventory(player, 54, Utils.chat("&6Shop"));
		// fly
		Material[] materials = {Material.DIAMOND_HELMET, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_LEGGINGS, Material.DIAMOND_BOOTS};
		String[] names = { "Diamond Helmet", "Diamond Chestplate", "Diamond Leggins", "Diamond Boots"};
		char[] positions = { 2, 11, 20, 29 };
		Enchantment[][] ench = {{Enchantment.PROTECTION_ENVIRONMENTAL}, {Enchantment.PROTECTION_ENVIRONMENTAL}, {Enchantment.PROTECTION_ENVIRONMENTAL}, {Enchantment.PROTECTION_ENVIRONMENTAL}};
		int[] costs = {4500, 5000, 3000, 2500};
		int[][] enchlevels = {{2}, {2}, {2}, {2}};
		for(int i = 0; i < materials.length; i++) {
			Enchantment[] enchs = ench[i];
			int cost = costs[i];
			List<String> itemLore = new ArrayList<String>();
			itemLore.add(Utils.chat("&r&7Price: &a" + cost));
			int position = positions[i];
			int[] enchlvls = enchlevels[i];
			ItemGen(names[i], materials[i], itemLore, true, enchs, enchlvls, cost, inv, position);
		}
		List<String> closeItemLore = new ArrayList<String>();
		closeItemLore.add(Utils.chat("&r&7Click to close shop."));
		closeItem("&cClose", Material.BARRIER, closeItemLore, inv, 49);
		player.openInventory(inv);
	}
	private ItemStack ItemGen(String itemName, Material material, List<String> lore, boolean hasEnch, Enchantment[] enchments, int[] enchmentslvls, int cost, Inventory inv, int pos) {
		if(enchments.length != enchmentslvls.length) 
			return null;
		ItemStack item = new ItemStack(material);
		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(Utils.chat(itemName));
		List<String> itemLore = lore;
		itemMeta.setLore(itemLore);
		item.setItemMeta(itemMeta);
		if(hasEnch) {
			for(int i = 0; i < enchments.length; i++) {
				item.addUnsafeEnchantment(enchments[i], enchmentslvls[i]);
			}
		}
		net.minecraft.server.v1_8_R3.ItemStack nmsItem = NBTWrapper.addNBT("cost", cost, item);
		item = CraftItemStack.asBukkitCopy(nmsItem);
		inv.setItem(pos, item);
		return item;
	}
	private ItemStack closeItem(String itemName, Material material, List<String> lore, Inventory inv, int pos) {
		ItemStack closeItem = new ItemStack(material);
		ItemMeta closeMeta = closeItem.getItemMeta();
		closeMeta.setDisplayName(Utils.chat(itemName));
		closeMeta.setLore(lore);
		closeItem.setItemMeta(closeMeta);
		inv.setItem(pos, closeItem);
		return closeItem;
	}

}