package com.sadmean.mc.SpawnerAdjuster.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.sadmean.mc.SpawnerAdjuster.SpawnerAdjuster;

public class spawneradjusterdebug implements CommandExecutor {

	private SpawnerAdjuster plugin;
	
	public spawneradjusterdebug(SpawnerAdjuster plugin) {
		this.setPlugin(plugin);
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if(!(sender instanceof Player)) {
			SpawnerAdjuster.log_It("warning", "/spawneradjusterdebug cannot be done from the console");
			return true;
		}
		Player senderplayer = (Player) sender;
		if(!cmd.getName().equalsIgnoreCase("spawneradjusterdebug")) {
			sender.sendMessage("Major error");
			return false;
		}
		if(!senderplayer.hasPermission("SpawnerAdjuster.Commands.SADebug")) {
			if(senderplayer.isOp()) {
				//don't worry abut it.
			} else {
				sender.sendMessage("permission error");
				return false;
			}
		}
		SpawnerAdjuster.log_It("info", "Running debug check, started by " + sender.getName());
		senderplayer.sendMessage(SpawnerAdjuster.chatPrefix + "Starting Debug Check");
		//start by checking a setting or two
		senderplayer.sendMessage(SpawnerAdjuster.chatPrefix + "allowPigs is set to " + SpawnerAdjuster.allowPig);
		senderplayer.sendMessage(SpawnerAdjuster.chatPrefix + "allowEnderDragon is set to " + SpawnerAdjuster.allowEnderDragon);
		senderplayer.sendMessage(SpawnerAdjuster.chatPrefix + "Radius Checks are " + SpawnerAdjuster.useRadiusCheck);
		//Lets run a permissions check
		if(senderplayer.hasPermission("SpawnerAdjuster.Commands.SADebug")) {
			senderplayer.sendMessage(SpawnerAdjuster.chatPrefix + "You have permission node SpawnerAdjuster.Commands.SADebug");
		} else {
			senderplayer.sendMessage(SpawnerAdjuster.chatPrefix + "You DO NOT have permission node SpawnerAdjuster.Commands.SADebug");

		}
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
