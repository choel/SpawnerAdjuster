package com.sadmean.mc.SpawnerAdjuster.Config;

import java.util.Map;

import org.bukkit.util.config.Configuration;

import com.sadmean.mc.SpawnerAdjuster.SpawnerAdjuster;

public class Config {
	public static boolean load() {
		Configuration configYAML = SpawnerAdjuster.getThisPlugin().getConfiguration();
		configYAML.load();
		Map<String, Object> map = configYAML.getAll();
		
		if(map.containsKey("system.usePlayerListener")) {
			SpawnerAdjuster.usePlayerListener = configYAML.getBoolean("system.usePlayerListener", true);
		} else {
			configYAML.setProperty("system.usePlayerListener", SpawnerAdjuster.usePlayerListener); 
		}
		
		if(map.containsKey("system.useRedstoneListener")) {
			SpawnerAdjuster.useRedstoneListener = configYAML.getBoolean("system.useRedstoneListener", true);
		} else {
			configYAML.setProperty("system.useRedstoneListener", SpawnerAdjuster.useRedstoneListener); 
		}
		
		if(map.containsKey("system.useBlockListener")) {
			SpawnerAdjuster.useBlockListener = configYAML.getBoolean("system.useBlockListener", true);
		} else {
			configYAML.setProperty("system.useBlockListener", SpawnerAdjuster.useBlockListener); 
		}
		
		if(map.containsKey("system.debugLogs")) {
			SpawnerAdjuster.debugLogs = configYAML.getBoolean("system.debugLogs", true);
		} else {
			configYAML.setProperty("system.debugLogs", SpawnerAdjuster.debugLogs); 
		}
		
		if(map.containsKey("system.SuperPerms")) {
			SpawnerAdjuster.SuperPerms = configYAML.getBoolean("system.SuperPerms", true);
		} else {
			configYAML.setProperty("system.SuperPerms", SpawnerAdjuster.SuperPerms); 
		}
		
		if(map.containsKey("system.ignorePermissions")) {
			SpawnerAdjuster.ignorePermissions = configYAML.getBoolean("system.ignorePermissions", true);
		} else {
			configYAML.setProperty("system.ignorePermissions", SpawnerAdjuster.ignorePermissions); 
		}
		
		//Spawner settings
		//Neutral Mobs
		if(map.containsKey("spawner.allowChicken")) {
			SpawnerAdjuster.allowChicken = configYAML.getBoolean("spawner.allowChicken", true);
		} else {
			configYAML.setProperty("spawner.allowChicken", SpawnerAdjuster.allowChicken); 
		}
		
		if(map.containsKey("spawner.allowWolf")) {
			SpawnerAdjuster.allowWolf = configYAML.getBoolean("spawner.allowWolf", true);
		} else {
			configYAML.setProperty("spawner.allowWolf", SpawnerAdjuster.allowWolf); 
		}
		
		if(map.containsKey("spawner.allowSheep")) {
			SpawnerAdjuster.allowSheep = configYAML.getBoolean("spawner.allowSheep", true);
		} else {
			configYAML.setProperty("spawner.allowSheep", SpawnerAdjuster.allowSheep); 
		}
		
		if(map.containsKey("spawner.allowCow")) {
			SpawnerAdjuster.allowCow = configYAML.getBoolean("spawner.allowCow", true);
		} else {
			configYAML.setProperty("spawner.allowCow", SpawnerAdjuster.allowCow); 
		}
		
		if(map.containsKey("spawner.allowSquid")) {
			SpawnerAdjuster.allowSquid = configYAML.getBoolean("spawner.allowSquid", false);
		} else {
			configYAML.setProperty("spawner.allowSquid", SpawnerAdjuster.allowSquid); 
		}
		
		if(map.containsKey("spawner.allowPig")) {
			SpawnerAdjuster.allowPig = configYAML.getBoolean("spawner.allowPig", true);
		} else {
			configYAML.setProperty("spawner.allowPig", SpawnerAdjuster.allowPig); 
		}
		
		if(map.containsKey("spawner.allowEnderman")) {
			SpawnerAdjuster.allowEnderman = configYAML.getBoolean("spawner.allowEnderman", true);
		} else {
			configYAML.setProperty("spawner.allowEnderman", SpawnerAdjuster.allowEnderman); 
		}
		
		//Hostile Mobs
		
		if(map.containsKey("spawner.allowCaveSpider")) {
			SpawnerAdjuster.allowCaveSpider = configYAML.getBoolean("spawner.allowCaveSpider", true);
		} else {
			configYAML.setProperty("spawner.allowCaveSpider", SpawnerAdjuster.allowCaveSpider); 
		}
		
		if(map.containsKey("spawner.allowSpider")) {
			SpawnerAdjuster.allowSpider = configYAML.getBoolean("spawner.allowSpider", true);
		} else {
			configYAML.setProperty("spawner.allowSpider", SpawnerAdjuster.allowSpider);
		}
		
		if(map.containsKey("spawner.allowCreeper")) {
			SpawnerAdjuster.allowCreeper = configYAML.getBoolean("spawner.allowCreeper", true);
		} else {
			configYAML.setProperty("spawner.allowCreeper", SpawnerAdjuster.allowCreeper); 
		}
		
		if(map.containsKey("spawner.allowSkeleton")) {
			SpawnerAdjuster.allowSkeleton = configYAML.getBoolean("spawner.allowSkeleton", true);
		} else {
			configYAML.setProperty("spawner.allowSkeleton", SpawnerAdjuster.allowSkeleton); 
		}
		
		if(map.containsKey("spawner.allowZombie")) {
			SpawnerAdjuster.allowZombie = configYAML.getBoolean("spawner.allowZombie", true);
		} else {
			configYAML.setProperty("spawner.allowZombie", SpawnerAdjuster.allowZombie); 
		}
		
		if(map.containsKey("spawner.allowSilverfish")) {
			SpawnerAdjuster.allowSilverfish = configYAML.getBoolean("spawner.allowSilverfish", true);
		} else {
			configYAML.setProperty("spawner.allowSilverfish", SpawnerAdjuster.allowSilverfish); 
		}
		
		if(map.containsKey("spawner.allowSlime")) {
			SpawnerAdjuster.allowSlime = configYAML.getBoolean("spawner.allowSlime", false);
		} else {
			configYAML.setProperty("spawner.allowSlime", SpawnerAdjuster.allowSlime); 
		}
		
		//NetherMobs
		
		if(map.containsKey("spawner.allowGhast")) {
			SpawnerAdjuster.allowGhast = configYAML.getBoolean("spawner.allowGhast", true);
		} else {
			configYAML.setProperty("spawner.allowGhast", SpawnerAdjuster.allowGhast); 
		}
		
		if(map.containsKey("spawner.allowPigZombie")) {
			SpawnerAdjuster.allowPigZombie = configYAML.getBoolean("spawner.allowPigZombie", true);
		} else {
			configYAML.setProperty("spawner.allowPigZombie", SpawnerAdjuster.allowPigZombie); 
		}
		
		//WTF IS THIS SHIT?
		
		if(map.containsKey("spawner.allowGiant")) {
			SpawnerAdjuster.allowGiant = configYAML.getBoolean("spawner.allowGiant", true);
		} else {
			configYAML.setProperty("spawner.allowGiant", SpawnerAdjuster.allowGiant); 
		}
		
		if(configYAML.save()) {
			return true;
		} else {
			return false;
		}
	}
}
