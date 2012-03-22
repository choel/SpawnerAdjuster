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
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		Player senderPlayer = (Player) sender;
		if(!cmd.getName().equalsIgnoreCase("spawneradjusterreload")) {
			return false;
		}
		if(!senderPlayer.hasPermission("SpawnerAdjuster.Commands.SAReload") && !senderPlayer.isOp()) {
			return false;
		}
		
		Config.load();
		
		return true;
	}

}
