package com.liablecode.economy.utils;

import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagInt;
import net.minecraft.server.v1_8_R3.NBTTagString;

public class NBTWrapper {
	public static net.minecraft.server.v1_8_R3.ItemStack addNBT(String key, String Value, ItemStack item) {
		net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
		NBTTagCompound itemcompound = (nmsItem.hasTag()) ? nmsItem.getTag() : new NBTTagCompound();
		itemcompound.set(key, new NBTTagString(Value));
		nmsItem.setTag(itemcompound);
		return nmsItem;
	}
	public static net.minecraft.server.v1_8_R3.ItemStack addNBT(String key, int Value, ItemStack item) {
		net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
		NBTTagCompound itemcompound = (nmsItem.hasTag()) ? nmsItem.getTag() : new NBTTagCompound();
		itemcompound.set(key, new NBTTagInt(Value));
		nmsItem.setTag(itemcompound);
		return nmsItem;
	}
	public static boolean hasNBT(ItemStack item) {
		net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
		return nmsItem.hasTag();
	}
	public static boolean hasNBT ( ItemStack item, String key) {
		net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
		return nmsItem.getTag().hasKey(key);
	}
	public static String getNBT(String key, net.minecraft.server.v1_8_R3.ItemStack item) {
		String answer = item.getTag().getString(key);
		return answer;
	}
	public static int getIntNBT(String key, net.minecraft.server.v1_8_R3.ItemStack item) {
		int answer = item.getTag().getInt(key);
		return answer;
	}
	public static NBTTagCompound getNBT(net.minecraft.server.v1_8_R3.ItemStack item) {
		NBTTagCompound answer = item.getTag();
		return answer;
	}
}
