package com.sadmean.mc.SpawnerAdjuster.spawningEvents;

import java.util.ArrayList;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

public class CreeperListener implements Listener{
	
	@EventHandler
	public void onCreeperSpawn(CreatureSpawnEvent event) {
		if(event.getEntityType() == EntityType.CREEPER) {
			Creeper creeper = (Creeper) event.getEntity();
			if(event.getSpawnReason() == SpawnReason.SPAWNER || event.getSpawnReason() == SpawnReason.CUSTOM) {
				ArrayList<Block> blocks;
				blocks = new ArrayList<Block>();
				for(int x = -3; x > 3; x++) {
					for(int y = -3; y > 3; y++) {
						for(int z = -3; z > 3; z++) {
							blocks.add(event.getLocation().getBlock().getRelative(x, y, z));
						}
					}
				}
				int index = 0;
				while(index < blocks.size()) {
					if(blocks.get(index).getType() == Material.MOB_SPAWNER) {
						if(blocks.get(index).hasMetadata("CreeperMod")) {
							creeper.setPowered(true); //TADA!!!!
						}
					}
					index++;
				}
			}
		}
	}

}
