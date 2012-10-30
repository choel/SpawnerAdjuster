package com.sadmean.mc.SpawnerAdjuster.command;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.MetadataValue;

import com.sadmean.mc.SpawnerAdjuster.SpawnerAdjuster;

public class spawneradjusterspawndata implements CommandExecutor {

	private SpawnerAdjuster plugin;
	
	public spawneradjusterspawndata(SpawnerAdjuster plugin) {
		this.setPlugin(plugin);
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if(!(sender instanceof Player)) {
			SpawnerAdjuster.log_It("warning", "/spawneradjusterspawndata can not be run from console");
			return true;
		} else {
			if(SpawnerAdjuster.advanced_debugMode) {
				Player player = (Player) sender;
				if(player.getTargetBlock(null, 15).getType() == Material.MOB_SPAWNER) {
					CreatureSpawner spawner = (CreatureSpawner) player.getTargetBlock(null, 15).getState();
					SpawnerAdjuster.log_It("info", "Printing raw spawner data");
					SpawnerAdjuster.log_It("info", Byte.toString(spawner.getRawData()));
					List<MetadataValue> data = spawner.getMetadata("ForceSpawnedEnts");
					data.get(0).asString();
				} else {
					return false;
				}
			} else {
				return false;
			}
			//should never get this far
			return false;
		}
		
	}
	public void setPlugin(SpawnerAdjuster plugin) {
		this.plugin = plugin;
	}

	public SpawnerAdjuster getPlugin() {
		return plugin;
	}
	
}
