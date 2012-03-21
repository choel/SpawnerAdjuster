package com.sadmean.mc.SpawnerAdjuster;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class AdjusterPlayerListener implements Listener {
	public static SpawnerAdjuster plugin; public AdjusterPlayerListener(SpawnerAdjuster instance) { 
		plugin = instance;
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if(event.getClickedBlock() == null || !SpawnerAdjuster.usePlayerListener) return;
		if(event.getClickedBlock().getType() == Material.MOB_SPAWNER) {
			//if(SpawnerAdjuster.permCheck(event.getPlayer(), "SpawnerAdjuster.ChangeSpawnType")) {
				if(event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
					CreatureSpawner spawner = (CreatureSpawner) event.getClickedBlock().getState();
					String name = spawner.getSpawnedType().getName();
					if(SpawnerAdjuster.opsChangeSpawnTypeOnly) {
						if(event.getPlayer().isOp()) {
							setSpawnType(spawner, event.getPlayer());
						}
					} else {
						setSpawnType(spawner, event.getPlayer());
					}
					String newName = spawner.getSpawnedType().getName();
					event.getPlayer().sendMessage(SpawnerAdjuster.chatPrefix + "Spawner was: "+ ChatColor.GREEN + name + ChatColor.GRAY + " is now: " + ChatColor.GREEN + newName);
					event.setCancelled(true); //maybe prevent block placement?
					spawner.setDelay(200);
				}
			//}
		}
	}
	
	public static void setSpawnType(CreatureSpawner spawner, Player player) {
		int i = -1;
		
		if (spawner.getSpawnedType() == EntityType.CAVE_SPIDER) {
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Hostle.CaveSpider") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 0;
		}
		if (spawner.getSpawnedType() == EntityType.SPIDER) {
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Hostle.Spider") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 1;
		}
		if (spawner.getSpawnedType() == EntityType.CREEPER) {
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Hostle.Creeper") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;			i = 1;
			i = 2;
		}
		if (spawner.getSpawnedType() == EntityType.SILVERFISH) {
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Hostle.Silverfish") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 3;
		}
		if (spawner.getSpawnedType() == EntityType.SKELETON) {
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Hostle.Skeleton") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 4;
		}
		if (spawner.getSpawnedType() == EntityType.ZOMBIE) {
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Hostle.Zombie") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 5;
		}
		if (spawner.getSpawnedType() == EntityType.SLIME) {
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Hostile.Slime") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 6;
		}
		if (spawner.getSpawnedType() == EntityType.ENDERMAN) {
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Hostle.Enderman") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 7;
		}
		if (spawner.getSpawnedType() == EntityType.ENDER_DRAGON) {
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Boss.EnderDragon") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 8;
		}
		if (spawner.getSpawnedType() == EntityType.MAGMA_CUBE) {
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Nether.MagmaCube") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 9;
		}
		if (spawner.getSpawnedType() == EntityType.BLAZE) {
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Nether.Blaze") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 10;
		}
		if (spawner.getSpawnedType() == EntityType.GHAST) {
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Nether.Ghast") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 11;
		}
		if (spawner.getSpawnedType() == EntityType.PIG_ZOMBIE) {
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Nether.PigZombie") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 12;
		}
		if (spawner.getSpawnedType() == EntityType.PIG) {
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Neutral.Pig") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 13;
		}
		if (spawner.getSpawnedType() == EntityType.COW) {
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Neutral.Cow") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 14;
		}
		if (spawner.getSpawnedType() == EntityType.SHEEP) {
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Neutral.Sheep") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 15;
		}
		if (spawner.getSpawnedType() == EntityType.WOLF) {
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Neutral.Wolf") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 16;
		}
		if (spawner.getSpawnedType() == EntityType.CHICKEN) {
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Neutral.Chicken") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 17;
		}
		if (spawner.getSpawnedType() == EntityType.MUSHROOM_COW) {
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Neutral.Mooshroom") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 18;
		}
		if (spawner.getSpawnedType() == EntityType.SNOWMAN) {
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Utility.SnowGolem") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 19;
		}
		if (spawner.getSpawnedType() == EntityType.VILLAGER) {
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Neutral.Villager") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 20;
		}
		if (spawner.getSpawnedType() == EntityType.GIANT){
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Unused.Giant") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 21;
		}
		if (spawner.getSpawnedType() == EntityType.OCELOT){
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Neutral.Ocelot") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 22;
		}
		if (spawner.getSpawnedType() == EntityType.IRON_GOLEM){
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Utility.IronGolem") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 23;
		}
		//if i is still -1, then we have an unknown mob type. We should not play with that spawner
		if(i == -1) {
			SpawnerAdjuster.log_It("warning", "unkown mob type error");
			return;
		}
		
		int initalvalue = i;
		//i--;
		int b = 0;
		while(i != initalvalue || b == 0) {
			if(i >= 24) i = 0;

			if(i == 0 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Hostile.Spider") && SpawnerAdjuster.allowSpider) {
				//spawner.setSpawnedType(EntityType.SPIDER);
				spawner.setSpawnedType(EntityType.SPIDER);
				return;
			}
			if(i == 1 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Hostile.Creeper") && SpawnerAdjuster.allowCreeper) {
				spawner.setSpawnedType(EntityType.CREEPER);
				return;
			}
			if(i == 2 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Hostile.Silverfish") && SpawnerAdjuster.allowSilverfish) {
				spawner.setSpawnedType(EntityType.SILVERFISH);
				return;
			}
			if(i == 3 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Hostile.Skeleton") && SpawnerAdjuster.allowSkeleton) {
				spawner.setSpawnedType(EntityType.SKELETON);
				return;
			}
			if(i == 4 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Hostile.Zombie") && SpawnerAdjuster.allowZombie) {
				spawner.setSpawnedType(EntityType.ZOMBIE);
				return;
			}
			if(i == 5 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Hostile.Slime") && SpawnerAdjuster.allowSlime) {
				spawner.setSpawnedType(EntityType.SLIME);
				return;
			}
			if(i == 6 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Hostile.Enderman") && SpawnerAdjuster.allowEnderman) {
				spawner.setSpawnedType(EntityType.ENDERMAN);
				return;
			}
			if(i == 7 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Boss.EnderDragon") && SpawnerAdjuster.allowEnderDragon) {
				spawner.setSpawnedType(EntityType.ENDER_DRAGON);
				return;
			}
			if(i == 8 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Nether.MagmaCube") && SpawnerAdjuster.allowMagmaCube) {
				spawner.setSpawnedType(EntityType.MAGMA_CUBE);
				return;
			}
			if(i == 9 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Nether.Blaze") && SpawnerAdjuster.allowBlaze) {
				spawner.setSpawnedType(EntityType.BLAZE);
				return;
			}
			if(i == 10 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Nether.Ghast") && SpawnerAdjuster.allowGhast) {
				spawner.setSpawnedType(EntityType.GHAST);
				return;
			}
			if(i == 11 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Nether.PigZombie") && SpawnerAdjuster.allowPigZombie) {
				spawner.setSpawnedType(EntityType.PIG_ZOMBIE);
				return;
			}
			if(i == 12 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Neutral.Pig") && SpawnerAdjuster.allowPig) {
				spawner.setSpawnedType(EntityType.PIG);
				return;
			}
			if(i == 13 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Neutral.Cow") && SpawnerAdjuster.allowCow) {
				spawner.setSpawnedType(EntityType.COW);
				return;
			}
			if(i == 14 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Neutral.Sheep") && SpawnerAdjuster.allowSheep) {
				spawner.setSpawnedType(EntityType.SHEEP);
				return;
			}
			if(i == 15 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Neutral.Wolf") && SpawnerAdjuster.allowWolf) {
				spawner.setSpawnedType(EntityType.WOLF);
				return;
			}
			if(i == 16 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Neutral.Chicken") && SpawnerAdjuster.allowChicken) {
				spawner.setSpawnedType(EntityType.CHICKEN);
				return;
			}
			if(i == 17 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Neutral.Mooshroom") && SpawnerAdjuster.allowMooshroom) {
				spawner.setSpawnedType(EntityType.MUSHROOM_COW);
				return;
			}
			if(i == 18 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Utility.SnowGolem") && SpawnerAdjuster.allowSnowGolem) {
				spawner.setSpawnedType(EntityType.SNOWMAN);
				return;
			}
			if(i == 19 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Neutral.Villager") && SpawnerAdjuster.allowVillager) {
				spawner.setSpawnedType(EntityType.VILLAGER);
				return;
			}
			if(i == 20 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Unused.Giant") && SpawnerAdjuster.allowGiant) {
				spawner.setSpawnedType(EntityType.GIANT);
				return;
			}
			if(i == 21 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Neutral.Ocelot") && SpawnerAdjuster.allowOcelot) {
				spawner.setSpawnedType(EntityType.OCELOT);
				return;
			}
			if(i == 22 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Hostile.IronGolem") && SpawnerAdjuster.allowIronGolem) {
				spawner.setSpawnedType(EntityType.IRON_GOLEM);
				return;
			}
			if(i == 23 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Hostile.CaveSpider") && SpawnerAdjuster.allowCaveSpider) {
				spawner.setSpawnedType(EntityType.CAVE_SPIDER);
				return;
			}
			b++;
			i++;
			if(b > 30) {
				//infinite loop protection.
				return;
			}
		}
	}
}
