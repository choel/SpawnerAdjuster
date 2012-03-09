package com.sadmean.mc.SpawnerAdjuster;

import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class AdjusterBlockListener implements Listener {
	public static SpawnerAdjuster plugin; public AdjusterBlockListener(SpawnerAdjuster instance) { 
		plugin = instance;
	}
	
	@EventHandler
	public void onBlockRedstoneChange(BlockRedstoneEvent event){
		if(!(SpawnerAdjuster.respondToRedstone && SpawnerAdjuster.useRedstoneListener)) return;
		if(event.getBlock().getRelative(1, 0, 0).getType() == Material.MOB_SPAWNER) {
			CreatureSpawner spawner = (CreatureSpawner) event.getBlock().getRelative(1, 0, 0).getState();
			if(SpawnerAdjuster.redstoneForcesSpawn) {
				forceSpawn(spawner);
			} else {
				spawner.setDelay(1);
			}
		}
		if(event.getBlock().getRelative(-1, 0, 0).getType() == Material.MOB_SPAWNER) {
			CreatureSpawner spawner = (CreatureSpawner) event.getBlock().getRelative(-1, 0, 0).getState();
			if(SpawnerAdjuster.redstoneForcesSpawn) {
				forceSpawn(spawner);
			} else {
				spawner.setDelay(1);
			}
		}
		if(event.getBlock().getRelative(0, 0, 1).getType() == Material.MOB_SPAWNER) {
			CreatureSpawner spawner = (CreatureSpawner) event.getBlock().getRelative(0, 0, 1).getState();
			if(SpawnerAdjuster.redstoneForcesSpawn) {
				forceSpawn(spawner);
			} else {
				spawner.setDelay(1);
			}
		}
		if(event.getBlock().getRelative(0, 0, -1).getType() == Material.MOB_SPAWNER) {
			CreatureSpawner spawner = (CreatureSpawner) event.getBlock().getRelative(0, 0, -1).getState();
			if(SpawnerAdjuster.redstoneForcesSpawn) {
				forceSpawn(spawner);
			} else {
				spawner.setDelay(1);
			}
		}
		if(event.getBlock().getRelative(0, -2, 0).getType() == Material.MOB_SPAWNER) {
			CreatureSpawner spawner = (CreatureSpawner) event.getBlock().getRelative(0, -2, 0).getState();
			if(SpawnerAdjuster.redstoneForcesSpawn) {
				forceSpawn(spawner);
			} else {
				spawner.setDelay(1);
			}
		}
		if(event.getBlock().getRelative(0, 2, 0).getType() == Material.MOB_SPAWNER) {
			CreatureSpawner spawner = (CreatureSpawner) event.getBlock().getRelative(0, 2, 0).getState();
			if(SpawnerAdjuster.redstoneForcesSpawn) {
				forceSpawn(spawner);
			} else {
				spawner.setDelay(1);
			}
		}
		if(event.getBlock().getRelative(0, 1, 0).getType() == Material.MOB_SPAWNER) {
			CreatureSpawner spawner = (CreatureSpawner) event.getBlock().getRelative(0, 1, 0).getState();
			if(SpawnerAdjuster.redstoneForcesSpawn) {
				forceSpawn(spawner);
			} else {
				spawner.setDelay(1);
			}
		}
		if(event.getBlock().getRelative(1, -1, 0).getType() == Material.MOB_SPAWNER) {
			CreatureSpawner spawner = (CreatureSpawner) event.getBlock().getRelative(1, -1, 0).getState();
			if(SpawnerAdjuster.redstoneForcesSpawn) {
				forceSpawn(spawner);
			} else {
				spawner.setDelay(1);
			}
		}
		if(event.getBlock().getRelative(-1, -1, 0).getType() == Material.MOB_SPAWNER) {
			CreatureSpawner spawner = (CreatureSpawner) event.getBlock().getRelative(-1, -1, 0).getState();
			if(SpawnerAdjuster.redstoneForcesSpawn) {
				forceSpawn(spawner);
			} else {
				spawner.setDelay(1);
			}
		}
		if(event.getBlock().getRelative(0, -1, 1).getType() == Material.MOB_SPAWNER) {
			CreatureSpawner spawner = (CreatureSpawner) event.getBlock().getRelative(0, -1, 1).getState();
			if(SpawnerAdjuster.redstoneForcesSpawn) {
				forceSpawn(spawner);
			} else {
				spawner.setDelay(1);
			}
		}
		if(event.getBlock().getRelative(0, -1, -1).getType() == Material.MOB_SPAWNER) {
			CreatureSpawner spawner = (CreatureSpawner) event.getBlock().getRelative(0, -1, -1).getState();
			if(SpawnerAdjuster.redstoneForcesSpawn) {
				forceSpawn(spawner);
			} else {
				spawner.setDelay(1);
			}
		}
		
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		if(event.getBlock().getType() == Material.MOB_SPAWNER && SpawnerAdjuster.useBlockListener) {
			if(event.getPlayer() != null) {
				if(!SpawnerAdjuster.permCheck(event.getPlayer(), "SpawnerAdjuster.BreakSpawners")) {
					event.setCancelled(true);
					return;
				}
			}
			if(SpawnerAdjuster.permCheck(event.getPlayer(), "SpawnerAdjuster.DropSpawners") && SpawnerAdjuster.allowDroppedSpawners) {
				short asdf = 1;
				ItemStack spawnerstack = new ItemStack(event.getBlock().getType(), 1, asdf, event.getBlock().getData());
				event.getBlock().getLocation().getWorld().dropItemNaturally(event.getBlock().getLocation(), spawnerstack);
			}
		}
	}
	
	/*
	 * this wil not be used until 1.5 or 2.0
	 * Catches CreatureSpawnEvents and checks to see if redstone is nearby. 
	 * If redstone is near and it is unpowered, prevent the spawn.
	 * 
	 */
	public void onCreatureSpawn(CreatureSpawnEvent event) {
		if(event.getSpawnReason() == SpawnReason.SPAWNER) {
			//if(event.getLocation().getBlock().ge) {
				
			//}
		}
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		//admin redstone thing here (someday).
	}
	
	private void forceSpawn(CreatureSpawner spawner) {

		int range = 1;
		boolean reset = false;
		//if(spawner.getCreatureType() == CreatureType.SKELETON)
		for(int dx = -(range); dx <= range && !reset; dx++) {
			for(int dy = -(range); dy <= range && !reset; dy++) {
				for(int dz = -(range); dz <= range && !reset; dz++) {
					if(spawner.getBlock().getRelative(dx, dy, dz).getType() == Material.AIR) {
						LivingEntity spawnedEnt = spawner.getWorld().spawnCreature(spawner.getBlock().getRelative(dx, dy, dz).getLocation(), spawner.getSpawnedType());
						List<Entity> entList = spawnedEnt.getNearbyEntities(SpawnerAdjuster.spawnerEntCheckRadius - 1, SpawnerAdjuster.spawnerEntCheckRadius - 1, SpawnerAdjuster.spawnerEntCheckRadius - 1);
						///int i = 0; //index
						int numEntsofType = 0; //number of matching ents
						/** this check needs work. Next version!
						while(i <= entList.size()) {
							if(entList.get(i) instanceof LivingEntity) {
								numEntsofType++;
							}
							i++;
						}
						**/
						numEntsofType = entList.size();
						if(numEntsofType >= SpawnerAdjuster.maxNumberOfEntsNearSpawner && SpawnerAdjuster.useRadiusCheck) {
							spawnedEnt.remove();
						}
						reset = true;
						/* anti-munson implement here */
						if(!SpawnerAdjuster.canSpawn(spawner, spawnedEnt)) {
							spawnedEnt.remove();
						}
						/* end anti munson implement */	
					}
				}
			}
		}
		spawner.setDelay(600);
	}
}
