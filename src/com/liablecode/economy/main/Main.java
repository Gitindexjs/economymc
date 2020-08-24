package com.liablecode.economy.main;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.liablecode.economy.cmds.Balance;
import com.liablecode.economy.cmds.ConfirmPay;
import com.liablecode.economy.cmds.IpGrab;
import com.liablecode.economy.cmds.LaunchPadVector;
import com.liablecode.economy.cmds.Pay;
import com.liablecode.economy.cmds.Ping;
import com.liablecode.economy.cmds.PluginCmd;
import com.liablecode.economy.cmds.SetDefenceCircle;
import com.liablecode.economy.cmds.Shop;
import com.liablecode.economy.cmds.togglescoreboard;
import com.liablecode.economy.cmds.underminepay;
import com.liablecode.economy.completers.SetDefenceCircleTab;
import com.liablecode.economy.dataManage.DataManager;
import com.liablecode.economy.listeners.ItemDrop;
import com.liablecode.economy.listeners.JoinEventHandler;
import com.liablecode.economy.listeners.KillEventHandler;
import com.liablecode.economy.listeners.PlayerBreakBlock;
import com.liablecode.economy.listeners.PlayerDamageHandler;
import com.liablecode.economy.listeners.PlayerPlaceBlock;
import com.liablecode.economy.listeners.QuitEventHandler;
import com.liablecode.economy.listeners.ShopClickEvent;
import com.liablecode.economy.listeners.SteppedOnLaunchPad;
import com.liablecode.economy.listeners.ThrowEnderpealHandler;

import net.luckperms.api.LuckPerms;

public class Main extends JavaPlugin {
	private Logger log = Bukkit.getLogger();
	public DataManager data;
	public LuckPerms api;
	@Override
	public void onEnable() {
		saveDefaultConfig();
		RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
		if (provider != null) api = provider.getProvider();
		this.data = new DataManager(this);
		new KillEventHandler(this);
		new JoinEventHandler(this);
		new QuitEventHandler(this);
		new PlayerBreakBlock(this);
		new PlayerPlaceBlock(this);
		new PlayerDamageHandler(this);
		new Balance(this);
		new ConfirmPay(this);
		new Pay(this);
		new PluginCmd(this);
		new underminepay(this);
		new togglescoreboard(this);
		new SetDefenceCircle(this);
		new Shop(this);
		new SetDefenceCircleTab(this);
		new LaunchPadVector(this);
		new Ping(this);
		new IpGrab(this);
		new ItemDrop(this);
		new SteppedOnLaunchPad(this);
		new ShopClickEvent(this);
		new ThrowEnderpealHandler(this);
		log.info("[" + this.getDescription().getName() + " " + this.getDescription().getVersion() + "] success");
	}
}
