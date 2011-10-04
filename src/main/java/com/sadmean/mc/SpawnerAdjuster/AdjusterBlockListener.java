package com.sadmean.mc.SpawnerAdjuster;

import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.inventory.ItemStack;

public class AdjusterBlockListener extends BlockListener {
	public static SpawnerAdjuster plugin; public AdjusterBlockListener(SpawnerAdjuster instance) { 
		plugin = instance;
	}
	
	public void onBlockRedstoneChange(BlockRedstoneEvent event){
		if(event.getBlock().getRelative(1, 0, 0).getType() == Material.MOB_SPAWNER && SpawnerAdjuster.useRedstoneListener) {
			CreatureSpawner spawner = (CreatureSpawner) event.getBlock().getRelative(1, 0, 0).getState();
			spawner.setDelay(1);
		}
		if(event.getBlock().getRelative(-1, 0, 0).getType() == Material.MOB_SPAWNER && SpawnerAdjuster.useRedstoneListener) {
			CreatureSpawner spawner = (CreatureSpawner) event.getBlock().getRelative(-1, 0, 0).getState();
			spawner.setDelay(1);
		}
		if(event.getBlock().getRelative(0, 0, 1).getType() == Material.MOB_SPAWNER && SpawnerAdjuster.useRedstoneListener) {
			CreatureSpawner spawner = (CreatureSpawner) event.getBlock().getRelative(0, 0, 1).getState();
			spawner.setDelay(1);
		}
		if(event.getBlock().getRelative(0, 0, -1).getType() == Material.MOB_SPAWNER && SpawnerAdjuster.useRedstoneListener) {
			CreatureSpawner spawner = (CreatureSpawner) event.getBlock().getRelative(0, 0, -1).getState();
			spawner.setDelay(1);
		}
	}
	
	public void onBlockBreak(BlockBreakEvent event) {
		if(event.getBlock().getType() == Material.MOB_SPAWNER) {
			if(event.getPlayer() != null) {
				if(!SpawnerAdjuster.permCheck(event.getPlayer(), "SpawnerAdjuster.BreakSpawners")) {
					event.setCancelled(true);
					return;
				}
			}
			ItemStack spawnerstack = new ItemStack(event.getBlock().getType(), 1);
			event.getBlock().getLocation().getWorld().dropItemNaturally(event.getBlock().getLocation(), spawnerstack);
		}
	}
}
