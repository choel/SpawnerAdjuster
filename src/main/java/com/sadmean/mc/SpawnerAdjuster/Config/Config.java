package com.sadmean.mc.SpawnerAdjuster.Config;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import com.sadmean.mc.SpawnerAdjuster.SpawnerAdjuster;

public class Config {
	public static boolean load() {
		SpawnerAdjuster.log_It("info", "Loading config ...");
		FileConfiguration configYAML = SpawnerAdjuster.getThisPlugin().getConfig();
		try {
			configYAML.load(SpawnerAdjuster.configFile);
		} catch (FileNotFoundException e1) {
			SpawnerAdjuster.log_It("warning", "Error loading file: File not found:");
			e1.printStackTrace();
		} catch (IOException e1) {
			SpawnerAdjuster.log_It("warning", "Error loading file: Input output exception");
			e1.printStackTrace();
		} catch (InvalidConfigurationException e1) {
			SpawnerAdjuster.log_It("warning", "Error loading file: Not a valid YAML");
			e1.printStackTrace();
		}
		
		if(configYAML.contains("system.onlyOpsChangeSpawnType")) {
			SpawnerAdjuster.opsChangeSpawnTypeOnly = configYAML.getBoolean("system.onlyOpsChangeSpawnType", false);
		} else {
			configYAML.set("system.onlyOpsChangeSpawnType", SpawnerAdjuster.opsChangeSpawnTypeOnly); 
		}
		
		if(configYAML.contains("system.SpawnersRespondToRedstone")) {
			SpawnerAdjuster.respondToRedstone = configYAML.getBoolean("system.SpawnersRespondToRedstone", configYAML.getBoolean("system.useRedstoneListener", true));
		} else {
			configYAML.set("system.SpawnersRespondToRedstone", SpawnerAdjuster.respondToRedstone); 
		}
		
		if(configYAML.contains("system.allowDroppedSpawners")) {
			SpawnerAdjuster.allowDroppedSpawners = configYAML.getBoolean("system.allowDroppedSpawners", configYAML.getBoolean("system.useBlockListener", true));
		} else {
			configYAML.set("system.allowDroppedSpawners", SpawnerAdjuster.allowDroppedSpawners); 
		}
		
		if(configYAML.contains("system.debugLogs")) {
			SpawnerAdjuster.debugLogs = configYAML.getBoolean("system.debugLogs", true);
		} else {
			configYAML.set("system.debugLogs", SpawnerAdjuster.debugLogs); 
		}
		
		/** 
		 * Legacy Permissions is no longer supported, ignorePermissions is now the only permission setting that matters.
		if(configYAML.contains("system.SuperPerms")) {
			SpawnerAdjuster.SuperPerms = configYAML.getBoolean("system.SuperPerms", true);
		} else {
			configYAML.set("system.SuperPerms", SpawnerAdjuster.SuperPerms); 
		}
		**/
		
		if(configYAML.contains("system.ignorePermissions")) {
			SpawnerAdjuster.ignorePermissions = configYAML.getBoolean("system.ignorePermissions", true);
		} else {
			configYAML.set("system.ignorePermissions", SpawnerAdjuster.ignorePermissions); 
		}
		
		if(configYAML.contains("system.useVault")) {
			SpawnerAdjuster.useVault = configYAML.getBoolean("system.useVault", false);
		} else {
			configYAML.set("system.useVault", SpawnerAdjuster.useVault); 
		}
		/**
		if(configYAML.contains("system.deactivedByRedstoneStatus")) {
			SpawnerAdjuster.deactivedByRedstoneStatus = configYAML.getBoolean("system.deactivedByRedstoneStatus", false);
		} else {
			configYAML.set("system.deactivedByRedstoneStatus", SpawnerAdjuster.deactivedByRedstoneStatus); 
		}
		**/
		if(configYAML.contains("system.mustHaveValidPermissionsToAlterSpawner")) {
			SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner = configYAML.getBoolean("system.mustHaveValidPermissionsToAlterSpawner", false);
		} else {
			configYAML.set("system.mustHaveValidPermissionsToAlterSpawner", SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner); 
		}
				
		if(configYAML.contains("system.TotalSpawnedEnts")) {
			SpawnerAdjuster.TotalSpawnedEnts = configYAML.getInt("system.TotalSpawnedEnts", 50);
		} else {
			configYAML.set("system.TotalSpawnedEnts", SpawnerAdjuster.TotalSpawnedEnts); 
		}
				
		if(configYAML.contains("system.redstoneForcesSpawn")) {
			SpawnerAdjuster.redstoneForcesSpawn = configYAML.getBoolean("system.redstoneForcesSpawn", true);
		} else {
			configYAML.set("system.redstoneForcesSpawn", SpawnerAdjuster.redstoneForcesSpawn); 
		}
		
		//radius check
		
		if(configYAML.contains("radiusCheck.useRadiusCheck")) {
			SpawnerAdjuster.useRadiusCheck = configYAML.getBoolean("radiusCheck.useRadiusCheck", true);
		} else {
			configYAML.set("radiusCheck.useRadiusChecks", SpawnerAdjuster.useRadiusCheck); 
		}
		
		if(configYAML.contains("radiusCheck.maxNumberOfEntsNearSpawner")) {
			SpawnerAdjuster.maxNumberOfEntsNearSpawner = configYAML.getInt("radiusCheck.maxNumberOfEntsNearSpawner", 6);
		} else {
			configYAML.set("radiusCheck.maxNumberOfEntsNearSpawner", configYAML.getInt("system.maxNumberOfEntsNearSpawner", SpawnerAdjuster.maxNumberOfEntsNearSpawner)); 
		}
		
		if(configYAML.contains("radiusCheck.distanceToCheck")) {
			SpawnerAdjuster.spawnerEntCheckRadius = configYAML.getInt("radiusCheck.distanceToCheck", 6);
		} else {
			configYAML.set("radiusCheck.distanceToCheck", configYAML.getInt("system.spawnerEntCheckRadius", SpawnerAdjuster.spawnerEntCheckRadius)); 
		}
		
		//Spawner settings
		//Neutral Mobs
		if(configYAML.contains("spawner.allowChicken")) {
			SpawnerAdjuster.allowChicken = configYAML.getBoolean("spawner.allowChicken", true);
		} else {
			configYAML.set("spawner.allowChicken", SpawnerAdjuster.allowChicken); 
		}
		
		if(configYAML.contains("spawner.allowWolf")) {
			SpawnerAdjuster.allowWolf = configYAML.getBoolean("spawner.allowWolf", true);
		} else {
			configYAML.set("spawner.allowWolf", SpawnerAdjuster.allowWolf); 
		}
		
		if(configYAML.contains("spawner.allowSheep")) {
			SpawnerAdjuster.allowSheep = configYAML.getBoolean("spawner.allowSheep", true);
		} else {
			configYAML.set("spawner.allowSheep", SpawnerAdjuster.allowSheep); 
		}
		
		if(configYAML.contains("spawner.allowCow")) {
			SpawnerAdjuster.allowCow = configYAML.getBoolean("spawner.allowCow", true);
		} else {
			configYAML.set("spawner.allowCow", SpawnerAdjuster.allowCow); 
		}
		
		if(configYAML.contains("spawner.allowBlaze")) {
			SpawnerAdjuster.allowBlaze = configYAML.getBoolean("spawner.allowBlaze", false);
		} else {
			configYAML.set("spawner.allowBlaze", SpawnerAdjuster.allowBlaze); 
		}
		
		if(configYAML.contains("spawner.allowOcelot")) {
			SpawnerAdjuster.allowOcelot= configYAML.getBoolean("spawner.allowOcelot", false);
		} else {
			configYAML.set("spawner.allowOcelot", SpawnerAdjuster.allowOcelot); 
		}
		
		if(configYAML.contains("spawner.allowIronGolem")) {
			SpawnerAdjuster.allowIronGolem = configYAML.getBoolean("spawner.allowIronGolem", false);
		} else {
			configYAML.set("spawner.allowIronGolem", SpawnerAdjuster.allowIronGolem); 
		}
		
		if(configYAML.contains("spawner.allowEnderDragon")) {
			SpawnerAdjuster.allowEnderDragon = configYAML.getBoolean("spawner.allowEnderDragon", false);
		} else {
			configYAML.set("spawner.allowEnderDragon", SpawnerAdjuster.allowEnderDragon); 
		}
		
		if(configYAML.contains("spawner.allowMagmaCube")) {
			SpawnerAdjuster.allowMagmaCube = configYAML.getBoolean("spawner.allowMagmaCube", false);
		} else {
			configYAML.set("spawner.allowMagmaCube", SpawnerAdjuster.allowMagmaCube); 
		}
		
		if(configYAML.contains("spawner.allowMooShroom")) {
			SpawnerAdjuster.allowMooshroom = configYAML.getBoolean("spawner.allowMooShroom", false);
		} else {
			configYAML.set("spawner.allowMooShroom", SpawnerAdjuster.allowMooshroom); 
		}
		
		if(configYAML.contains("spawner.allowSnowGolem")) {
			SpawnerAdjuster.allowSnowGolem = configYAML.getBoolean("spawner.allowSnowGolem", false);
		} else {
			configYAML.set("spawner.allowSnowGolem", SpawnerAdjuster.allowSnowGolem); 
		}
		
		if(configYAML.contains("spawner.allowVillager")) {
			SpawnerAdjuster.allowVillager = configYAML.getBoolean("spawner.allowVillager", false);
		} else {
			configYAML.set("spawner.allowVillager", SpawnerAdjuster.allowVillager); 
		}
		
		if(configYAML.contains("spawner.allowSquid")) {
			SpawnerAdjuster.allowSquid = configYAML.getBoolean("spawner.allowSquid", false);
		} else {
			configYAML.set("spawner.allowSquid", SpawnerAdjuster.allowSquid); 
		}
		
		if(configYAML.contains("spawner.allowPig")) {
			SpawnerAdjuster.allowPig = configYAML.getBoolean("spawner.allowPig", true);
		} else {
			configYAML.set("spawner.allowPig", SpawnerAdjuster.allowPig); 
		}
		
		if(configYAML.contains("spawner.allowEnderman")) {
			SpawnerAdjuster.allowEnderman = configYAML.getBoolean("spawner.allowEnderman", true);
		} else {
			configYAML.set("spawner.allowEnderman", SpawnerAdjuster.allowEnderman); 
		}
		
		//Hostile Mobs
		
		if(configYAML.contains("spawner.allowCaveSpider")) {
			SpawnerAdjuster.allowCaveSpider = configYAML.getBoolean("spawner.allowCaveSpider", true);
		} else {
			configYAML.set("spawner.allowCaveSpider", SpawnerAdjuster.allowCaveSpider); 
		}
		
		if(configYAML.contains("spawner.allowSpider")) {
			SpawnerAdjuster.allowSpider = configYAML.getBoolean("spawner.allowSpider", true);
		} else {
			configYAML.set("spawner.allowSpider", SpawnerAdjuster.allowSpider);
		}
		
		if(configYAML.contains("spawner.allowCreeper")) {
			SpawnerAdjuster.allowCreeper = configYAML.getBoolean("spawner.allowCreeper", true);
		} else {
			configYAML.set("spawner.allowCreeper", SpawnerAdjuster.allowCreeper); 
		}
		
		if(configYAML.contains("spawner.allowSkeleton")) {
			SpawnerAdjuster.allowSkeleton = configYAML.getBoolean("spawner.allowSkeleton", true);
		} else {
			configYAML.set("spawner.allowSkeleton", SpawnerAdjuster.allowSkeleton); 
		}
		
		if(configYAML.contains("spawner.allowZombie")) {
			SpawnerAdjuster.allowZombie = configYAML.getBoolean("spawner.allowZombie", true);
		} else {
			configYAML.set("spawner.allowZombie", SpawnerAdjuster.allowZombie); 
		}
		
		if(configYAML.contains("spawner.allowSilverfish")) {
			SpawnerAdjuster.allowSilverfish = configYAML.getBoolean("spawner.allowSilverfish", true);
		} else {
			configYAML.set("spawner.allowSilverfish", SpawnerAdjuster.allowSilverfish); 
		}
		
		if(configYAML.contains("spawner.allowSlime")) {
			SpawnerAdjuster.allowSlime = configYAML.getBoolean("spawner.allowSlime", false);
		} else {
			configYAML.set("spawner.allowSlime", SpawnerAdjuster.allowSlime); 
		}
		
		//NetherMobs
		
		if(configYAML.contains("spawner.allowGhast")) {
			SpawnerAdjuster.allowGhast = configYAML.getBoolean("spawner.allowGhast", true);
		} else {
			configYAML.set("spawner.allowGhast", SpawnerAdjuster.allowGhast); 
		}
		
		if(configYAML.contains("spawner.allowPigZombie")) {
			SpawnerAdjuster.allowPigZombie = configYAML.getBoolean("spawner.allowPigZombie", true);
		} else {
			configYAML.set("spawner.allowPigZombie", SpawnerAdjuster.allowPigZombie); 
		}
		
		//WTF IS THIS SHIT?
		
		if(configYAML.contains("spawner.allowGiant")) {
			SpawnerAdjuster.allowGiant = configYAML.getBoolean("spawner.allowGiant", true);
		} else {
			configYAML.set("spawner.allowGiant", SpawnerAdjuster.allowGiant); 
		}
		
		//Entities
		if(configYAML.contains("spawner.allowFireball")) {
			SpawnerAdjuster.allowFireball = configYAML.getBoolean("spawner.allowFireball", false);
		} else {
			configYAML.set("spawner.allowFireball", SpawnerAdjuster.allowFireball); 
		}
		
		if(configYAML.contains("spawner.allowBoat")) {
			SpawnerAdjuster.allowBoat = configYAML.getBoolean("spawner.allowBoat", false);
		} else {
			configYAML.set("spawner.allowBoat", SpawnerAdjuster.allowBoat); 
		}
		
		if(configYAML.contains("spawner.allowMinecart")) {
			SpawnerAdjuster.allowMinecart = configYAML.getBoolean("spawner.allowMinecart", false);
		} else {
			configYAML.set("spawner.allowMinecart", SpawnerAdjuster.allowMinecart); 
		}
		
		if(configYAML.contains("spawner.allowEgg")) {
			SpawnerAdjuster.allowEgg = configYAML.getBoolean("spawner.allowEgg", false);
		} else {
			configYAML.set("spawner.allowEgg", SpawnerAdjuster.allowEgg); 
		}
		
		if(configYAML.contains("spawner.allowArrow")) {
			SpawnerAdjuster.allowArrow = configYAML.getBoolean("spawner.allowArrow", false);
		} else {
			configYAML.set("spawner.allowArrow", SpawnerAdjuster.allowArrow); 
		}
		
		//advanced settings
		if(configYAML.contains("advanced.stopPigSpawns")) {
			SpawnerAdjuster.advanced_stopPigSpawns = configYAML.getBoolean("advanced.stopPigSpawns", false);
		} else {
			configYAML.set("advanced.stopPigSpawns", false); 
		}
		
		if(configYAML.contains("advanced.requireExtraPermission")) {
			SpawnerAdjuster.advanced_requireExtraPermission = configYAML.getBoolean("advanced.requireExtraPermission", false);
		} else {
			configYAML.set("advanced.requireExtraPermission", false); 
		}
		
		if(configYAML.contains("advanced.hideMessageIfSpawnerUnchanged")) {
			SpawnerAdjuster.advanced_hideMessageIfSpawnerUnchanged = configYAML.getBoolean("advanced.hideMessageIfSpawnerUnchanged", false);
		} else {
			configYAML.set("advanced.hideMessageIfSpawnerUnchanged", false); 
		}
		
		//ignorePermissions extended
		if(SpawnerAdjuster.ignorePermissions) {
			/** This stuff not yet ready (or needed)
			SpawnerAdjuster.log_It("info", "ignorePermissions is set to true, extending config");
			
			if(configYAML.contains("ignorePermissions.OpAccessOnly")) {
				SpawnerAdjuster.ignore_opsOnly = configYAML.getBoolean("ignorePermissions.OpAccessOnly", true);
			} else {
				configYAML.set("ignorePermissions.OpAccessOnly", SpawnerAdjuster.ignore_opsOnly);
			}
			
			
			if(configYAML.contains("ignorePermissions.OpsOnlyRedstone")) {
				SpawnerAdjuster.ignore_OpsOnlyRedstone = configYAML.getBoolean("ignorePermissions.OpsOnlyRedstone", true);
			} else {
				configYAML.set("ignorePermissions.OpsOnlyRedstone", SpawnerAdjuster.ignore_OpsOnlyRedstone); 
			}
			
			if(configYAML.contains("ignorePermissions.OpsOnlyChangeSpawner")) {
				SpawnerAdjuster.ignore_OpsOnlyChangeSpawner = configYAML.getBoolean("ignorePermissions.OpsOnlyChangeSpawner", true);
			} else {
				configYAML.set("ignorePermissions.OpsOnlyChangeSpawner", SpawnerAdjuster.ignore_OpsOnlyChangeSpawner); 
			}
			
			if(configYAML.contains("ignorePermissions.OpsOnlyDropSpanwers")) {
				SpawnerAdjuster.ignore_OpsOnlyDropSpanwers = configYAML.getBoolean("ignorePermissions.OpsOnlyDropSpanwers", true);
			} else {
				configYAML.set("ignorePermissions.OpsOnlyDropSpanwers", SpawnerAdjuster.ignore_OpsOnlyDropSpanwers); 
			}
			**/

		}
		//remove old values

		configYAML.set("system.useBlockListener", null);	
		configYAML.set("system.useRedstoneListener", null);	
		configYAML.set("system.usePlayerListener", null);	
		configYAML.set("system.useRestoneListener", null);
		configYAML.set("system.maxNumberOfEntsNearSpawner", null);
		configYAML.set("system.spawnerEntCheckRadius", null);
		configYAML.set("system.superPerms", null);
		configYAML.set("opsChangeSpawnTypeOnly", null);
		
		//these values are not currently used and should really just be hidden until I use them
		configYAML.set("system.deactivedByRedstoneStatus", null);
		
		try {
			configYAML.save(SpawnerAdjuster.configFile);
		} catch (IOException e) {
			SpawnerAdjuster.log_It("warning", "Error saving file:");
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
