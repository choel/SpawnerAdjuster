package com.sadmean.mc.SpawnerAdjuster.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.sadmean.mc.SpawnerAdjuster.SpawnerAdjuster;
import com.sadmean.mc.SpawnerAdjuster.Config.Config;

public class spawneradjusterreload implements CommandExecutor{

	private SpawnerAdjuster plugin;
	
	public spawneradjusterreload(SpawnerAdjuster plugin) {
		this.setPlugin(plugin);
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if(!(sender instanceof Player)) {
			SpawnerAdjuster.log_It("info", "reloading config ...");
			Config.load();
			return true;
		}
		Player senderPlayer = (Player) sender;
		if(!cmd.getName().equalsIgnoreCase("spawneradjusterreload")) {
			return false;
		}
		if(!senderPlayer.hasPermission("SpawnerAdjuster.Commands.SAReload") && !senderPlayer.isOp()) {
			return false;
		}
		senderPlayer.sendMessage(SpawnerAdjuster.chatPrefix + "reloading config ....");
		Config.load();
		
		return true;
	}

	//never used, removes annoying eclipse warning.
	public void setPlugin(SpawnerAdjuster plugin) {
		this.plugin = plugin;
	}

	public SpawnerAdjuster getPlugin() {
		return plugin;
	}

}
